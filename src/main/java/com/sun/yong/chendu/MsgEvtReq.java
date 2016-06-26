package com.sun.yong.chendu;

import java.io.Serializable;
import java.util.List;

public class MsgEvtReq implements Serializable {
	
	private static final long serialVersionUID = 8670915632868442834L;

	private String msgEvtType;
	
	private String fromMemberID;
	
	private String fromUsername;
	
	private String toMemberID;
	
	private String toUsername;
	
	private List<MsgEvt> msgEvtList;

	public String getMsgEvtType() {
		return msgEvtType;
	}

	public void setMsgEvtType(String msgEvtType) {
		this.msgEvtType = msgEvtType;
	}

	public String getFromMemberID() {
		return fromMemberID;
	}

	public void setFromMemberID(String fromMemberID) {
		this.fromMemberID = fromMemberID;
	}

	public String getFromUsername() {
		return fromUsername;
	}

	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}

	public String getToMemberID() {
		return toMemberID;
	}

	public void setToMemberID(String toMemberID) {
		this.toMemberID = toMemberID;
	}

	public String getToUsername() {
		return toUsername;
	}

	public void setToUsername(String toUsername) {
		this.toUsername = toUsername;
	}

	public List<MsgEvt> getMsgEvtList() {
		return msgEvtList;
	}

	public void setMsgEvtList(List<MsgEvt> msgEvtList) {
		this.msgEvtList = msgEvtList;
	}
}
