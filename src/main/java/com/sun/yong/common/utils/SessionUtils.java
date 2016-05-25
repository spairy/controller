package com.sun.yong.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.yong.common.Constant;
import com.sun.yong.common.entity.common.UserSession;

public class SessionUtils {

    public static void setUserSession(final HttpServletRequest request, final HttpServletResponse response,
    		final UserSession userSession) {
    	System.out.print("set user session");
        final HttpSession session = request.getSession(true);
        synchronized (session) {
            session.setAttribute(Constant.USER_SESSION_KEY, userSession);
            session.setAttribute(Constant.USER_SESSION_TIME_KEY, System.currentTimeMillis());
        }
    }
    
    public static UserSession getUserSession(final HttpServletRequest request, final HttpServletResponse response) {
    	System.out.print("get user session");
    	UserSession userSession = null;
        final HttpSession session = request.getSession(true);
        if (null != session) {
        	userSession = (UserSession)session.getAttribute(Constant.USER_SESSION_KEY);
        }
        return userSession;
    }
}
