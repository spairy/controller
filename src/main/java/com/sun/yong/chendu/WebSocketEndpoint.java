package com.sun.yong.chendu;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

//http://www.myexception.cn/web/1997640.html
//è¯¥æ³¨è§£ç”¨æ�¥æŒ‡å®šä¸€ä¸ªURI,å®¢æˆ·ç«¯å�¯ä»¥é€šè¿‡è¿™ä¸ªURIæ�¥è¿žæŽ¥åˆ°WebSocket,ç±»ä¼¼Servletçš„æ³¨è§£mapping,æ— éœ€åœ¨web.xmlä¸­é…�ç½®.
//{name}ç”¨æ�¥ä¼ é€’å½“å‰�åŠ å…¥è€…çš„å��ç§°,RESTful
@ServerEndpoint(value = "/websocket/{msgTo}", configurator = GetHttpSessionConfigurator.class)
public class WebSocketEndpoint {

	//private static final Set<WebSocketEndpoint> onlineUsers = new CopyOnWriteArraySet<WebSocketEndpoint>(); // é�™æ€�å�˜é‡�
	//é�™æ€�å�˜é‡�ï¼Œç”¨æ�¥è®°å½•å½“å‰�åœ¨çº¿è¿žæŽ¥æ•°ã€‚åº”è¯¥æŠŠå®ƒè®¾è®¡æˆ�çº¿ç¨‹å®‰å…¨çš„ã€‚
    private static int onlineCount = 0;
	//å­˜å‚¨ memberID å’Œæœ‰è�”ç³»çš„å¯¹è±¡ID List
	//private static final ConcurrentHashMap<String, CopyOnWriteArraySet<String>> conversationMap = new ConcurrentHashMap<String, CopyOnWriteArraySet<String>>();
	//å­˜å‚¨ memberID å’Œå¯¹è±¡
	private static final ConcurrentHashMap<String, WebSocketEndpoint> memberSessionMap = new ConcurrentHashMap<String, WebSocketEndpoint>();
	private Session session;
	private HttpSession httpSession;
	private String from;
	private String to;
	
	/**
	 * A connection is open
	 *
	 * @param session
	 * @param config
	 * @throws IOException
	 */
	@OnOpen
	public void onOpen(Session session, EndpointConfig config, @PathParam("msgTo") String msgTo) throws IOException {
		this.session = session;
		this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		this.from = (String) httpSession.getAttribute("LOGIN_USER");
		this.to = msgTo;

		memberSessionMap.put(this.from +"||" +this.to, this);

		//mappingConversation(this.from, this.to);

		addOnlineCount();

		MessageEvent event  = new MessageEvent("SYSTEM", this.from, "LOGIN_WELCOME");
		event.setContent("WELCOME! You can send message to " + (null == this.to ? "All" : this.to));
		String packagedMsg = new ObjectMapper().writeValueAsString(event);

		broadcastMessage(this.from, this.to, packagedMsg, true);
	}
	/*
	private void mappingConversation(String from, String to){
		//from -> to
		CopyOnWriteArraySet<String> fromConversation = null;
		if(conversationMap.get(from) == null){
			conversationMap.put(from, new CopyOnWriteArraySet<String>());
		}
		fromConversation = conversationMap.get(from);
		fromConversation.add(to);
		//to -> from
		CopyOnWriteArraySet<String> toConversation = null;
		if(conversationMap.get(to) == null){
			conversationMap.put(to, new CopyOnWriteArraySet<String>());
		}
		toConversation = conversationMap.get(to);
		toConversation.add(from);
	}
	*/
	/*private ArrayList<WebSocketEndpoint> searchforSession(WebSocketEndpoint wse){
		ArrayList<WebSocketEndpoint> list = new ArrayList<WebSocketEndpoint>();
		list.add(this);
		WebSocketEndpoint toWse = memberSessinMap.get(wse.getTo());
		if(toWse != null){
			list.add(toWse);
			Session toSession = toWse.getSession();
			ArrayList<WebSocketEndpoint> subList = new ArrayList<WebSocketEndpoint>();
			subList.add(this);
			subList.add(toWse);
			storeMap.put(toSession.getId(), subList);
		}
		return list;
	}*/
	
