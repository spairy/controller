package com.sun.yong.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.yong.business.IChatService;
import com.sun.yong.common.entity.common.ErrorInfo;
import com.sun.yong.common.entity.common.LogFlag;
import com.sun.yong.common.entity.common.UserSession;
import com.sun.yong.common.entity.request.CreateChatRequest;
import com.sun.yong.common.entity.response.CreateChatResponse;
import com.sun.yong.common.utils.LogUtils;
import com.sun.yong.common.utils.SessionUtils;

@RequestMapping(value = "/chatJDK")
public class ChatJDKController extends BaseController {

	private IChatService chatService;
	
	@RequestMapping(value = "/createChat", method = RequestMethod.POST)
	@ResponseBody
	public CreateChatResponse createChat(HttpServletRequest request, HttpServletResponse response,
			@RequestBody CreateChatRequest CreateChatRequest) {
		System.out.println("createChat post");
		LogFlag logFlag = LogUtils.getLogFlag();
		CreateChatResponse createChatResponse = null;
		UserSession userSession = SessionUtils.getUserSession(request, response);
		if (null != userSession) {
			createChatResponse = chatService.createChat(CreateChatRequest, logFlag);
		} else {
			createChatResponse = new CreateChatResponse();
			createChatResponse.addError(new ErrorInfo("not login!"));
		}
		return createChatResponse;
	}
	
	@RequestMapping(value = "/getFriends", method = RequestMethod.POST)
	@ResponseBody
	public CreateChatResponse getFriends(HttpServletRequest request, HttpServletResponse response,
			@RequestBody CreateChatRequest CreateChatRequest) {
		System.out.println("createChat post");
		LogFlag logFlag = LogUtils.getLogFlag();
		CreateChatResponse createChatResponse = null;
		UserSession userSession = SessionUtils.getUserSession(request, response);
		if (null != userSession) {
			createChatResponse = chatService.createChat(CreateChatRequest, logFlag);
		} else {
			createChatResponse = new CreateChatResponse();
			createChatResponse.addError(new ErrorInfo("not login!"));
		}
		return createChatResponse;
	}
}
