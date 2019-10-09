package com.aowin.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aowin.dao.finance.PayRecordDao;
import com.aowin.dao.purchase.PomainDao;
import com.aowin.dao.sale.SomainDao;
import com.aowin.model.Page;
import com.aowin.model.PayRecord;
import com.aowin.util.DBUtil;

/**
 * 月度收支报表的service
 * @author Peter
 *
 */
public class FinanceReportService {
	/**
	 * 单例
	 */
	private FinanceReportService() {}
	private static FinanceReportService financeReportService = new FinanceReportService();
	public static FinanceReportService getInstance() {
		return financeReportService;
	}
	private Connection conn;
	/**
	 * 查询月度收款的总明细数
	 * @throws SQLException 
	 */
	public ArrayList<PayRecord> findAllFinanceReportOfProceeds(String con) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			return PayRecordDao.getInstance().findAllFinanceReportOfProceeds(con);
		} finally {
			conn.close();
		}
	}
	/**
	 * 查询月度付款的总明细数
	 * @throws SQLException 
	 */
	public ArrayList<PayRecord> findAllFinanceReportOfPayment(String con) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			return PayRecordDao.getInstance().findAllFinanceReportOfPayment(con);
		} finally {
			conn.close();
		}
	}
	/**
	 * 查询月度收款单相关信息
	 * @throws SQLException 
	 */
	public Page findSomainReport(Page page, String con) throws SQLException {
		conn = DBUtil.getConnection();
		try {
			SomainDao.getInstance().findSomainReport(page,con);
			return page;
		} finally {
			conn.close();
		}
	}
	/**
	 * 查询月度收款单总笔数
	 * @throws SQLException 
	 */
	public Page findAllSomainCount(Page page, String con) throws SQLException {
		conn = DBUtil.getConnection();
		try {
			SomainDao.getInstance().findAllSomainCount(page,con);
			return page;
		} finally {
			conn.close();
		}
	}
	/**
	 * 查询月度付款单相关信息
	 * @throws SQLException 
	 */
	public Page findPomainReport(Page page, String con) throws SQLException {
		conn = DBUtil.getConnection();
		try {
			PomainDao.getInstance().findPomainReport(page,con);
			return page;
		} finally {
			conn.close();
		}
	}
	/**
	 * 查询月度付款单总笔数
	 * @throws SQLException 
	 */
	public Page findAllPomainCount(Page page, String con) throws SQLException {
		conn = DBUtil.getConnection();
		try {
			PomainDao.getInstance().findAllPomainCount(page,con);
			return page;
		} finally {
			conn.close();
		}
	}
}
