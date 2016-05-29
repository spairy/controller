package com.sun.yong.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public enum DateFormat {
		
		YYYY_MM_DD_HMS("yyyy-MM-dd HH:mm:ss"),
		YYYY_MM_DD("yyyy-MM-dd");
		
		private String value;
		
		private DateFormat (String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
	
	public static String getCurrentDateTime() {
		return getCurrentDateTime(DateFormat.YYYY_MM_DD_HMS);
	}
	
	public static String getCurrentDateTime(DateFormat dateFormat) {
		SimpleDateFormat df = new SimpleDateFormat(dateFormat.getValue());
		return df.format(new Date());
	}
}
