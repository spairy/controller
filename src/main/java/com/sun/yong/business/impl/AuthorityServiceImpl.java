package com.sun.yong.business.impl;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.sun.yong.business.IAuthorityService;
import com.sun.yong.business.provider.IAuthorityProvider;
import com.sun.yong.common.entity.common.ErrorInfo;
import com.sun.yong.common.entity.common.LogFlag;
import com.sun.yong.common.entity.request.LoginRequest;
import com.sun.yong.common.entity.response.LoginResponse;
import com.sun.yong.common.entity.response.UserResponse;

public class AuthorityServiceImpl implements IAuthorityService {

	private IAuthorityProvider authorityProvider;
	
	public void setAuthorityProvider(IAuthorityProvider authorityProvider) {
		this.authorityProvider = authorityProvider;
	}

	@Override
	public LoginResponse login(final LoginRequest loginRequest, final LogFlag logFlag) {
		LoginResponse loginResponse = null; 
		if (StringUtils.isEmpty(loginRequest.getUsername())) {
			if (null == loginResponse) {
				loginResponse = new LoginResponse();
			}
			loginResponse.addError(new ErrorInfo("empty","username"));
		}
		if (StringUtils.isEmpty(loginRequest.getPassword())) {
			if (null == loginResponse) {
				loginResponse = new LoginResponse();
			}
			loginResponse.addError(new ErrorInfo("empty","password"));
		}
		if (loginResponse == null || CollectionUtils.isEmpty(loginResponse.getErrors())) {
			loginResponse = authorityProvider.login(loginRequest, logFlag);
		}
		return loginResponse;
	}
	
	@Override
	public LoginResponse enroll(final LoginRequest loginRequest, final LogFlag logFlag) {
		LoginResponse loginResponse = null; 
		if (StringUtils.isEmpty(loginRequest.getUsername())) {
			if (null == loginResponse) {
				loginResponse = new LoginResponse();
			}
			loginResponse.addError(new ErrorInfo("empty","username"));
		}
		if (StringUtils.isEmpty(loginRequest.getPassword())) {
			if (null == loginResponse) {
				loginResponse = new LoginResponse();
			}
			loginResponse.addError(new ErrorInfo("empty","password"));
		}
		if (loginResponse == null || CollectionUtils.isEmpty(loginResponse.getErrors())) {
			loginResponse = authorityProvider.enroll(loginRequest, logFlag);
		}
		return loginResponse;
	}

	@Override
	public UserResponse getUser(String memberId, LogFlag logFlag) {
		UserResponse userResponse = null; 
		if (StringUtils.isEmpty(memberId)) {
			if (null == userResponse) {
				userResponse = new UserResponse();
			}
			userResponse.addError(new ErrorInfo("empty","memberId"));
		}
		if (userResponse == null || CollectionUtils.isEmpty(userResponse.getErrors())) {
			userResponse = authorityProvider.getUser(memberId, logFlag);
		}
		return userResponse;
	}

}
