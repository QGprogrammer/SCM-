package com.aowin.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.aowin.dao.purchase.PomainDao;
import com.aowin.model.Pomain;
import com.aowin.util.DBUtil;

/**
 * 采购的service
 * 
 * @author Peter
 *
 */
public class PurchaseService {

	/**
	 * 单例
	 */
	private PurchaseService() {
	}

	private static PurchaseService purchaseService = new PurchaseService();

	public static PurchaseService getInstance() {
		return purchaseService;
	}

	private Connection conn;

	/**
	 * 删除某用户相关销售单
	 * 
	 * @throws SQLException
	 */
	public int deletePomain(Pomain pom) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			return PomainDao.getInstance().deletePomain(pom);
		} finally {
			conn.close();
		}
	}

	/**
	 * 检查某用户是否有相关的采购单
	 * 
	 * @throws SQLException
	 */
	public boolean checkPomain(Pomain pom) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			return PomainDao.getInstance().checkPomain(pom);
		} finally {
			conn.close();
		}
	}
}
