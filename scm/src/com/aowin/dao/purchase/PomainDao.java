package com.aowin.dao.purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aowin.model.Page;
import com.aowin.model.Pomain;
import com.aowin.util.DBUtil;

/**
 * 采购主单的数据库操作
 * 
 * @author Peter
 *
 */
public class PomainDao {
	/**
	 * 单例
	 */
	private PomainDao() {
	}

	private static PomainDao pomainDao = new PomainDao();

	public static PomainDao getInstance() {
		return pomainDao;
	}

	/**
	 * 删除某用户相关销售单
	 * 
	 * @throws SQLException
	 */
	public int deletePomain(Pomain pom) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "delete from pomain where account=?";

		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pom.getAccount());
		} finally {
			DBUtil.close(pstat, rs);
		}
		return pstat.executeUpdate();
	}

	/**
	 * 检查某用户是否有相关的采购单
	 * 
	 * @throws SQLException
	 */
	public boolean checkPomain(Pomain pom) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select account from pomain where account=?";
		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pom.getAccount());
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
	 * 分页查询采购主单
	 */
	public Page findPomain(Page page) throws SQLException {
		ArrayList<Pomain> list = new ArrayList<Pomain>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select p.poid, p.createtime, p.account, v.name, p.tipfee, p.producttotal, p.pototal,p.paytype, p.prepayfee, "
				+ " p.status from Pomain p,vender v where p.vendercode = v.vendercode and ((p.PayType=1 and p.status=2) or "
				+ "(p.PayType=2 and p.Status=1) or (p.PayType=3 and p.status=1) or (p.PayType=3 and p.status=2)) limit ?,?";
		// 获取连接
		conn = DBUtil.getConnection();
		try {
			// 执行sql
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, (page.getCurrentPage() - 1) * page.getPageSize());
			pstat.setInt(2, page.getPageSize());
			rs = pstat.executeQuery();
			while (rs.next()) {
				Pomain pom = new Pomain();
				pom.setPoId(Long.parseLong(rs.getString("poid")));
				pom.setCreateTime(rs.getString("createtime"));
				pom.setAccount(rs.getString("account"));
				pom.setVenderName(rs.getString("name"));
				pom.setTipfee(Float.parseFloat(rs.getString("tipfee")));
				pom.setProductTotal(Float.parseFloat(rs.getString("producttotal")));
				pom.setPoTotal(Float.parseFloat(rs.getString("pototal")));
				pom.setPayType(Integer.parseInt(rs.getString("paytype")));
				pom.setPrePayFee(Float.parseFloat(rs.getString("prepayfee")));
				pom.setStatus(Integer.parseInt(rs.getString("status")));
				list.add(pom);
			}
			page.setData(list);
		} finally {
			DBUtil.close(pstat, rs);
		}
		return page;
	}

	/**
	 * 查询全部采购单数
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
			sql = "select  count(*) number from Pomain s,vender c where s.vendercode = c.vendercode and ((s.PayType=1 and s.status=2) or (s.PayType=2 and s.Status=1) or (s.PayType=3 and s.status=1) or (s.PayType=3 and s.status=2))";
		} else {
			sql = "select  count(*) number from Pomain s,vender c where s.vendercode = c.vendercode and ((s.PayType=1 and s.status=2) or (s.PayType=2 and s.Status=1) or (s.PayType=3 and s.status=1) or (s.PayType=3 and s.status=2)) and "
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
	public Page findPomain(Page page, String payType) throws SQLException {
		ArrayList<Pomain> list = new ArrayList<Pomain>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "";
		if (payType.indexOf("0") > 0) {
			sql = "select s.poid, s.createtime, s.account, c.name, s.tipfee, s.producttotal, s.pototal,"
					+ "s.paytype, s.prepayfee, s.status from Pomain s,vender c where s.vendercode = c.vendercode and ((s.PayType=1 and s.status=2) or (s.PayType=2 and s.Status=1) or (s.PayType=3 and s.status=1) or (s.PayType=3 and s.status=2)) limit ?,?";
		} else {
			sql = "select s.poid, s.createtime, s.account, c.name, s.tipfee, s.producttotal, s.pototal,"
					+ "s.paytype, s.prepayfee, s.status from Pomain s,vender c where s.vendercode = c.vendercode and ((s.PayType=1 and s.status=2) or (s.PayType=2 and s.Status=1) or (s.PayType=3 and s.status=1) or (s.PayType=3 and s.status=2)) and "
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
				Pomain pom = new Pomain();
				pom.setPoId(Long.parseLong(rs.getString("poid")));
				pom.setCreateTime(rs.getString("createtime"));
				pom.setAccount(rs.getString("account"));
				pom.setVenderName(rs.getString("name"));
				pom.setTipfee(Float.parseFloat(rs.getString("tipfee")));
				pom.setProductTotal(Float.parseFloat(rs.getString("producttotal")));
				pom.setPoTotal(Float.parseFloat(rs.getString("pototal")));
				pom.setPayType(Integer.parseInt(rs.getString("paytype")));
				pom.setPrePayFee(Float.parseFloat(rs.getString("prepayfee")));
				pom.setStatus(Integer.parseInt(rs.getString("status")));
				list.add(pom);
			}
			page.setData(list);
			return page;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}

	/**
	 * 查询单个采购单(总价、预付款价格、付款方式、经办人)
	 * 
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public Pomain searchPomain(Pomain pom) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select * from pomain where poId=?";
		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sql);
			pstat.setLong(1, pom.getPoId());
			rs = pstat.executeQuery();
			if (rs.next()) {
				pom.setPoTotal(rs.getFloat("pototal"));
				pom.setPrePayFee(rs.getFloat("prepayfee"));
				pom.setPayType(rs.getInt("payType"));
				pom.setAccount(rs.getString("account"));
				pom.setStatus(rs.getInt("status"));
			} 
			return pom;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}

	/**
	 * 修改pomain
	 * 
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public int updatePomain(Pomain pom) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "";
		if (pom.getPayType() == 1 || pom.getPayType() == 2) {
			sql = "update pomain set paytime=?,payuser=?,status=status+1 where poid=?";
		} else {
			if (pom.getStatus() == 1) {
				sql = "update pomain set prepaytime=?,prepayuser=?,status=5 where poid=?";
			} else {
				sql = "update pomain set paytime=?,prepayuser=?,status=3 where poid=?";
			}
		}
		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pom.getPayTime());
			pstat.setString(2, pom.getAccount());
			pstat.setLong(3, pom.getPoId());
			return pstat.executeUpdate();
		} finally {
			DBUtil.close(pstat, rs);
		}
	}
	/**
	 * 查询月度付款单相关信息
	 * @throws SQLException 
	 */
	public Page findPomainReport(Page page,String con) throws SQLException {
		ArrayList<Pomain> list = new ArrayList<Pomain>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select * from pomain s,payrecord p where s.poid=p.ordercode and p.pay_time " + con + " limit ?,?";
		conn = DBUtil.getConnection();
		pstat = conn.prepareStatement(sql);
		try {
			pstat.setInt(1, (page.getCurrentPage() - 1) * page.getPageSize());
			pstat.setInt(2, page.getPageSize());
			rs = pstat.executeQuery();
			while (rs.next()) {
				Pomain pom = new Pomain();
				pom.setPoId(rs.getLong("poid"));
				pom.setCreateTime(rs.getString("createtime"));
				pom.setPayTime(rs.getString("pay_time"));
				pom.setPrePayFee(rs.getFloat("p.pay_price")); //付款金额暂时放这里
				pom.setPayUser(rs.getString("payuser"));
				pom.setStatus(rs.getInt("status"));
				list.add(pom);
			}
			page.setData(list);
			return page;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}
	/**
	 * 查询月度付款单总笔数
	 * @throws SQLException 
	 */
	public Page findAllPomainCount(Page page,String con) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select count(*) number from pomain s,payrecord p where s.poid=p.ordercode and p.pay_time " + con;
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
