package com.sun.yong.chendu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.yong.business.IAuthorityService;
import com.sun.yong.business.IChatService;
import com.sun.yong.business.impl.AuthorityServiceImpl;
import com.sun.yong.business.impl.ChatServiceImpl;
import com.sun.yong.common.Constant;
import com.sun.yong.common.entity.common.ErrorEnum;
import com.sun.yong.common.entity.common.LogFlag;
import com.sun.yong.common.entity.common.MsgEvtType;
import com.sun.yong.common.entity.common.SpecMember;
import com.sun.yong.common.entity.common.UserSession;
import com.sun.yong.common.entity.response.FriendResponse;
import com.sun.yong.common.utils.DateUtils;
import com.sun.yong.common.utils.LogUtils;

@ServerEndpoint(value = "/chatJDK", configurator = GetHttpSessionConfigurator.class)
public class ChatJDKSocket {

	private static final ConcurrentHashMap<String, ChatJDKSocket> memberSessionMap = 
			new ConcurrentHashMap<String, ChatJDKSocket>();
    private static int onlineCount = 0;
    
	private static IChatService chatService;
	private static IAuthorityService authorityService;

	private LogFlag logFlag;
	
	private Session session;
	private HttpSession httpSession;
	private UserSession userSession;

	public Session getSession() {
		return session;
	}
	
	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	static {
		@SuppressWarnings("resource")
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		chatService = (ChatServiceImpl) ac.getBean("chatService");
		authorityService = (AuthorityServiceImpl) ac.getBean("authorityService");
	}
	/**
	 * A connection is open
	 *
	 * @param session
	 * @param config
	 * @throws Exception 
	 */
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) throws Exception {
		logFlag = LogUtils.getLogFlag();;
		this.session = session;
		this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		this.userSession = (UserSession) httpSession.getAttribute(Constant.USER_SESSION_KEY);
		if (null == userSession) {
			onError(session, new Throwable(ErrorEnum.ERR_SYS_002.toString()));
			return;
		}
		
		// close old chat
		if (null != memberSessionMap.get(userSession.getMemberID())) {
			ChatJDKSocket oldChat = (ChatJDKSocket)memberSessionMap.get(userSession.getMemberID());
			oldChat.onClose(oldChat.getSession(), new CloseReason(CloseReason.CloseCodes.TLS_HANDSHAKE_FAILURE, ErrorEnum.ERR_SYS_003.toString()));
			memberSessionMap.remove(userSession.getMemberID());
			subOnlineCount();
		}
		memberSessionMap.put(userSession.getMemberID(), this);
		addOnlineCount();
		
		// insert login message
		insertLoginMessage();
		
		// get friend list
		FriendResponse friendResponse = authorityService.getFriendList(userSession.getMemberID(), logFlag);
		
		// send friend list
		sendFriendList(friendResponse);
	}
	
	private void insertLoginMessage() {
		MsgEvtReq msgEvtReq = new MsgEvtReq();
		msgEvtReq.setMsgEvtType(MsgEvtType.NEWMSG.toString());
		msgEvtReq.setFromMemberID(SpecMember.SYSTEM.getMemberID());
		msgEvtReq.setFromUsername(SpecMember.SYSTEM.getUsername());
		msgEvtReq.setToMemberID(userSession.getMemberID());
		msgEvtReq.setToUsername(userSession.getUsername());
		List<MsgEvt> msgEvtList = new ArrayList<MsgEvt>();
		MsgEvt msgEvt = new MsgEvt();
		msgEvt.setContent("Login");
		msgEvt.setDateTime(DateUtils.getCurrentDateTime());
		msgEvt.setIsSend(false);
		msgEvtList.add(msgEvt);
		msgEvtReq.setMsgEvtList(msgEvtList);
		ChatSocketUtil.insertMessage(chatService, msgEvtReq, logFlag);
	}
	
	private void sendFriendList(FriendResponse friendResponse)  throws IOException {
		MsgEvtReq msgEvtReq = new MsgEvtReq();
		msgEvtReq.setMsgEvtType(MsgEvtType.FRIEND.toString());
		msgEvtReq.setFromMemberID(SpecMember.SYSTEM.getMemberID());
		msgEvtReq.setFromUsername(SpecMember.SYSTEM.getUsername());
		msgEvtReq.setToMemberID(userSession.getMemberID());
		msgEvtReq.setToUsername(userSession.getUsername());
		List<MsgEvt> msgEvtList = new ArrayList<MsgEvt>();
		MsgEvt msgEvt = new MsgEvt();
		msgEvt.setContent(new ObjectMapper().writeValueAsString(friendResponse.getFriendList()));
		msgEvt.setDateTime(DateUtils.getCurrentDateTime());
		msgEvt.setIsSend(false);
		msgEvtList.add(msgEvt);
		msgEvtReq.setMsgEvtList(msgEvtList);
		broadcastMessage(msgEvtReq);
	}
	
	private void broadcastMessage(MsgEvtReq messageEventRequest) throws IOException {
		System.out.println("Enter broadcastMessage.");
		String message = new ObjectMapper().writeValueAsString(messageEventRequest);
		if (userSession.getMemberID().equals(messageEventRequest.getFromMemberID())) {
			session.getBasicRemote().sendText(message);
		}
		if (null != messageEventRequest.getToMemberID() && null != memberSessionMap.get(messageEventRequest.getToMemberID())) {
			memberSessionMap.get(messageEventRequest.getToMemberID()).getSession().getBasicRemote().sendText(message);
		}
	}
	
	@OnMessage
	public void onMessage(String jsonMessage) throws IOException {
		System.out.println("send message:" + jsonMessage);
		MsgEvtReq msgEvtReq = 
				new ObjectMapper().readValue(jsonMessage, MsgEvtReq.class);
		msgEvtReq.setFromMemberID(userSession.getMemberID());
		msgEvtReq.setFromUsername(userSession.getUsername());
		msgEvtReq.setToUsername(msgEvtReq.getToMemberID());
		msgEvtReq.getMsgEvtList().get(0).setDateTime(DateUtils.getCurrentDateTime());
		broadcastMessage(msgEvtReq);
	}

	/**
	 * Close a session
	 *
	 * @param session
	 * @param closeReason
	 */
	@OnClose
	public void onClose(Session session, CloseReason closeReason) throws IOException {
		try{
			if(session.isOpen()){
				session.close();
			}
		} catch(IllegalStateException | IOException ex){
			// TODO 
		} finally {
			memberSessionMap.remove(userSession.getMemberID());
			subOnlineCount();
		}
		
	}

	@OnError
	public void onError(Session session, Throwable t) throws IOException {
		System.out.println("Enter on Error method.");
		System.out.println(t.getMessage());
		t.printStackTrace();
	}
	
	public static int getOnlineCount() {
        return onlineCount;
    }
 
    public static void addOnlineCount() {
    	ChatJDKSocket.onlineCount++;
    	System.out.println("(add)On line member number is: " + ChatJDKSocket.getOnlineCount());
    }
     
    public static void subOnlineCount() {
    	ChatJDKSocket.onlineCount--;
    	System.out.println("(sub)On line member number is: " + ChatJDKSocket.getOnlineCount());
    }
}