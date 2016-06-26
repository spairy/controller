package com.sun.yong.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.yong.business.IAuthorityService;
import com.sun.yong.common.entity.common.BaseResponse;
import com.sun.yong.common.entity.common.ErrorEnum;
import com.sun.yong.common.entity.common.ErrorInfo;
import com.sun.yong.common.entity.common.LogFlag;
import com.sun.yong.common.entity.common.UserSession;
import com.sun.yong.common.entity.request.LoginRequest;
import com.sun.yong.common.entity.response.LoginResponse;
import com.sun.yong.common.utils.LogUtils;
import com.sun.yong.common.utils.SessionUtils;

@RequestMapping(value = "/auth")
public class AuthorityController extends BaseController {

	private IAuthorityService authorityService;
	
	public void setAuthorityService(IAuthorityService authorityService) {
		this.authorityService = authorityService;
	}

/*	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public LoginResponse login(HttpServletRequest request, HttpServletResponse responset) {
		System.out.print("login get");
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername(StringUtils.trimWhitespace(request.getParameter("username")));
		loginRequest.setPassword(StringUtils.trimWhitespace(request.getParameter("password")));
		LoginResponse loginResponse = null;
		LogIndex logIndex = LogUtils.getLogIndex();
		loginResponse = authorityService.login(loginRequest, logIndex);
		return loginResponse;
	}*/
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponse login(HttpServletRequest request, HttpServletResponse response,
			@RequestBody LoginRequest loginRequest) {
		System.out.println("login post");
		LoginResponse loginResponse = null;
		LogFlag logIndex = LogUtils.getLogFlag();
		loginResponse = authorityService.login(loginRequest, logIndex);
		UserSession userSession = createUserSession(loginResponse);
		SessionUtils.setUserSession(request, response, userSession);
		return loginResponse;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse logout(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("logout");
		BaseResponse baseResponse = null;
		try {
			//LogFlag logIndex = LogUtils.getLogFlag();
			SessionUtils.removeUserSession(request);
			baseResponse = new BaseResponse();
		} catch (Exception e) {
			baseResponse = new BaseResponse();
			baseResponse.addError(new ErrorInfo(ErrorEnum.ERR_SYS_001.toString()));
		}
		return baseResponse;
	}
	
	@RequestMapping(value = "/enroll", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponse enroll(HttpServletRequest request, HttpServletResponse response,
			@RequestBody LoginRequest loginRequest) {
		System.out.println("enroll ");
		LoginResponse loginResponse = null;
		LogFlag logIndex = LogUtils.getLogFlag();
		loginResponse = authorityService.enroll(loginRequest, logIndex);
		UserSession userSession = createUserSession(loginResponse);
		SessionUtils.setUserSession(request, response, userSession);
		return loginResponse;
	}
	
	@RequestMapping(value = "/getSession", method = RequestMethod.GET)
	@ResponseBody
	public LoginResponse getSession(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("getSession");
		LoginResponse loginResponse = new LoginResponse();
		UserSession userSession = SessionUtils.getUserSession(request, response);
		if (null == userSession) {
			loginResponse.addError(new ErrorInfo(ErrorEnum.ERR_SYS_002.toString()));
		} else {
			loginResponse.setMemberID(userSession.getMemberID());
			loginResponse.setUsername(userSession.getUsername());
			loginResponse.setSurname(userSession.getSurname());
			loginResponse.setName(userSession.getName());
			loginResponse.setBirthYear(userSession.getBirthYear());
			loginResponse.setBirthMonth(userSession.getBirthMonth());
			loginResponse.setBirthDay(userSession.getBirthDay());
			loginResponse.setIdentity(userSession.getIdentity());
		}
		return loginResponse;
	}
	
	public UserSession createUserSession(final LoginResponse loginResponse) {
		UserSession userSession = new UserSession();
		userSession.setMemberID(loginResponse.getMemberID());
		userSession.setUsername(loginResponse.getUsername());
		userSession.setSurname(loginResponse.getSurname());
		userSession.setName(loginResponse.getName());
		userSession.setBirthYear(loginResponse.getBirthYear());
		userSession.setBirthMonth(loginResponse.getBirthMonth());
		userSession.setBirthDay(loginResponse.getBirthDay());
		userSession.setIdentity(loginResponse.getIdentity());
		return userSession;
	}
	
}
