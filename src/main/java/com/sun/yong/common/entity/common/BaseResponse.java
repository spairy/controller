package com.sun.yong.common.entity.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseResponse implements Serializable {

	private static final long serialVersionUID = 1345259691244449112L;

	private List<ErrorInfo> errors;

	/**
	 * @return the errors
	 */
	public List<ErrorInfo> getErrors() {
		if(null == errors) {
			errors = new ArrayList<ErrorInfo>();
		}
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(ArrayList<ErrorInfo> errors) {
		this.errors = errors;
	}

	public void addError(ErrorInfo error) {
		if(null == errors) {
			errors = new ArrayList<ErrorInfo>();
		}
		errors.add(error);
	}
}
