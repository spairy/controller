package com.sun.yong.dataservice;

import com.sun.yong.common.entity.model.Message;
import com.sun.yong.common.entity.model.UserInfo;
import com.sun.yong.common.exception.DateServiceException;

public interface IDataServiceSpringJDBC {

	UserInfo getUserInfoByUserName(String username) throws DateServiceException;
	
	void insertMessage(Message message) throws DateServiceException;
	
	void isSendMessage(Message message) throws DateServiceException;
}
