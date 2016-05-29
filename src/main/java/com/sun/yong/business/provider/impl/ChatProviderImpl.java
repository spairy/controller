package com.sun.yong.business.provider.impl;

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
	public BaseResponse insertMessage(MessageRequest insertMessageRequest, LogFlag logFlag) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			Message message = new Message();
			message.setMsgID("");
			message.setFromID(insertMessageRequest.getFromMemberID());
			message.setToID(insertMessageRequest.getToMemberID());
			message.setContent(insertMessageRequest.getContent());
			message.setSend(insertMessageRequest.isSend());
			message.setDateTime(insertMessageRequest.getDateTime());
			dataService.insertMessage(message);
		} catch (Exception e) {
			baseResponse.addError(new ErrorInfo("system error","provider"));
			System.out.println(e.getMessage());
		}
		return baseResponse;
	}

	@Override
	public BaseResponse isSendMessage(MessageRequest isSendMessageRequest, LogFlag logFlag) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			Message message = new Message();
			message.setMsgID("");
			message.setFromID(isSendMessageRequest.getFromMemberID());
			message.setToID(isSendMessageRequest.getToMemberID());
			message.setContent(isSendMessageRequest.getContent());
			message.setSend(isSendMessageRequest.isSend());
			message.setDateTime(isSendMessageRequest.getDateTime());
			dataService.insertMessage(message);
		} catch (Exception e) {
			baseResponse.addError(new ErrorInfo("system error","provider"));
			System.out.println(e.getMessage());
		}
		return baseResponse;
	}

}
