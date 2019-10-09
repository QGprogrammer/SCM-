package com.aowin.util;

import java.util.ArrayList;

import com.aowin.model.PayRecord;

/**
 * 计算工具
 * @author Peter
 *
 */
public class CalculateUtil {
	private CalculateUtil(){}
	/**
	 * 计算收付款款总额
	 */
	public static float sumPayOrPro(ArrayList<PayRecord> list) {
		float sum = 0f;
		for (PayRecord payRecord : list) {
			sum += payRecord.getPayPrice();
		}
		return sum;
	}
}
