package com.sun.yong.business;

import com.sun.yong.common.entity.common.LogFlag;
import com.sun.yong.common.entity.request.LoginRequest;
import com.sun.yong.common.entity.response.LoginResponse;

public interface IAuthorityService {

	LoginResponse login(final LoginRequest loginRequest, final LogFlag logFlag);
}
