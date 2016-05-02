package com.sun.yong.dataservice.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import com.sun.yong.common.entity.model.UserInfo;
import com.sun.yong.common.exception.DateServiceException;
import com.sun.yong.dataservice.IDataServiceSpringJDBC;
import com.sun.yong.dataservice.mapper.UserInfoRowMapper;

public class DataServiceSpringJDBCImpl implements IDataServiceSpringJDBC {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final static String SQL_GET_USERINFO_BY_USERNAME = 
			"SELECT memberID, username, password, surname, name, birthYear, birthMonth, birthDay, identity From user Where username = ?";
	
	public UserInfo getUserInfoByUserName(String username) throws DateServiceException {
		UserInfo userInfo = null;
		List<UserInfo> userInfoList = jdbcTemplate.query(SQL_GET_USERINFO_BY_USERNAME, new Object[]{username}, new UserInfoRowMapper());
		if (!CollectionUtils.isEmpty(userInfoList)) {
			userInfo = userInfoList.get(0);
		}
		return userInfo;
	}

}
