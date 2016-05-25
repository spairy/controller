package com.sun.yong.common.utils;

import java.util.UUID;

import com.sun.yong.common.entity.common.LogFlag;

public class LogUtils {

	public static LogFlag getLogFlag() {
		LogFlag logIndex = new LogFlag();
		logIndex.setSerialNumber(UUID.randomUUID().toString());
		return logIndex;
	}
}
