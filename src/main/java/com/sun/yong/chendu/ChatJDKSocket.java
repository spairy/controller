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

@ServerEndpoint(value = "/chatJDK", configurator = GetHttpSessionConfigurator.class)
public class ChatJDKSocket {

    private static int onlineCount = 0;
	private static final ConcurrentHashMap<String, ChatJDKSocket> memberSessionMap = new ConcurrentHashMap<String, ChatJDKSocket>();
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
		System.out.println("####online count:" + ChatJDKSocket.onlineCount);
		for(ConcurrentHashMap.Entry<String, ChatJDKSocket> member: memberSessionMap.entrySet()){
			ChatJDKSocket wse = member.getValue();
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
		
		if(!isFirstLogin){
			if("ALL".equals(to)){
				for(ConcurrentHashMap.Entry<String, ChatJDKSocket> member: memberSessionMap.entrySet()){
					Session sessionX = member.getValue().getSession();
					if((isFirstLogin && sessionX != this.session) || !isFirstLogin){
						sessionX.getBasicRemote().sendText(message);
					}
				}
			}else{
				if(memberSessionMap.get(to + "||" + from) != null){
					ChatJDKSocket wse = memberSessionMap.get(to + "||" + from);
					Session sessionX = wse.getSession();
					sessionX.getBasicRemote().sendText(message);
					if(from != null){
						ChatJDKSocket wseF = memberSessionMap.get(from + "||" + to);
						wseF.getSession().getBasicRemote().sendText(message);
					}
				}else{
					if(from != null){
						MessageEvent event  = new MessageEvent("SYSTEM", this.from, "MEMBER_NOT_LOGIN");
						event.setContent("["+ new Date() +"]."+ this.to +" å½“å‰�ä¸�åœ¨çº¿,ä¼šåœ¨ä¸Šçº¿å�ŽæŽ¨é€�.");
						String packagedMsg = new ObjectMapper().writeValueAsString(event);
						ChatJDKSocket wseF = memberSessionMap.get(from + "||" + to);
						wseF.getSession().getBasicRemote().sendText(packagedMsg);
					}
				}
			}
		}else{
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
		subOnlineCount();
		MessageEvent event  = new MessageEvent("SYSTEM", this.to, "MEMBER_OFFLINE");
		event.setContent("["+ new Date() +"]."+ this.from +" å·²ç»�ä¸‹çº¿.");
		String packagedMsg = new ObjectMapper().writeValueAsString(event);
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
    	ChatJDKSocket.onlineCount++;
    	System.out.println("å½“å‰�ç”¨æˆ·ä¸ªæ•°" + ChatJDKSocket.getOnlineCount());
    }
     
    public static synchronized void subOnlineCount() {
    	ChatJDKSocket.onlineCount--;
    	System.out.println("å½“å‰�ç”¨æˆ·ä¸ªæ•°" + ChatJDKSocket.getOnlineCount());
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