package com.sun.yong.dataservice.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sun.yong.common.entity.common.Identity;
import com.sun.yong.common.entity.model.UserInfo;

public class UserInfoRowMapper implements RowMapper<UserInfo> {

	@Override
	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserInfo userInfo = new UserInfo();
		userInfo.setMemberID(rs.getString("memberID"));
		userInfo.setUsername(rs.getString("username"));
		userInfo.setPassword(rs.getString("password"));
		userInfo.setSurname(rs.getString("surname"));
		userInfo.setName(rs.getString("name"));
		userInfo.setBirthYear(rs.getString("birthYear"));
		userInfo.setBirthMonth(rs.getString("birthMonth"));
		userInfo.setBirthDay(rs.getString("birthDay"));
		userInfo.setIdentity(Identity.getIdentity(rs.getString("identity")));
		return userInfo;
	}

}
