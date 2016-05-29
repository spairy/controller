package com.sun.yong.business.provider;

import com.sun.yong.common.entity.common.BaseResponse;
import com.sun.yong.common.entity.common.LogFlag;
import com.sun.yong.common.entity.request.MessageRequest;

public interface IChatProvider {

	BaseResponse insertMessage(final MessageRequest messageRequest, final LogFlag logFlag);

	BaseResponse isSendMessage(final MessageRequest messageRequest, final LogFlag logFlag);
}
