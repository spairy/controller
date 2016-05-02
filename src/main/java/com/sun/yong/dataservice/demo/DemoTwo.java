package com.sun.yong.dataservice.demo;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sun.yong.common.entity.model.UserInfo;
import com.sun.yong.common.exception.DateServiceException;
import com.sun.yong.dataservice.IDataServiceSpringJDBC;

public class DemoTwo implements IDataServiceSpringJDBC {

	private JdbcTemplate jdbcTemplate;  
	
	@Override
	public UserInfo getUserInfoByUserName(String username) throws DateServiceException {
		jdbcTemplate.execute("");
		return null;
	}  

}
