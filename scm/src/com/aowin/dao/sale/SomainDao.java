package com.aowin.dao.sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aowin.model.Page;
import com.aowin.model.Somain;
import com.aowin.util.DBUtil;

/**
 * 销售主单的数据库操作
 * 
 * @author Peter
 *
 */
public class SomainDao {
	/**
	 * 单例
	 */
	private SomainDao() {
	}

	private static SomainDao somainDao = new SomainDao();

	public static SomainDao getInstance() {
		return somainDao;
	}

	/**
	 * 删除某用户相关销售单
	 * 
	 * @throws SQLException
	 */
	public int deleteSomain(Somain som) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "delete from somain where account=?";

		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, som.getAccount());
		} finally {
			DBUtil.close(pstat, rs);
		}
		return pstat.executeUpdate();
	}

	/**
	 * 检查某用户是否有相关的销售单
	 * 
	 * @throws SQLException
	 */
	public boolean checkSomain(Somain som) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select account from somain where account=?";
		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, som.getAccount());
			rs = pstat.executeQuery();
			if (rs.next()) {
				return true;

			} 
		} finally {
			DBUtil.close(pstat, rs);
		}
		return false;
	}

	/**
	 * 分页查询销售主单
	 */
	public Page findSomain(Page page) throws SQLException {
		ArrayList<Somain> list = new ArrayList<Somain>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select s.soid, s.createtime, s.account, c.name, s.tipfee, s.producttotal, s.pototal,s.paytype, s.prepayfee, "
				+ " s.status from Somain s,customer c where s.customercode = c.customercode and ((s.PayType=1 and s.status=2) or "
				+ "(s.PayType=2 and s.Status=1) or (s.PayType=3 and s.status=1) or (s.PayType=3 and s.status=2)) limit ?,?";
		// 获取连接
		conn = DBUtil.getConnection();
		try {
			// 执行sql
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, (page.getCurrentPage() - 1) * page.getPageSize());
			pstat.setInt(2, page.getPageSize());
			rs = pstat.executeQuery();
			while (rs.next()) {
				Somain som = new Somain();
				som.setSoId(Long.parseLong(rs.getString("soid")));
				som.setCreateTime(rs.getString("createtime"));
				som.setAccount(rs.getString("account"));
				som.setCustomerName(rs.getString("name"));
				som.setTipFee(Float.parseFloat(rs.getString("tipfee")));
				som.setProductTotal(Float.parseFloat(rs.getString("producttotal")));
				som.setPoTotal(Float.parseFloat(rs.getString("pototal")));
				som.setPayType(Integer.parseInt(rs.getString("paytype")));
				som.setPrePayFee(Float.parseFloat(rs.getString("prepayfee")));
				som.setStatus(Integer.parseInt(rs.getString("status")));
				list.add(som);
			}
			page.setData(list);
		} finally {
			DBUtil.close(pstat, rs);
		}
		return page;
	}

	/**
	 * 查询全部销售单数
	 * 
	 * @throws SQLException
	 */
	public int findAllCount(String con) throws Exception {
		int number = 0;
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "";
		if ("".equals(con) || con.indexOf("0") > 0) {
			sql = "select  count(*) number from Somain s,customer c where s.customercode = c.customercode and ((s.PayType=1 and s.status=2) or (s.PayType=2 and s.Status=1) or (s.PayType=3 and s.status=1) or (s.PayType=3 and s.status=2))";
		} else {
			sql = "select  count(*) number from Somain s,customer c where s.customercode = c.customercode and ((s.PayType=1 and s.status=2) or (s.PayType=2 and s.Status=1) or (s.PayType=3 and s.status=1) or (s.PayType=3 and s.status=2)) and "
					+ con;
		}

		try {
			// 获取连接
			conn = DBUtil.getConnection();
			// 执行sql
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while (rs.next()) {
				number = rs.getInt("number");
			} 
		} finally {
			DBUtil.close(pstat, rs);
		}
		return number;
	}

	/**
	 * 分付款方式分页查询
	 */
	public Page findSomain(Page page, String payType) throws SQLException {
		ArrayList<Somain> list = new ArrayList<Somain>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "";
		if (payType.indexOf("0") > 0) {
			sql = "select s.soid, s.createtime, s.account, c.name, s.tipfee, s.producttotal, s.pototal,"
					+ "s.paytype, s.prepayfee, s.status from Somain s,customer c where s.customercode = c.customercode and ((s.PayType=1 and s.status=2) or (s.PayType=2 and s.Status=1) or (s.PayType=3 and s.status=1) or (s.PayType=3 and s.status=2)) limit ?,?";
		} else {
			sql = "select s.soid, s.createtime, s.account, c.name, s.tipfee, s.producttotal, s.pototal,"
					+ "s.paytype, s.prepayfee, s.status from Somain s,customer c where s.customercode = c.customercode and ((s.PayType=1 and s.status=2) or (s.PayType=2 and s.Status=1) or (s.PayType=3 and s.status=1) or (s.PayType=3 and s.status=2)) and "
					+ payType;
		}
		// 获取连接
		conn = DBUtil.getConnection();
		try {
			// 执行sql
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, (page.getCurrentPage() - 1) * page.getPageSize());
			pstat.setInt(2, page.getPageSize());
			rs = pstat.executeQuery();
			while (rs.next()) {
				Somain som = new Somain();
				som.setSoId(Long.parseLong(rs.getString("soid")));
				som.setCreateTime(rs.getString("createtime"));
				som.setAccount(rs.getString("account"));
				som.setCustomerName(rs.getString("name"));
				som.setTipFee(Float.parseFloat(rs.getString("tipfee")));
				som.setProductTotal(Float.parseFloat(rs.getString("producttotal")));
				som.setPoTotal(Float.parseFloat(rs.getString("pototal")));
				som.setPayType(Integer.parseInt(rs.getString("paytype")));
				som.setPrePayFee(Float.parseFloat(rs.getString("prepayfee")));
				som.setStatus(Integer.parseInt(rs.getString("status")));
				list.add(som);
			}
			page.setData(list);
		} finally {
			DBUtil.close(pstat, rs);
		}
		return page;
	}

	/**
	 * 查询单个销售单(总价、预付款价格、付款方式、经办人)
	 * 
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public Somain searchSomain(Somain som) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select * from somain where soId=?";
		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sql);
			pstat.setLong(1, som.getSoId());
			rs = pstat.executeQuery();
			if (rs.next()) {
				som.setPoTotal(rs.getFloat("pototal"));
				som.setPrePayFee(rs.getFloat("prepayfee"));
				som.setPayType(rs.getInt("payType"));
				som.setAccount(rs.getString("account"));
				som.setStatus(rs.getInt("status"));
			} 
		} finally {
			DBUtil.close(pstat, rs);
		}
		return som;
	}

	/**
	 * 修改somain
	 * 
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public int updateSomain(Somain som) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sqlSom = "";
		if (som.getPayType() == 1 || som.getPayType() == 2) {
			sqlSom = "update somain set paytime=?,payuser=?,status=status+1 where soid=?";
		} else {
			if (som.getStatus() == 1) {
				sqlSom = "update somain set prepaytime=?,prepayuser=?,status=5 where soid=?";
			} else {
				sqlSom = "update somain set paytime=?,prepayuser=?,status=3 where soid=?";
			}
		}
		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sqlSom);
			pstat.setString(1, som.getPayTime());
			pstat.setString(2, som.getAccount());
			pstat.setLong(3, som.getSoId());
			return pstat.executeUpdate();
		} finally {
			DBUtil.close(pstat, rs);
		}
	}
	/**
	 * 查询月度收款单相关信息
	 * @throws SQLException 
	 */
	public Page findSomainReport(Page page,String con) throws SQLException {
		ArrayList<Somain> list = new ArrayList<Somain>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select * from somain s,payrecord p where s.soid=p.ordercode and p.pay_time " + con + " limit ?,?";
		conn = DBUtil.getConnection();
		pstat = conn.prepareStatement(sql);
		try {
			pstat.setInt(1, (page.getCurrentPage() - 1) * page.getPageSize());
			pstat.setInt(2, page.getPageSize());
			rs = pstat.executeQuery();
			while (rs.next()) {
				Somain som = new Somain();
				som.setSoId(rs.getLong("soid"));
				som.setCreateTime(rs.getString("createtime"));
				som.setPayTime(rs.getString("pay_time"));
				som.setPrePayFee(rs.getFloat("p.pay_price")); //付款金额暂时放这里
				som.setPayUser(rs.getString("payuser"));
				som.setStatus(rs.getInt("status"));
				list.add(som);
			}
			page.setData(list);
			return page;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}
	/**
	 * 查询月度收款单总笔数
	 * @throws SQLException 
	 */
	public Page findAllSomainCount(Page page,String con) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select count(*) number from somain s,payrecord p where s.soid=p.ordercode and p.pay_time " + con;
		conn = DBUtil.getConnection();
		pstat = conn.prepareStatement(sql);
		try {
			rs = pstat.executeQuery();
			if (rs.next()) {
				page.setAllcount(rs.getInt("number"));
			}
			return page;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}

}
