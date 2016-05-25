package com.sun.yong.business.provider;

import com.sun.yong.common.entity.common.LogFlag;
import com.sun.yong.common.entity.request.LoginRequest;
import com.sun.yong.common.entity.response.LoginResponse;

public interface IAuthorityProvider {

	LoginResponse login(final LoginRequest loginRequest, final LogFlag logFlag);
}
