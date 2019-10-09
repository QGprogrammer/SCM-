package com.aowin.dao.finance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aowin.model.Page;
import com.aowin.model.PayRecord;
import com.aowin.util.DBUtil;

/**
 * 收付款记录dao
 * 
 * @author Peter
 *
 */
public class PayRecordDao {

	/**
	 * 单例
	 */
	private PayRecordDao() {
	}

	private static PayRecordDao payRecordDao = new PayRecordDao();

	public static PayRecordDao getInstance() {
		return payRecordDao;
	}

	/**
	 * 新增
	 * 
	 * @throws SQLException
	 */
	public int addPayRecord(PayRecord pr) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "insert into payrecord(pay_price, account, ordercode, pay_type, pay_time) values(?,?,?,?,?)";
		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sql);
			pstat.setFloat(1, pr.getPayPrice());
			pstat.setString(2, pr.getAccount());
			pstat.setLong(3, pr.getOrderCode());
			pstat.setInt(4, pr.getPayType());
			pstat.setString(5, pr.getPayTime());
			return pstat.executeUpdate();
		} finally {
			DBUtil.close(pstat, rs);
		}
	}
	/**
	 * 查询相关的收款订单信息
	 * @throws SQLException 
	 */
	public Page searchProceeds(Page page, String con) throws SQLException {
		ArrayList<PayRecord> list = new ArrayList<PayRecord>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select * from somain s,payrecord p "+ con;
		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, (page.getCurrentPage() - 1 ) * page.getPageSize());
			pstat.setInt(2, page.getPageSize());
			rs = pstat.executeQuery();
			while (rs.next()) {
				PayRecord pr = new PayRecord();
				pr.setOrderCode(Long.valueOf(rs.getString("ordercode")));
				pr.setPayTime(rs.getString("pay_time"));
				pr.setAccount(rs.getString("account"));
				pr.setPayType(rs.getInt("pay_type"));
				pr.setPayPrice(rs.getFloat("pay_price"));
				list.add(pr);
			}
			page.setData(list);
			return page;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}
	/**
	 * 查询收款的总记录数
	 * @throws SQLException 
	 */
	public Page findAllProceedsCount(Page page, String con) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select count(*) number from somain s,payrecord p "+ con;
		sql = sql.substring(0, sql.length() - 9);
		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			if (rs.next()) {
				page.setAllcount(rs.getInt("number"));
			}
			return page;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}
	/**
	 * 查询相关的付款订单信息
	 * @throws SQLException 
	 */
	public Page searchPayment(Page page, String con) throws SQLException {
		ArrayList<PayRecord> list = new ArrayList<PayRecord>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select * from pomain s,payrecord p "+ con;
		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, (page.getCurrentPage() - 1 ) * page.getPageSize());
			pstat.setInt(2, page.getPageSize());
			rs = pstat.executeQuery();
			while (rs.next()) {
				PayRecord pr = new PayRecord();
				pr.setOrderCode(Long.valueOf(rs.getString("ordercode")));
				pr.setPayTime(rs.getString("pay_time"));
				pr.setAccount(rs.getString("account"));
				pr.setPayType(rs.getInt("pay_type"));
				pr.setPayPrice(rs.getFloat("pay_price"));
				list.add(pr);
			}
			page.setData(list);
			return page;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}
	/**
	 * 查询付款款的总记录数
	 * @throws SQLException 
	 */
	public Page findAllPaymentCount(Page page, String con) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select count(*) number from pomain s,payrecord p "+ con;
		sql = sql.substring(0, sql.length() - 9);
		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			if (rs.next()) {
				page.setAllcount(rs.getInt("number"));
			}
			return page;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}
	/**
	 * 查询月度收款的明细单
	 * @throws SQLException 
	 */
	public ArrayList<PayRecord> findAllFinanceReportOfProceeds(String con) throws SQLException {
		ArrayList<PayRecord> list = new ArrayList<PayRecord>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select * from somain s,payrecord p where s.soid=p.ordercode and pay_time " + con;
		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while (rs.next()) {
				PayRecord pr = new PayRecord();
				pr.setPayPrice(rs.getFloat("pay_price"));
				list.add(pr);
			}
			return list;
		} 
		finally {
			DBUtil.close(pstat, rs);
		}
	}
	/**
	 * 查询月度付款的明细单
	 * @throws SQLException 
	 */
	public ArrayList<PayRecord> findAllFinanceReportOfPayment(String con) throws SQLException {
		ArrayList<PayRecord> list = new ArrayList<PayRecord>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select * from pomain s,payrecord p where s.poid=p.ordercode and pay_time " + con;
		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while (rs.next()) {
				PayRecord pr = new PayRecord();
				pr.setPayPrice(rs.getFloat("pay_price"));
				list.add(pr);
			}
			return list;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}
}
