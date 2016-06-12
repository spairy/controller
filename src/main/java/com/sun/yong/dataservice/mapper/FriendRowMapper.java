package com.sun.yong.dataservice.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sun.yong.common.entity.model.Friend;

public class FriendRowMapper implements RowMapper<Friend> {

	@Override
	public Friend mapRow(ResultSet rs, int rowNum) throws SQLException {
		Friend friend = new Friend();
		friend.setFriendID(rs.getString("friend_id"));
		friend.setMemberID(rs.getString("member_id"));
		friend.setFriendMemberID(rs.getString("friend_member_id"));
		friend.setFriendGroup(rs.getString("friend_group"));
		friend.setFriendLevel(rs.getString("friend_level"));
		return friend;
	}

}
