package com.sun.yong.chendu;

public class MessageEventRequest {
	
	private String fromMemberID;
	
	private String toMemberID;
	
	private String content;
	
	private boolean isSend;

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
}
