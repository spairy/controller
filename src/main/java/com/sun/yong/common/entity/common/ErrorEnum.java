package com.sun.yong.common.entity.common;

public enum ErrorEnum {

	ERR_SYS_000("S000", "Unknow Exception!"),
	ERR_SYS_001("S001", "System Exception!"),
	ERR_SYS_002("S002", "Session Is Empty!");
	
	private String errorCode;
	
	private String errorMessage;
	
	private ErrorEnum(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	public static ErrorEnum getErrorEnum(String errorCode) {
		for (ErrorEnum errorEnum : ErrorEnum.values()) {
			if (errorEnum.getErrorCode().equals(errorCode)) {
				return errorEnum;
			}
		}
		return ERR_SYS_000;
	}
}
