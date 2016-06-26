package com.sun.yong.common.entity.response;

import java.util.List;

import com.sun.yong.common.entity.common.BaseResponse;
import com.sun.yong.common.entity.model.Friend;

public class FriendResponse extends BaseResponse {

	private static final long serialVersionUID = 6848698549483909283L;

	private List<Friend> friendList;

	public List<Friend> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<Friend> friendList) {
		this.friendList = friendList;
	}
	
}
