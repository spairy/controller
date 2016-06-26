package com.sun.yong.common.entity.common;

public enum SpecMember {

	UNKNOW("null","NULL"),
	SUNYONG("10000000", "SunYong"),
	SYSTEM("10000001", "SYSTEM");
	
	private String memberID;
	
	private String username;

	private SpecMember(String memberID, String username) {
		this.memberID = memberID;
		this.username = username;
	}
	
	public String getMemberID() {
		return memberID;
	}

	public String getUsername() {
		return username;
	}
	
	public static SpecMember getSpecMember(String memberID) {
		for (SpecMember specMember : SpecMember.values()) {
			if (specMember.getMemberID().equals(memberID)) {
				return specMember;
			}
		}
		return UNKNOW;
	}
}
