package com.aowin.dao.scmuser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aowin.model.UserModel;
import com.aowin.util.DBUtil;

/**
 * 用户模块dao 权限
 * 
 * @author Peter
 *
 */
public class UserModelDao {
	/**
	 * 单例
	 */
	private UserModelDao() {
	}

	private static UserModelDao userModelDao = new UserModelDao();

	public static UserModelDao getInstance() {
		return userModelDao;
	}

	/**
	 * 新增
	 */
	public int addUserModel(UserModel um) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "insert into usermodel(account,modelcode) values(?,?)";

		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sql);
			for (Integer md : um.getModelCode()) {
				pstat.setString(1, um.getAccount());
				pstat.setInt(2, md);
				pstat.executeUpdate();
			} 
			return 1;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}

	/**
	 * 删除
	 * 
	 * @throws SQLException
	 */
	public int deleteUserModel(UserModel um) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sqlUm = "delete from usermodel where account=?";

		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sqlUm);
			pstat.setString(1, um.getAccount());
			return pstat.executeUpdate();
		} finally {
			DBUtil.close(pstat, rs);
		}

	}
	/**
	 * 查询用户模块表 已知账号 查uri
	 * @return
	 * @throws SQLException 
	 */
	public UserModel searchUserModel(UserModel um) throws SQLException {
		ArrayList<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sqlUm = "select modeluri from usermodel u,systemmodel s where u.modelcode=s.modelcode and account=?";
		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sqlUm);
			pstat.setString(1, um.getAccount());
			rs = pstat.executeQuery();
			while (rs.next()) {
				String uri = rs.getString("modelUri");
				list.add(uri);
			}
			um.setModelUri(list);
			return um;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}
}
