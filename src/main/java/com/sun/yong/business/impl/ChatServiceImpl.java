package com.sun.yong.business.impl;

import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.sun.yong.business.IChatService;
import com.sun.yong.business.provider.IChatProvider;
import com.sun.yong.common.entity.common.BaseResponse;
import com.sun.yong.common.entity.common.ErrorInfo;
import com.sun.yong.common.entity.common.LogFlag;
import com.sun.yong.common.entity.request.CreateChatRequest;
import com.sun.yong.common.entity.request.MessageRequest;
import com.sun.yong.common.entity.response.CreateChatResponse;

public class ChatServiceImpl implements IChatService {

	private IChatProvider chatProvider;
	
	public void setChatProvider(IChatProvider chatProvider) {
		this.chatProvider = chatProvider;
	}

	@Override
	public CreateChatResponse createChat(CreateChatRequest createChatRequest,
			LogFlag logFlag) {
		CreateChatResponse createChatResponse = null;
		if (StringUtils.isEmpty(createChatRequest.getMemberID())) {
			if (null == createChatResponse) {
				createChatResponse = new CreateChatResponse();
			}
			createChatResponse.addError(new ErrorInfo("empty","memberID"));
		}
		if (createChatResponse == null || CollectionUtils.isEmpty(createChatResponse.getErrors())) {
			//createChatResponse = authorityProvider.login(loginRequest, logFlag);
		}
		return createChatResponse;
	}

	@Override
	public BaseResponse insertMessage(List<MessageRequest> insertMessageRequestList, LogFlag logFlag) {
		BaseResponse baseResponse = null; 
		if (null == insertMessageRequestList || insertMessageRequestList.size() <= 0 ) {
			if (null == baseResponse) {
				baseResponse = new BaseResponse();
			}
			baseResponse.addError(new ErrorInfo("empty","insert Message Request List"));
		} else {
			for (MessageRequest insertMessageRequest: insertMessageRequestList) {
				if (StringUtils.isEmpty(insertMessageRequest.getFromMemberID())) {
					if (null == baseResponse) {
						baseResponse = new BaseResponse();
					}
					baseResponse.addError(new ErrorInfo("empty","formMemberID"));
				}
				if (StringUtils.isEmpty(insertMessageRequest.getToMemberID())) {
					if (null == baseResponse) {
						baseResponse = new BaseResponse();
					}
					baseResponse.addError(new ErrorInfo("empty","toMemberID"));
				}
			}
		}
		if (baseResponse == null || CollectionUtils.isEmpty(baseResponse.getErrors())) {
			baseResponse = chatProvider.insertMessage(insertMessageRequestList, logFlag);
		}
		return baseResponse;
	}

	@Override
	public BaseResponse isSendMessage(final List<MessageRequest> isSendMessageRequestList, LogFlag logFlag) {
		BaseResponse baseResponse = null; 
		if (null == isSendMessageRequestList || isSendMessageRequestList.size() <= 0 ) {
			if (null == baseResponse) {
				baseResponse = new BaseResponse();
			}
			baseResponse.addError(new ErrorInfo("empty","isSend Message Request List"));
		} else {
			for (MessageRequest isSendMessageRequest: isSendMessageRequestList) {
				if (StringUtils.isEmpty(isSendMessageRequest.getFromMemberID())) {
					if (null == baseResponse) {
						baseResponse = new BaseResponse();
					}
					baseResponse.addError(new ErrorInfo("empty","formMemberID"));
				}
				if (StringUtils.isEmpty(isSendMessageRequest.getToMemberID())) {
					if (null == baseResponse) {
						baseResponse = new BaseResponse();
					}
					baseResponse.addError(new ErrorInfo("empty","toMemberID"));
				}
			}
		}
		if (baseResponse == null || CollectionUtils.isEmpty(baseResponse.getErrors())) {
			baseResponse = chatProvider.isSendMessage(isSendMessageRequestList, logFlag);
		}
		return baseResponse;
	}

}
