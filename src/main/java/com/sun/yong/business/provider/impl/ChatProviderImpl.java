package com.sun.yong.business.provider.impl;

import java.util.ArrayList;
import java.util.List;

import com.sun.yong.business.provider.IChatProvider;
import com.sun.yong.common.entity.common.BaseResponse;
import com.sun.yong.common.entity.common.ErrorInfo;
import com.sun.yong.common.entity.common.LogFlag;
import com.sun.yong.common.entity.model.Message;
import com.sun.yong.common.entity.request.MessageRequest;
import com.sun.yong.dataservice.IDataServiceSpringJDBC;

public class ChatProviderImpl implements IChatProvider {

	private IDataServiceSpringJDBC dataService;
	
	public void setDataService(IDataServiceSpringJDBC dataService) {
		this.dataService = dataService;
	}

	@Override
	public BaseResponse insertMessage(List<MessageRequest> insertMessageRequestList, LogFlag logFlag) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			List<Message> messageList = new ArrayList<Message>();
			Message message = null;
			if (null != insertMessageRequestList && insertMessageRequestList.size() > 0) {
				for (MessageRequest insertMessageRequest: insertMessageRequestList) {
					message = new Message();
					message.setMsgID("");
					message.setFromID(insertMessageRequest.getFromMemberID());
					message.setToID(insertMessageRequest.getToMemberID());
					message.setContent(insertMessageRequest.getContent());
					message.setSend(insertMessageRequest.isSend());
					message.setDateTime(insertMessageRequest.getDateTime());
					messageList.add(message);
				}
			}
			dataService.insertMessage(messageList);
		} catch (Exception e) {
			baseResponse.addError(new ErrorInfo("system error","provider"));
			System.out.println(e.getMessage());
		}
		return baseResponse;
	}

	@Override
	public BaseResponse isSendMessage(List<MessageRequest> isSendMessageRequestList, LogFlag logFlag) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			List<Message> messageList = new ArrayList<Message>();
			Message message = null;
			if (null != isSendMessageRequestList && isSendMessageRequestList.size() > 0) {
				for (MessageRequest isSendMessageRequest: isSendMessageRequestList) {
					message = new Message();
					message.setMsgID("");
					message.setFromID(isSendMessageRequest.getFromMemberID());
					message.setToID(isSendMessageRequest.getToMemberID());
					message.setContent(isSendMessageRequest.getContent());
					message.setSend(isSendMessageRequest.isSend());
					message.setDateTime(isSendMessageRequest.getDateTime());
				}
			}
			dataService.insertMessage(messageList);
		} catch (Exception e) {
			baseResponse.addError(new ErrorInfo("system error","provider"));
			System.out.println(e.getMessage());
		}
		return baseResponse;
	}

}
