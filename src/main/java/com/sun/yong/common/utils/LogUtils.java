package com.sun.yong.common.utils;

import java.util.UUID;

import com.sun.yong.common.entity.common.LogIndex;

public class LogUtils {

	public static LogIndex getLogIndex() {
		LogIndex logIndex = new LogIndex();
		logIndex.setSerialNumber(UUID.randomUUID().toString());
		return logIndex;
	}
}
