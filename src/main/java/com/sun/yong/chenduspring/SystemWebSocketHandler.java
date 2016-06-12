/*package com.sun.yong.chenduspring;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.sun.yong.business.IChatService;

public class SystemWebSocketHandler implements WebSocketHandler {

    //private static final Logger logger;

    private static final ArrayList<WebSocketSession> users;

    static {
        users = new ArrayList<>();
        //logger = LoggerFactory.getLogger(SystemWebSocketHandler.class);
    }

    @Autowired
	private IChatService chatService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
       // logger.debug("connect to the websocket success......");
        users.add(session);
        String userName = (String) session.getAttributes().get(""Constants.WEBSOCKET_USERNAME);
        if(userName!= null){
            //查询未读消息
           //TODO int count = chatService.getUnReadNews((String) session.getAttributes().get(""/*Constants.WEBSOCKET_USERNAME));

           //TODO session.sendMessage(new TextMessage(count + ""));
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

        //sendMessageToUsers();
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
       // logger.debug("websocket connection closed......");
        users.remove(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
      //  logger.debug("websocket connection closed......");
        users.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    *//**
     * 给所有在线用户发送消息
     *
     * @param message
     *//*
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    *//**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     *//*
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get(""Constants.WEBSOCKET_USERNAME).equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}*/