package com.sun.yong.business.provider;

import java.util.List;

import com.sun.yong.common.entity.common.BaseResponse;
import com.sun.yong.common.entity.common.LogFlag;
import com.sun.yong.common.entity.request.MessageRequest;

public interface IChatProvider {

	BaseResponse insertMessage(final List<MessageRequest> insertMessageRequestList, final LogFlag logFlag);

	BaseResponse isSendMessage(final List<MessageRequest> isSendMessageRequestList, final LogFlag logFlag);
}
