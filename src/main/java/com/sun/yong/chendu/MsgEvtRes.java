package com.sun.yong.chendu;

import java.io.Serializable;
import java.util.List;

import com.sun.yong.common.entity.model.Friend;

public class MsgEvtRes implements Serializable {

	private static final long serialVersionUID = -8807685183591419324L;

	private String messageType;
	
	private List<MsgEvt> msgEvtList;
	
	private List<Friend> friendList;

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public List<MsgEvt> getMsgEvtList() {
		return msgEvtList;
	}

	public void setMsgEvtList(List<MsgEvt> msgEvtList) {
		this.msgEvtList = msgEvtList;
	}

	public List<Friend> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<Friend> friendList) {
		this.friendList = friendList;
	}
}
