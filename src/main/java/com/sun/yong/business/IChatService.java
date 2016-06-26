package com.sun.yong.business;

import java.util.List;

import com.sun.yong.common.entity.common.BaseResponse;
import com.sun.yong.common.entity.common.LogFlag;
import com.sun.yong.common.entity.request.CreateChatRequest;
import com.sun.yong.common.entity.request.MessageRequest;
import com.sun.yong.common.entity.response.CreateChatResponse;

public interface IChatService {

	CreateChatResponse createChat(final CreateChatRequest createChatRequest, final LogFlag logFlag);

	BaseResponse insertMessage(final List<MessageRequest> insertMessageRequestList, final LogFlag logFlag);

	BaseResponse isSendMessage(final List<MessageRequest> isSendMessageRequestList, final LogFlag logFlag);
}
