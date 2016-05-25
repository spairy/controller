package com.sun.yong.business.impl;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.sun.yong.business.IChatService;
import com.sun.yong.common.entity.common.ErrorInfo;
import com.sun.yong.common.entity.common.LogFlag;
import com.sun.yong.common.entity.request.CreateChatRequest;
import com.sun.yong.common.entity.response.CreateChatResponse;

public class ChatServiceImpl implements IChatService {

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

}
