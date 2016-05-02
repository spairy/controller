package com.sun.yong.dataservice.demo;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.sun.yong.common.entity.model.UserInfo;
import com.sun.yong.common.exception.DateServiceException;
import com.sun.yong.dataservice.IDataServiceSpringJDBC;

public class DemoOne extends JdbcDaoSupport implements IDataServiceSpringJDBC {

	@Override
	public UserInfo getUserInfoByUserName(String username) throws DateServiceException {
		this.getJdbcTemplate().execute("");
		return null;
	}
}
