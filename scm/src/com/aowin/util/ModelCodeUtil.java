package com.aowin.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户权限的转换工具
 * @author Peter
 *
 */
public class ModelCodeUtil {

	private ModelCodeUtil() {}
	
	/**
	 * 权限String数组 转List
	 */
	public static ArrayList<Integer> getModelCodeList(String[] modelCodes) {
		ArrayList<Integer> list = new ArrayList<Integer>(6);
		if ("1".equals(modelCodes[0])) {
			for (int i = 1; i <= 6; i++) {
				list.add(i);
			}
		}else {
			for (String mc : modelCodes) {
				list.add(Integer.parseInt(mc));
			}
		}
		return list;
	}
	/**
	 * modelcode 数字转中文
	 */
	public static String getModeNames(String str) {
		switch (str) {
			case "1":
				str="系统管理";
				break;
			case "2":
				str="采购";
				break;
			case "3":
				str="仓管";
				break;
			case "4":
				str="财务";
				break;
			case "5":
				str="销售";
				break;
			case "6":
				str="报表";
				break;
			default:
				break;
			}
		return str;
	}
	/**
	 * 判断用户是否具备该路径的访问权限
	 */
	public static boolean isPermited(List<String> list, String pattern) {
		for (String string : list) {
			if (string.contains(pattern)) {
				return true;
			}
		}
		return false;
	}
}
