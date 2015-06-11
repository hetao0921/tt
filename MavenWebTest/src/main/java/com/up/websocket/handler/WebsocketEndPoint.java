package com.up.websocket.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebsocketEndPoint extends TextWebSocketHandler {
	
	private static final ArrayList<WebSocketSession> users;//这个会出现性能问题，最好用Map来存储，key用userid
	private static Logger logger = Logger.getLogger(WebsocketEndPoint.class);
	static {
		users = new ArrayList<WebSocketSession>();
	}
	
	Map<String, WebSocketSession> sessions = new HashMap<String, WebSocketSession>();
	@Override
	protected void handleTextMessage(WebSocketSession session,
			TextMessage message) throws Exception {
		Map<String, Object> map = session.getAttributes();
		System.out.println(map);
		System.out.println(session);
		System.out.println("getId" + session.getId());
		System.out.println("getUri" + session.getUri());
		System.out.println("isOpen" + session.isOpen());
		System.out.println("Principal" + session.getPrincipal());
		System.out.println("getHandshakeAttributes" + map);
		System.out.println("getLocalAddress" + session.getLocalAddress());
		System.out.println("getRemoteAddress" + session.getRemoteAddress());
		System.out.println("getAcceptedProtocol"
				+ session.getAcceptedProtocol());
		System.out.println("getExtensions" + session.getExtensions());
		System.out.println("sessions "+ sessions);
		

		sessions.put(session.getId(), session);

		Iterator iter = sessions.entrySet().iterator();
		System.out.println("size1111 "+ sessions.size());
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			WebSocketSession subSession=(WebSocketSession) entry.getValue();
			System.out.println("subSession11111" + subSession);
			
			super.handleTextMessage(subSession, message);
			TextMessage returnMessage = new TextMessage(message.getPayload()
					+ " received at server");
			subSession.sendMessage(returnMessage);
		}


		


	}
	
	
	/**
	 * 连接成功时候，会触发UI上onopen方法
	 */
	@Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("connect to the websocket success......");
	users.add(session);
	//这块会实现自己业务，比如，当用户登录后，会把离线消息推送给用户
        //TextMessage returnMessage = new TextMessage("你将收到的离线");
	//session.sendMessage(returnMessage);
    }



	/**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get(Constants.WEBSOCKET_USERNAME).equals(userName)) {
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
    
    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
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
    
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        logger.debug("websocket connection closed......");
        users.remove(session);
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.debug("websocket connection closed......");
        users.remove(session);
    }
    
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