	private void broadcastMessage(String from, String to, String message) throws IOException{
		broadcastMessage(from, to, message, false);
	}
	private void broadcastMessage(String from, String to, String message, boolean isFirstLogin)
			throws IOException {
		System.out.println("####online count:" + WebSocketEndpoint.onlineCount);
		for(ConcurrentHashMap.Entry<String, WebSocketEndpoint> member: memberSessionMap.entrySet()){
			WebSocketEndpoint wse = member.getValue();
			Session sessionX = wse.getSession();
			System.out.print(member.getKey() +" | ");
			System.out.print(member.getValue() +" | ");
			System.out.print(sessionX.getId() +" | ");
			System.out.print(wse.getFrom() +" | ");
			System.out.println(wse.getTo());
		}
		System.out.println("isFirstLogin="+isFirstLogin);
		System.out.println("from="+from);
		System.out.println("to="+to);
		System.out.println("message="+message);
		System.out.println("memberSessionMap.get(to)=" + (memberSessionMap.get(to)==null));
		System.out.println("#################### è°ƒè¯•ä¿¡æ�¯ç»“æ�Ÿ #################");
		
		//ä¸�æ˜¯ç™»å½•ä¿¡æ�¯
		if(!isFirstLogin){
			//toæ˜¯ALL,ç³»ç»Ÿæ¶ˆæ�¯.å¹¿æ’­ç»™æ‰€æœ‰äºº(å�¯ä»¥åœ¨é¡µé�¢éªŒè¯�æ�ƒé™�ä¿¡æ�¯)
			if("ALL".equals(to)){
				for(ConcurrentHashMap.Entry<String, WebSocketEndpoint> member: memberSessionMap.entrySet()){
					Session sessionX = member.getValue().getSession();
					if((isFirstLogin && sessionX != this.session) || !isFirstLogin){
						sessionX.getBasicRemote().sendText(message);
					}
				}
			//toä¸�æ—¶ALL,å±žäºŽç§�è�Š
			}else{
				//toå·²ç»�ç™»å½•,å¹¿æ’­æ¶ˆæ�¯ç»™toå’Œfrom
				if(memberSessionMap.get(to + "||" + from) != null){
					//å¹¿æ’­ç»™to
					WebSocketEndpoint wse = memberSessionMap.get(to + "||" + from);
					Session sessionX = wse.getSession();
					sessionX.getBasicRemote().sendText(message);
					if(from != null){
						WebSocketEndpoint wseF = memberSessionMap.get(from + "||" + to);
						wseF.getSession().getBasicRemote().sendText(message);
					}
				//toæ²¡æœ‰ç™»å½•,å¹¿æ’­ç»™from,å‘Šè¯‰ä»–toæœªç™»å½•çš„æ¶ˆæ�¯
				}else{
					//å½“å‰�ç”¨æˆ·é€€å‡º,toä¹Ÿå·²ç»�æŽ‰çº¿æˆ–è€…ä¸�å�¯è¾¾
					if(from != null){
						//åˆ›å»ºç”¨æˆ·ä¸�åœ¨çº¿é€šçŸ¥
						MessageEvent event  = new MessageEvent("SYSTEM", this.from, "MEMBER_NOT_LOGIN");
						event.setContent("["+ new Date() +"]."+ this.to +" å½“å‰�ä¸�åœ¨çº¿,ä¼šåœ¨ä¸Šçº¿å�ŽæŽ¨é€�.");
						String packagedMsg = new ObjectMapper().writeValueAsString(event);
						//å¹¿æ’­ç»™è‡ªå·±
						WebSocketEndpoint wseF = memberSessionMap.get(from + "||" + to);
						wseF.getSession().getBasicRemote().sendText(packagedMsg);
					}
				}
			}
		//æ˜¯ç™»å½•ä¿¡æ�¯
		}else{
			//ç”¨æˆ·ç™»å½•, å�ªå¹¿æ’­ç»™è‡ªå·±çš„æ¬¢è¿Žä¿¡æ�¯
			this.getSession().getBasicRemote().sendText(message);
		}
	}

	@OnMessage
	public void onMessage(String jsonMessage) throws IOException {
		System.out.println("send message:" + jsonMessage);
		MessageEvent event = new ObjectMapper().readValue(jsonMessage, MessageEvent.class);
		event.setFrom(this.from);
		event.setTo(this.to);
		event.setSendDate(new Date());
		String packageMessage = new ObjectMapper().writeValueAsString(event);
		broadcastMessage(this.from, this.to, packageMessage);
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
		//æ›´æ–°åœ¨çº¿äººæ•°
		subOnlineCount();
		//åˆ›å»ºç”¨æˆ·ç¦»çº¿é€šçŸ¥
		MessageEvent event  = new MessageEvent("SYSTEM", this.to, "MEMBER_OFFLINE");
		event.setContent("["+ new Date() +"]."+ this.from +" å·²ç»�ä¸‹çº¿.");
		String packagedMsg = new ObjectMapper().writeValueAsString(event);
		//å°†ä¸‹çº¿æ¶ˆæ�¯å�‘é€�ç»™å¯¹è¯�çš„äºº
		broadcastMessage(null, this.to, packagedMsg);
	}

	@OnError
	public void onError(Session session, Throwable t) {
		System.out.println("*********************************************************");
		t.printStackTrace();
	}
	
	public static synchronized int getOnlineCount() {
        return onlineCount;
    }
 
    public static synchronized void addOnlineCount() {
    	WebSocketEndpoint.onlineCount++;
    	System.out.println("å½“å‰�ç”¨æˆ·ä¸ªæ•°" + WebSocketEndpoint.getOnlineCount());
    }
     
    public static synchronized void subOnlineCount() {
    	WebSocketEndpoint.onlineCount--;
    	System.out.println("å½“å‰�ç”¨æˆ·ä¸ªæ•°" + WebSocketEndpoint.getOnlineCount());
    }

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
}