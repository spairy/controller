package com.sun.yong.business;

import com.sun.yong.common.entity.common.LogFlag;
import com.sun.yong.common.entity.request.CreateChatRequest;
import com.sun.yong.common.entity.response.CreateChatResponse;

public interface IChatService {

	CreateChatResponse createChat(final CreateChatRequest createChatRequest, final LogFlag logFlag);
}
