package com.sun.yong.common.entity.common;


public enum Identity {

	ADMIN("0", "admin"),
	USER("1", "user"),
	UNKNOW("2", "unknow");
	
	private String code;
	
	private String value;
	
	private Identity(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public String getCode() {
		return code;
	}
	
	public String getValue() {
		return value;
	}
	
	public static Identity getIdentity(String code) {
		for (Identity identity : Identity.values()) {
			if (identity.getCode().equals(code)) {
				return identity;
			}
		}
		return UNKNOW;
	}
}
