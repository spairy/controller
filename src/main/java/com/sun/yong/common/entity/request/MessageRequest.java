package com.sun.yong.common.entity.request;

import java.io.Serializable;

public class MessageRequest implements Serializable {

	private static final long serialVersionUID = 8392541916867444025L;

	private String msgID;
	
	private String fromMemberID;
	
	private String toMemberID;
	
	private String content;
	
	private boolean isSend;
	
	private String dateTime;

	public String getMsgID() {
		return msgID;
	}

	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}

	public String getFromMemberID() {
		return fromMemberID;
	}

	public void setFromMemberID(String fromMemberID) {
		this.fromMemberID = fromMemberID;
	}

	public String getToMemberID() {
		return toMemberID;
	}

	public void setToMemberID(String toMemberID) {
		this.toMemberID = toMemberID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isSend() {
		return isSend;
	}

	public void setSend(boolean isSend) {
		this.isSend = isSend;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
