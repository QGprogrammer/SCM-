package com.aowin.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.aowin.dao.sale.SomainDao;
import com.aowin.model.Somain;
import com.aowin.util.DBUtil;

/**
 * 销售单有关的service
 * @author Peter
 *
 */
public class SaleService {
	/**
	 * 单例
	 */
	private SaleService() {}
	private static SaleService saleService = new SaleService();
	public static SaleService getInstance() {
		return saleService;
	}
	private Connection conn;
	
	/**
	 * 删除某用户相关销售单
	 * @throws SQLException 
	 */
	public int deleteSomain(Somain som) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			return SomainDao.getInstance().deleteSomain(som);
		} finally {
			conn.close();
		} 
	}
	/**
	 * 检查某用户是否有相关的销售单
	 * @throws SQLException 
	 */
	public boolean checkSomain(Somain som) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			return SomainDao.getInstance().checkSomain(som);
		} finally {
			conn.close();
		}
	}
}
