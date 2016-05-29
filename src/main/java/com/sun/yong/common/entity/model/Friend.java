package com.sun.yong.common.entity.model;

public class Friend {

	private String friendID;
	
	private String memberID;
	
	private String friendMemberID;
	
	private String friendGroup;
	
	private String friendLevel;

	public String getFriendID() {
		return friendID;
	}

	public void setFriendID(String friendID) {
		this.friendID = friendID;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getFriendMemberID() {
		return friendMemberID;
	}

	public void setFriendMemberID(String friendMemberID) {
		this.friendMemberID = friendMemberID;
	}

	public String getFriendGroup() {
		return friendGroup;
	}

	public void setFriendGroup(String friendGroup) {
		this.friendGroup = friendGroup;
	}

	public String getFriendLevel() {
		return friendLevel;
	}

	public void setFriendLevel(String friendLevel) {
		this.friendLevel = friendLevel;
	}
}
