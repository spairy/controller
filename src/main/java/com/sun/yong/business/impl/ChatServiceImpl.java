package com.sun.yong.business.impl;

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
	public BaseResponse insertMessage(MessageRequest insertMessageRequest, LogFlag logFlag) {
		BaseResponse baseResponse = null; 
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
		if (baseResponse == null || CollectionUtils.isEmpty(baseResponse.getErrors())) {
			baseResponse = chatProvider.insertMessage(insertMessageRequest, logFlag);
		}
		return baseResponse;
	}

	@Override
	public BaseResponse isSendMessage(final MessageRequest isSendMessageRequest, LogFlag logFlag) {
		BaseResponse baseResponse = null; 
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
		if (baseResponse == null || CollectionUtils.isEmpty(baseResponse.getErrors())) {
			baseResponse = chatProvider.isSendMessage(isSendMessageRequest, logFlag);
		}
		return baseResponse;
	}

}
