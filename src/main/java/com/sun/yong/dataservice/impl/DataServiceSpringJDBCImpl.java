package com.sun.yong.dataservice.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import com.sun.yong.common.entity.model.Message;
import com.sun.yong.common.entity.model.UserInfo;
import com.sun.yong.common.exception.DateServiceException;
import com.sun.yong.dataservice.IDataServiceSpringJDBC;
import com.sun.yong.dataservice.SQLConstant;
import com.sun.yong.dataservice.mapper.UserInfoRowMapper;

public class DataServiceSpringJDBCImpl implements IDataServiceSpringJDBC {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private String getDateBaseLineID() {
		return UUID.randomUUID().toString();
	}
	
	public UserInfo getUserInfoByUserName(String username) throws DateServiceException {
		UserInfo userInfo = null;
		List<UserInfo> userInfoList = 
				jdbcTemplate.query(SQLConstant.SQL_GET_USERINFO_BY_USERNAME, 
						new Object[]{username}, new UserInfoRowMapper());
		if (!CollectionUtils.isEmpty(userInfoList)) {
			userInfo = userInfoList.get(0);
		}
		return userInfo;
	}

	@Override
	public void insertMessage(Message message) throws DateServiceException {
		Object[] o = new Object[] {getDateBaseLineID(),
				message.getFromID(), message.getToID(), message.getContent(), 
				message.isSend(),    message.getDateTime()
		};
		int n = jdbcTemplate.update(SQLConstant.SQL_INSERT_MESSAGE, o);
		if (n <= 0) {
			throw new DateServiceException("n <= 0", "insert fail");
		}
	}

	@Override
	public void isSendMessage(Message message) throws DateServiceException {
		Object[] o = new Object[] {message.isSend(),
				message.getFromID(), message.getToID(), message.getContent(), 
				message.getDateTime()
		};
		int n = jdbcTemplate.update(SQLConstant.SQL_IS_SEND_MESSAGE, o);
		if (n <= 0) {
			throw new DateServiceException("n <= 0", "update fail");
		}
	}

}
