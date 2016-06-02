package com.sun.yong.common.entity.response;

import com.sun.yong.common.entity.common.BaseResponse;
import com.sun.yong.common.entity.model.Friend;
import com.sun.yong.common.entity.model.UserInfo;

public class UserResponse extends BaseResponse {

	private static final long serialVersionUID = -4969273171078422327L;

	private UserInfo userInfo;
	
	private Friend friend;

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Friend getFriend() {
		return friend;
	}

	public void setFriend(Friend friend) {
		this.friend = friend;
	}
	
	
}
