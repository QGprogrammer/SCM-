package com.aowin.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.aowin.dao.finance.PayRecordDao;
import com.aowin.dao.sale.SoitemDao;
import com.aowin.dao.sale.SomainDao;
import com.aowin.model.Page;
import com.aowin.model.Soitem;
import com.aowin.model.Somain;
import com.aowin.model.PayRecord;
import com.aowin.util.DBUtil;

/**
 * 收款的service
 * @author Peter
 *
 */
public class ProceedsService {
	/**
	 * 单例
	 */
	private ProceedsService() {}
	private static ProceedsService proceedsService = new ProceedsService();
	public static ProceedsService getInstance() {
		return proceedsService;
	}
	private Connection conn;
	/**
	 * 分页查询销售主单
	 */
	public Page findSomain(Page page) throws SQLException{
		try {
			conn = DBUtil.getConnection();
			return SomainDao.getInstance().findSomain(page);
		} finally {
			conn.close();
		}
	}
/**
* 查询全部销售单数
* @throws SQLException 
*/
public int findAllCount(String con) throws Exception {
	try {
		conn = DBUtil.getConnection();
		return SomainDao.getInstance().findAllCount(con);
	} finally {
		conn.close();
	}
}	
/**
 * 分付款方式分页查询
 */
public Page findSomain(Page page,String payType) throws SQLException{
	try {
		conn = DBUtil.getConnection();
		return SomainDao.getInstance().findSomain(page, payType);
	} finally {
		conn.close();
	}
}	
/**
* 查询单个销售单(总价、预付款价格、付款方式、经办人)
* @throws SQLException 
*/
	@SuppressWarnings("resource")
	public Somain searchSomain(Somain som) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			return SomainDao.getInstance().searchSomain(som);
		} finally {
			conn.close();
		}
	}
/**
 * 登记
 * 修改somain
 * 新增收付款记录单     
 * @throws SQLException 
 */
	@SuppressWarnings("resource")
	public boolean checkin(Somain som, PayRecord pr) throws SQLException {
		conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			int rowsS = SomainDao.getInstance().updateSomain(som);
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
	public Soitem searchSoitem(Soitem soi) throws SQLException{
		try {
			conn = DBUtil.getConnection();
			return SoitemDao.getInstance().searchSoitem(soi);
		} finally {
			conn.close();
		}
	}
}
