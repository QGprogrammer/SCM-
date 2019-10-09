package com.aowin.dao.scmuser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aowin.model.Page;
import com.aowin.model.Scmuser;
import com.aowin.util.DBUtil;
import com.aowin.util.ModelCodeUtil;

/**
 * 用户管理的数据库操作
 * 
 * @author Peter
 *
 */
public class ScmuserDao {
	/**
	 * 单例
	 */
	private ScmuserDao() {
	}

	private static ScmuserDao scmuserDao = new ScmuserDao();

	public static ScmuserDao getInstance() {
		return scmuserDao;
	}

	/**
	 * 用户表新增
	 * 
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public int addScmuser(Scmuser su) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "insert into scmuser(account,password,name,createdate,status) values(?,?,?,?,?)";

		conn = DBUtil.getConnection();
		try {
			// 用户表更新
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, su.getAccount());
			pstat.setString(2, su.getPassword());
			pstat.setString(3, su.getName());
			pstat.setString(4, su.getCreateDate());
			pstat.setInt(5, su.getStatus());
			return pstat.executeUpdate();
		} finally {
			DBUtil.close(pstat, rs);
		}
	}

	/**
	 * 用户账号校验
	 * 
	 * @throws SQLException
	 */
	public boolean checkAccount(Scmuser su) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sqlUm = "select account from scmuser where account=?";

		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sqlUm);
			pstat.setString(1, su.getAccount());
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
	 * 查询全部用户数
	 * 
	 * @throws SQLException
	 */
	public int findAllCount(String con) throws SQLException {
		int number = 0;
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select count(*) number from scmuser " + con;

		try {
			// 获取连接
			conn = DBUtil.getConnection();
			// 执行sql
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while (rs.next()) {
				number = rs.getInt("number");
			} 
			return number;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}

	/**
	 * 分页查询用户
	 */
	public Page findScmuser(Page page) throws SQLException {
		ArrayList<Scmuser> list = new ArrayList<Scmuser>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select * from scmuser limit ?,?";
		String sqlMd = "select modelcode from usermodel where account=?";
		// 获取连接
		conn = DBUtil.getConnection();
		try {
			// 执行sql
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, (page.getCurrentPage() - 1) * page.getPageSize());
			pstat.setInt(2, page.getPageSize());
			rs = pstat.executeQuery();
			while (rs.next()) {
				Scmuser su = new Scmuser();
				su.setAccount(rs.getString("account"));
				su.setCreateDate(rs.getString("createdate"));
				su.setName(rs.getString("name"));
				su.setStatus(rs.getInt("status"));
				PreparedStatement ps = conn.prepareStatement(sqlMd);
				ps.setString(1, su.getAccount());
				ResultSet rs2 = ps.executeQuery();
				while (rs2.next()) {
					// 转化模块编码为中文表示并拼接
					String modelcode = su.getModelCode() == null
							? ModelCodeUtil.getModeNames(rs2.getString("modelcode"))
							: su.getModelCode() + "；" + ModelCodeUtil.getModeNames(rs2.getString("modelcode"));
					su.setModelCode(modelcode);
				}
				list.add(su);
			}
			page.setData(list);
			return page;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}

	/**
	 * 带过滤条件分页查询用户
	 */
	public Page findScmuser(Page page, String con) throws SQLException {
		ArrayList<Scmuser> list = new ArrayList<Scmuser>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select * from scmuser " + con;
		String sqlMd = "select modelcode from usermodel where account=?";
		// 获取连接
		conn = DBUtil.getConnection();
		try {
			// 执行sql
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, (page.getCurrentPage() - 1) * page.getPageSize());
			pstat.setInt(2, page.getPageSize());
			rs = pstat.executeQuery();
			while (rs.next()) {
				Scmuser su = new Scmuser();
				su.setAccount(rs.getString("account"));
				su.setCreateDate(rs.getString("createdate"));
				su.setName(rs.getString("name"));
				su.setStatus(rs.getInt("status"));
				PreparedStatement ps = conn.prepareStatement(sqlMd);
				ps.setString(1, su.getAccount());
				ResultSet rs2 = ps.executeQuery();
				while (rs2.next()) {
					// 转化模块编码为中文表示并拼接
					String modelcode = su.getModelCode() == null
							? ModelCodeUtil.getModeNames(rs2.getString("modelcode"))
							: su.getModelCode() + "；" + ModelCodeUtil.getModeNames(rs2.getString("modelcode"));
					su.setModelCode(modelcode);
				}
				list.add(su);
			}
			page.setData(list);
			return page;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}

	/**
	 * 删除某用户的信息
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public int deleteScmUser(Scmuser su) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sqlSu = "delete from scmuser where account=?";

		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sqlSu);
			pstat.setString(1, su.getAccount());
			return pstat.executeUpdate();
		} finally {
			DBUtil.close(pstat, rs);
		}
	}

	/**
	 * 查询单个用户信息
	 * 
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public Scmuser searchScmuser(Scmuser su) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select * from scmuser where account=?";
		String sqlUm = "select modelcode from usermodel where account=?";
		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, su.getAccount());
			rs = pstat.executeQuery();
			if (rs.next()) {
				su.setName(rs.getString("name"));
				su.setPassword(rs.getString("password"));
				su.setStatus(Integer.parseInt(rs.getString("status")));
			}
			pstat = conn.prepareStatement(sqlUm);
			pstat.setString(1, su.getAccount());
			rs = pstat.executeQuery();
			String modelCode = new String();
			while (rs.next()) {
				modelCode = modelCode == null ? rs.getString("modelcode") : modelCode + rs.getString("modelcode");
			}
			su.setModelCode(modelCode);
			return su;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}

	/**
	 * 修改用户信息
	 * 
	 * @throws SQLException
	 */
	public boolean updateScmuer(Scmuser su) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "update scmuser set name=?,password=?,status=? where account=?";
		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, su.getName());
			pstat.setString(2, su.getPassword());
			pstat.setInt(3, su.getStatus());
			pstat.setString(4, su.getAccount());
			pstat.executeUpdate();
			return true;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}
	/**
	 * 登录校验
	 * @throws SQLException 
	 */
	public boolean loginCheck(Scmuser user) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select account from scmuser where account=? and password=?";
		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, user.getAccount());
			pstat.setString(2, user.getPassword());
			rs = pstat.executeQuery();
			if (rs.next()) {
				return true;				
			}
			return false;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}
}
