package com.aowin.util;

/**
 * 关键字转sql语句的util
 * 
 * @author Peter
 *
 */
public class TransToSqlUtil {

	private TransToSqlUtil() {
	}

	/**
	 * 用户模糊查询
	 */
	public static String transToSql(String account, String name, String startTime, String endTime, String status) {
		StringBuilder str = new StringBuilder();
		if (!"".equals(account)) {
			str.append("account like '%");
			str.append(account);
			str.append("%' and ");
		}
		if (!"".equals(name)) {
			str.append("name like '%");
			str.append(name);
			str.append("%' and ");
		}
		if (!"".equals(startTime)) {
			str.append("createdate >= '");
			str.append(startTime);
			str.append("' and ");
		}
		if (!"".equals(endTime)) {
			str.append("createdate <= '");
			str.append(endTime);
			str.append("' and ");
		}
		if (!"".equals(status) && status != null) {
			if (!"2".equals(status)) {
				str.append("status = '");
				str.append(status);
				str.append("' and ");
			}
		}
		return str.length() == 0 ? str.toString() + "limit ?,?"
				: "where " + str.toString().substring(0, str.length() - 4) + "limit ?,?";
	}

	/**
	 * 根据付款方式获得sql拼接语句
	 */
	public static String transToSql(String payType) {
		return "".equals(payType) ? " limit ?,?" : "payType= " + payType + " limit ?,?";
	}

	@SuppressWarnings("unlikely-arg-type")
	public static String transToSql(String orderId, String startTime, String endTime, int payType, String type) {
		StringBuilder str = new StringBuilder();
		// 销售单
		if ("1".equals(type)) {
			str.append("where p.ordercode=s.soid and ");
		} else {
			str.append("where p.ordercode=s.poid and ");
		}
		if (!"".equals(orderId)) {
			str.append("p.ordercode like '%");
			str.append(orderId);
			str.append("%' and ");
		}
		if (!"".equals(startTime)) {
			str.append("p.pay_time >= '");
			str.append(startTime);
			str.append("' and ");
		}
		if (!"".equals(endTime)) {
			str.append("p.pay_time <= '");
			str.append(endTime);
			str.append("' and ");
		}
		if (!"".equals(payType)) {
			if (payType != 0) {
				str.append("p.pay_type = '");
				str.append(payType);
				str.append("' and ");
			}
		}
		return str.toString().substring(0, str.length() - 4) + "limit ?,?";
	}

	/**
	 * 根据year 、month导出sql语句
	 */
	public static String transToSql(int year, int month) {
		StringBuilder sql = new StringBuilder("between ");
		if (month < 9) {
			sql.append("'");
			sql.append(year);
			sql.append("-0");
			sql.append(month);
			sql.append("'");
			sql.append(" and ");
			sql.append("'");
			sql.append(year);
			sql.append("-0");
			sql.append(month + 1);
			sql.append("'");
		}else if (month == 9) {
			sql.append("'");
			sql.append(year);
			sql.append("-09");
			sql.append("'");
			sql.append(" and ");
			sql.append("'");
			sql.append(year);
			sql.append("-10");
			sql.append("'");
		}else if (month <= 11) {
			sql.append("'");
			sql.append(year);
			sql.append("-");
			sql.append(month);
			sql.append("'");
			sql.append(" and ");
			sql.append("'");
			sql.append(year);
			sql.append("-");
			sql.append(month + 1);
			sql.append("'");
		}else if (month == 12) {
			sql.append("'");
			sql.append(year);
			sql.append("-");
			sql.append(month);
			sql.append("'");
			sql.append(" and ");
			sql.append("'");
			sql.append(year + 1);
			sql.append("-01");
			sql.append("'");
		}
		return sql.toString();
	}
	/**
	 * 根据year 、month导出sql语句 产品库存报表
	 */
	public static String transToSql(int year, int month, String nowDate) {
		StringBuilder sql = new StringBuilder("between ");
		if (month < 9) {
			sql.append("'");
			sql.append(year);
			sql.append("-0");
			sql.append(month + 1);
			sql.append("'");
			sql.append(" and ");
			sql.append("'");
			sql.append(nowDate);
			sql.append("'");
		}else if (month == 9) {
			sql.append("'");
			sql.append(year);
			sql.append("-10");
			sql.append("'");
			sql.append(" and ");
			sql.append("'");
			sql.append(nowDate);
			sql.append("'");
		}else if (month <= 11) {
			sql.append("'");
			sql.append(year);
			sql.append("-");
			sql.append(month + 1);
			sql.append("'");
			sql.append(" and ");
			sql.append("'");
			sql.append(nowDate);
			sql.append("'");
		}else if (month == 12) {
			sql.append("'");
			sql.append(year + 1);
			sql.append("-01");
			sql.append("'");
			sql.append(" and ");
			sql.append("'");
			sql.append(nowDate);
			sql.append("'");
		}
		return sql.toString();
	}
}
