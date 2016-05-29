package com.sun.yong.dataservice;

public class SQLConstant {

	public final static String SQL_GET_USERINFO_BY_USERNAME = 
			"SELECT memberID, username, password, surname, name, birthYear, birthMonth, birthDay, identity From user Where username = ?";
	
	public final static String SQL_INSERT_MESSAGE = 
			"INSERT INTO message (msg_id, from_id, to_id, content, is_send, date_time) VALUES (?, ?, ?, ?, ?, ?)";
	
	public final static String SQL_IS_SEND_MESSAGE = 
			"UPDATE message set is_send = ? where from_id = ? AND to_id = ? AND content = ? AND date_time = ?";
}
