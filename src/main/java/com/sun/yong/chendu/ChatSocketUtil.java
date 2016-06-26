package com.sun.yong.chendu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.sun.yong.business.IChatService;
import com.sun.yong.common.entity.common.LogFlag;
import com.sun.yong.common.entity.common.MsgEvtType;
import com.sun.yong.common.entity.request.MessageRequest;

public class ChatSocketUtil {

	public static void insertMessage(IChatService chatService, MsgEvtReq msgEvtReq, LogFlag logFlag) {
		System.out.println("Enter insertMessage.");
		MessageRequest messageRequest = null;
		if (MsgEvtType.NEWMSG.toString().equalsIgnoreCase(msgEvtReq.getMsgEvtType()) && null != msgEvtReq.getMsgEvtList()) {
			List<MessageRequest> insertMessageRequestList = new ArrayList<MessageRequest>();
			for (MsgEvt msgEvt: msgEvtReq.getMsgEvtList()) {
				messageRequest = new MessageRequest();
				messageRequest.setFromMemberID(msgEvtReq.getFromMemberID());
				messageRequest.setToMemberID(msgEvtReq.getToMemberID());
				messageRequest.setContent(msgEvt.getContent());
				messageRequest.setSend(msgEvt.getIsSend());
				messageRequest.setDateTime(msgEvt.getDateTime());
				insertMessageRequestList.add(messageRequest);
			}
			chatService.insertMessage(insertMessageRequestList, logFlag);
		}
	}
		
	public static void sendMessage(String memberID, String message, ConcurrentHashMap<String, ChatJDKSocket> memberSessionMap) throws IOException {
		if (null != memberSessionMap.get(memberID)){
			memberSessionMap.get(memberID).getSession().getBasicRemote().sendText(message);
		}
	}
}
