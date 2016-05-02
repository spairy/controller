package com.sun.yong.common.exception;

public class BaseException extends Exception {

	private static final long serialVersionUID = 4248370817472917994L;

	protected String errorCode;
	
	protected String errorMessage;

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
