package com.aowin.util;

/**
 * 数据库中的类型代号与实际对应类型转换
 * @author Peter
 *
 */
public class TransTypeUtil {
	private TransTypeUtil() {}
	/**
	 * 库存变化类型转换采购入库1
	 * 销售出库2
	 * 盘点入库3
	 * 盘点出库4
	 */
	public static int getType(String type) {
		switch (type) {
		case "采购入库":
			return 1;
		case "销售出库":
			return 2;
		case "盘点入库":
			return 3;
		case "盘点出库":
			return 4;
		default:
			return 0;
		}
	}
	/**
	 * 盘点盈余或损耗
	 * 盘点入库3
	 * 盘点出库4
	 */
	public static int getCountType(String type) {
		switch (type) {
		case "profit":
			return 3;
		case "waste":
			return 4;
		default:
			return 0;
		}
	}
}
