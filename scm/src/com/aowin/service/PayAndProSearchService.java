package com.aowin.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.aowin.dao.finance.PayRecordDao;
import com.aowin.model.Page;
import com.aowin.util.DBUtil;

/**
 * 收支查询
 * @author Peter
 *
 */
public class PayAndProSearchService {
	/**
	 * 单例
	 */
	private PayAndProSearchService() {}
	private static PayAndProSearchService payAndProSearchService = new PayAndProSearchService();
	public static PayAndProSearchService getInstance() {
		return payAndProSearchService;
	}
	private Connection conn;
	/**
	 * 查询收款
	 * somain
	 * payrecord
	 * @throws SQLException 
	 */
	public Page searchProceeds(Page page, String con) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			PayRecordDao.getInstance().searchProceeds(page, con);
			PayRecordDao.getInstance().findAllProceedsCount(page, con);
			return page;
		} finally {
			conn.close();
		}
	}
	/**
	 * 查询付款
	 */
	public Page searchPayment(Page page, String con) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			PayRecordDao.getInstance().searchPayment(page, con);
			PayRecordDao.getInstance().findAllPaymentCount(page, con);
			return page;
		} finally {
			conn.close();
		}
	}
}
