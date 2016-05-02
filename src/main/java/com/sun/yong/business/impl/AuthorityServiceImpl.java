package com.sun.yong.business.impl;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.sun.yong.business.IAuthorityService;
import com.sun.yong.business.provider.IAuthorityProvider;
import com.sun.yong.common.entity.common.ErrorInfo;
import com.sun.yong.common.entity.common.LogIndex;
import com.sun.yong.common.entity.request.LoginRequest;
import com.sun.yong.common.entity.response.LoginResponse;

public class AuthorityServiceImpl implements IAuthorityService {

	private IAuthorityProvider authorityProvider;
	
	public void setAuthorityProvider(IAuthorityProvider authorityProvider) {
		this.authorityProvider = authorityProvider;
	}

	@Override
	public LoginResponse login(final LoginRequest loginRequest, final LogIndex logInfo) {
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
			loginResponse = authorityProvider.login(loginRequest, logInfo);
		}
		return loginResponse;
	}

}
