package com.aowin.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.aowin.dao.finance.PayRecordDao;
import com.aowin.dao.purchase.PoitemDao;
import com.aowin.dao.purchase.PomainDao;
import com.aowin.model.Page;
import com.aowin.model.PayRecord;
import com.aowin.model.Poitem;
import com.aowin.model.Pomain;
import com.aowin.util.DBUtil;

/**
 * 付款的service
 * @author Peter
 *
 */
public class PaymentService {
	/**
	 * 单例
	 */
	private PaymentService() {}
	private static PaymentService paymentService = new PaymentService();
	public static PaymentService getInstance() {
		return paymentService;
	}
	private Connection conn;
	/**
	 * 分页查询采购主单
	 */
	public Page findPomain(Page page) throws SQLException{
		try {
			conn = DBUtil.getConnection();
			return PomainDao.getInstance().findPomain(page);
		} finally {
			conn.close();
		}
		
	}
/**
* 查询全部采购单数
* @throws SQLException 
*/
public int findAllCount(String con) throws Exception {
	try {
		conn = DBUtil.getConnection();
		return PomainDao.getInstance().findAllCount(con);
	} finally {
		conn.close();
	}
}	
/**
 * 分付款方式分页查询
 */
public Page findPomain(Page page,String payType) throws SQLException{
	try {
		conn = DBUtil.getConnection();
		return PomainDao.getInstance().findPomain(page, payType);
	} finally {
		conn.close();
	}
}	
/**
* 查询单个采购单(总价、预付款价格、付款方式、经办人)
* @throws SQLException 
*/
	@SuppressWarnings("resource")
	public Pomain searchPomain(Pomain pom) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			return PomainDao.getInstance().searchPomain(pom);
		} finally {
			conn.close();
		}
	}
/**
 * 登记
 * 修改pomain
 * 新增收付款记录单     
 * @throws SQLException 
 */
	@SuppressWarnings("resource")
	public boolean checkin(Pomain pom, PayRecord pr) throws SQLException {
		conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			int rowsS = PomainDao.getInstance().updatePomain(pom);
			int rowsP = PayRecordDao.getInstance().addPayRecord(pr);
			if (rowsS > 0 && rowsP > 0) {
				conn.commit();	
				return true;
			}else {
				conn.rollback();
				return false;
			}
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return false;
	}
	/**
	 * 查询明细单
	 */
	public Poitem searchPoitem(Poitem poi) throws SQLException{
		try {
			conn = DBUtil.getConnection();
			return PoitemDao.getInstance().searchPoitem(poi);
		} finally {
			conn.close();
		}
	}
	public Connection getConnection() {
		return conn;
	}
}
