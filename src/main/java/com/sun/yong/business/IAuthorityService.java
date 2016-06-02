package com.sun.yong.business;

import com.sun.yong.common.entity.common.LogFlag;
import com.sun.yong.common.entity.request.LoginRequest;
import com.sun.yong.common.entity.response.LoginResponse;
import com.sun.yong.common.entity.response.UserResponse;

public interface IAuthorityService {

	LoginResponse login(final LoginRequest loginRequest, final LogFlag logFlag);
	
	UserResponse getUser(final String memberId, final LogFlag logFlag);
}
