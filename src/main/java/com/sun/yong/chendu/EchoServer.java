package com.sun.yong.chendu;

import java.io.IOException;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

public class EchoServer extends Endpoint {

    public void onOpen(Session session, EndpointConfig config) {
        final RemoteEndpoint.Basic remote = session.getBasicRemote();
        session.addMessageHandler(new MessageHandler.Whole<String>() {
            public void onMessage(String text) {
                try {
                    remote.sendText("Got your message (" + text + "). Thanks !");
                } catch (IOException ioe) {
                    // handle send failure here
                }
            }
        });
    }

}