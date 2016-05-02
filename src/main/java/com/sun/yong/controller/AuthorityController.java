package com.sun.yong.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.yong.business.IAuthorityService;
import com.sun.yong.common.entity.common.ErrorInfo;
import com.sun.yong.common.entity.common.LogIndex;
import com.sun.yong.common.entity.request.LoginRequest;
import com.sun.yong.common.entity.response.LoginResponse;
import com.sun.yong.common.utils.LogUtils;

@RequestMapping(value = "/auth")
public class AuthorityController extends BaseController {

	private IAuthorityService authorityService;
	
	public void setAuthorityService(IAuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public LoginResponse login(HttpServletRequest request, HttpServletResponse responset) {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername(StringUtils.trimWhitespace(request.getParameter("username")));
		loginRequest.setPassword(StringUtils.trimWhitespace(request.getParameter("password")));
		LoginResponse loginResponse = null;
		LogIndex logIndex = LogUtils.getLogIndex();
		loginResponse = authorityService.login(loginRequest, logIndex);
		return loginResponse;
	}
	
/*	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponse loginPost(HttpServletRequest request, HttpServletResponse responset,
			@RequestBody LoginRequest loginRequest) {
		LoginResponse loginResponse = null;
		
		if (!StringUtils.isEmpty(loginRequest.getUsername()) 
				&& !StringUtils.isEmpty(loginRequest.getPassword())) {
				
			if ("username".equals(loginRequest.getUsername())) {
				
			}
			loginResponse = new LoginResponse();
			ErrorInfo errorInfo = new ErrorInfo();
			loginResponse.addError(errorInfo);
		} else {
			ErrorInfo errorInfo = new ErrorInfo("ERR_SYS_001");
			loginResponse.addError(errorInfo);
		}
		
		loginResponse = new LoginResponse();
		return loginResponse;
	}*/
}
