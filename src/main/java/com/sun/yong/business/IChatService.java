package com.sun.yong.business;

import com.sun.yong.common.entity.common.BaseResponse;
import com.sun.yong.common.entity.common.LogFlag;
import com.sun.yong.common.entity.request.CreateChatRequest;
import com.sun.yong.common.entity.request.MessageRequest;
import com.sun.yong.common.entity.response.CreateChatResponse;

public interface IChatService {

	CreateChatResponse createChat(final CreateChatRequest createChatRequest, final LogFlag logFlag);

	BaseResponse insertMessage(final MessageRequest insertMessageRequest, final LogFlag logFlag);

	BaseResponse isSendMessage(final MessageRequest isSendMessageRequest, final LogFlag logFlag);
}
