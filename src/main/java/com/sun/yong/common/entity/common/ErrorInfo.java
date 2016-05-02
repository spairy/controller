package com.sun.yong.common.entity.common;

import java.io.Serializable;

public class ErrorInfo implements Serializable {

	private static final long serialVersionUID = -460596087076256681L;

	private String errorCode;
	
	private String errorMessage;

	public ErrorInfo() {
	}
	
	public ErrorInfo(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public ErrorInfo(String errorCode, String errorMessage) {
		this(errorCode);
		this.errorMessage = errorMessage;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
