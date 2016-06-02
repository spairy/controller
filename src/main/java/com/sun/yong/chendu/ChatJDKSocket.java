package com.sun.yong.chendu;

import java.io.IOException;
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
import com.sun.yong.common.entity.common.LogFlag;
import com.sun.yong.common.entity.common.UserSession;
import com.sun.yong.common.entity.request.MessageRequest;
import com.sun.yong.common.entity.response.UserResponse;
import com.sun.yong.common.utils.DateUtils;
import com.sun.yong.common.utils.LogUtils;

@ServerEndpoint(value = "/chatJDK", configurator = GetHttpSessionConfigurator.class)
public class ChatJDKSocket {

	private IChatService chatService;
	
	private IAuthorityService authorityService;
	
	private LogFlag logFlag;
	
    private static int onlineCount = 0;
	private static final ConcurrentHashMap<String, ChatJDKSocket> memberSessionMap = 
			new ConcurrentHashMap<String, ChatJDKSocket>();
	
	private Session session;
	private HttpSession httpSession;
	private UserSession userSession;

	public Session getSession() {
		return session;
	}
	
	public void setChatService(IChatService chatService) {
		this.chatService = chatService;
	}  

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	/**
	 * A connection is open
	 *
	 * @param session
	 * @param config
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) throws IOException {
		logFlag = LogUtils.getLogFlag();;
		this.session = session;
		this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		this.userSession = (UserSession) httpSession.getAttribute(Constant.USER_SESSION_KEY);
		if (null == userSession) {
			onError(session, new Throwable("User is not login!"));
			return;
		}
		
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		chatService = (ChatServiceImpl) ac.getBean("chatService");
		authorityService = (AuthorityServiceImpl) ac.getBean("authorityService");
		
		UserResponse userResponse = authorityService.getUser(userSession.getMemberID(), logFlag);
		
		memberSessionMap.put(userSession.getMemberID(), this);
		addOnlineCount();

		MessageEventRequest messageEventRequest = new MessageEventRequest();
		messageEventRequest.setFromMemberID("10000001");
		messageEventRequest.setToMemberID(userSession.getMemberID());
		messageEventRequest.setContent(new ObjectMapper().writeValueAsString(userResponse));
		messageEventRequest.setSend(Boolean.FALSE);
		broadcastMessage(messageEventRequest);
	}
	
	private void broadcastMessage(MessageEventRequest messageEventRequest) throws IOException {
		System.out.println("Enter broadcastMessage.");
		
		String dateTime = DateUtils.getCurrentDateTime();
		
		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setFromMemberID(messageEventRequest.getFromMemberID());
		messageRequest.setToMemberID(messageEventRequest.getToMemberID());
		messageRequest.setContent(messageEventRequest.getContent());
		messageRequest.setSend(messageEventRequest.isSend());
		messageRequest.setDateTime(dateTime);
		chatService.insertMessage(messageRequest, logFlag);
		
		MessageEventResponse messageEventResponse = new MessageEventResponse();
		if ("10000001".equalsIgnoreCase(messageEventRequest.getFromMemberID())) {
			messageEventResponse.setFromUsername("SYSTEM");
		} else {
			messageEventResponse.setFromUsername(userSession.getUsername());
		}
		messageEventResponse.setContent(messageRequest.getContent());
		messageEventResponse.setDateTime(dateTime);
		if (null != memberSessionMap.get(messageEventRequest.getToMemberID())){
			messageEventResponse.setToUsername(memberSessionMap.get(messageEventRequest.getToMemberID()).getUserSession().getUsername());
		}
		
		String message = new ObjectMapper().writeValueAsString(messageEventResponse);
		sendMessage(messageEventRequest.getFromMemberID(), message);
		sendMessage(messageEventRequest.getToMemberID(), message);
	}
	
	private void sendMessage(String memberID, String message) throws IOException {
		if (null != memberSessionMap.get(memberID)){
			memberSessionMap.get(memberID).getSession().getBasicRemote().sendText(message);
		}
	}

	@OnMessage
	public void onMessage(String jsonMessage) throws IOException {
		System.out.println("send message:" + jsonMessage);
		MessageEventRequest messageEventRequest = 
				new ObjectMapper().readValue(jsonMessage, MessageEventRequest.class);
		messageEventRequest.setFromMemberID(userSession.getMemberID());
		broadcastMessage(messageEventRequest);
	}

	/**
	 * Close a session
	 *
	 * @param session
	 * @param closeReason
	 */
	@OnClose
	public void onClose(Session session, CloseReason closeReason) throws IOException {
		if(session.isOpen()){
			try{
				session.close();
			} catch(IllegalStateException | IOException ex){
				
			}
		}
		subOnlineCount();
		MessageEventRequest messageEventRequest = new MessageEventRequest();
		messageEventRequest.setFromMemberID("10000001");
		messageEventRequest.setToMemberID(userSession.getMemberID());
		messageEventRequest.setContent("On Close!");
		messageEventRequest.setSend(Boolean.FALSE);
		broadcastMessage(messageEventRequest);
	}

	@OnError
	public void onError(Session session, Throwable t) throws IOException {
		System.out.println("Enter on Error method.");
		System.out.println(t.getMessage());
		
		MessageEventRequest messageEventRequest = new MessageEventRequest();
		messageEventRequest.setFromMemberID("10000001");
		messageEventRequest.setToMemberID(userSession.getMemberID());
		messageEventRequest.setContent("On Error!");
		messageEventRequest.setSend(Boolean.FALSE);
		broadcastMessage(messageEventRequest);
		
		t.printStackTrace();
	}
	
	public static synchronized int getOnlineCount() {
        return onlineCount;
    }
 
    public static synchronized void addOnlineCount() {
    	ChatJDKSocket.onlineCount++;
    	System.out.println("On line member number is: " + ChatJDKSocket.getOnlineCount());
    }
     
    public static synchronized void subOnlineCount() {
    	ChatJDKSocket.onlineCount--;
    	System.out.println("On line member number is: " + ChatJDKSocket.getOnlineCount());
    }
}