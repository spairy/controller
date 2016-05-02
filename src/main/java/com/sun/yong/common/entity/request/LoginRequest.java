package com.sun.yong.common.entity.request;

import java.io.Serializable;

public class LoginRequest implements Serializable {

	private static final long serialVersionUID = -1794703130032410042L;

	private String memberID;
	
	private String username;
	
	private String password;

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
