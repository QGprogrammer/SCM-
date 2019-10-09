package com.aowin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期有关的util
 * @author Peter
 *
 */
public class DateUtil {

	/**
	 * 获得时间字符串
	 * @param date
	 * @return
	 */
	public static String getDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime = sdf.format(date);
		return dateTime;
	}
	/**
	 * 时间编码
	 * @param date
	 * @return
	 */
	public static String getDateCode(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateTime = sdf.format(date);
		return dateTime;
	}
}
