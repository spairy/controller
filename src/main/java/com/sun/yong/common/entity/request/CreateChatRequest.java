package com.sun.yong.common.entity.request;

import java.io.Serializable;


public class CreateChatRequest implements Serializable {

	private static final long serialVersionUID = -8701993397334152619L;

	private String memberID;

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
}
