package com.aowin.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.aowin.dao.scmuser.ScmuserDao;
import com.aowin.dao.scmuser.UserModelDao;
import com.aowin.model.Page;
import com.aowin.model.Scmuser;
import com.aowin.model.UserModel;
import com.aowin.util.DBUtil;

/**
 * 用户管理模块的service
 * @author Peter
 *
 */
public class ScmuserService {
	/**
	 * 单例
	 */
	private ScmuserService() {}
	private static ScmuserService scmuserService = new ScmuserService();
	public static ScmuserService getInstance() {
		return scmuserService;
	}
	private Connection conn;
	/**
	 * 新增用户 
	 * 用户表新增
	 * 模块表新增
	 * @throws SQLException 
	 */
	public boolean addScmuser(UserModel um, Scmuser su) throws SQLException {
		conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			int rowsS = ScmuserDao.getInstance().addScmuser(su);
			int rowsU = UserModelDao.getInstance().addUserModel(um);
			if (rowsS > 0 && rowsU > 0) {
				conn.commit();				
			}else {
				conn.rollback();
			}
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			conn.close();
		}
		return true;
	}
	/**
	 * 用户账号校验
	 * @throws SQLException 
	 */
	public boolean checkAccount(Scmuser su) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			return ScmuserDao.getInstance().checkAccount(su);
		} finally {
			conn.close();
		}
	}

	/**
	 * 查询全部用户数
	 * @throws SQLException 
	 */
	public int findAllCount(String con) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			return ScmuserDao.getInstance().findAllCount(con);
		} finally {
			conn.close();
		}
			
	}
	/**
	 * 分页查询用户
	 */
	public Page findScmuser(Page page) throws SQLException{
		try {
			conn = DBUtil.getConnection();
			return ScmuserDao.getInstance().findScmuser(page);
		} finally {
			conn.close();
		}
	}
	/**
	 * 带过滤条件分页查询用户
	 */
	public Page findScmuser(Page page,String con) throws SQLException{
		try {
			conn = DBUtil.getConnection();
			return ScmuserDao.getInstance().findScmuser(page, con);
		} finally {
			conn.close();
		}
	}
	/**
	 * 删除某用户的信息 事务处理
	 * @throws SQLException 
	 * @throws Exception 
	 */
	@SuppressWarnings("resource")
	public int deleteScmUser(Scmuser su,UserModel um) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			int rowsU = UserModelDao.getInstance().deleteUserModel(um);
			int rowsS = ScmuserDao.getInstance().deleteScmUser(su);
			if (rowsS > 0 && rowsU > 0) {
				conn.commit();
				return rowsS + rowsU;
			} else {
				conn.rollback();
				return 0;
			} 
		} finally {
			conn.close();
		}
	}
	/**
	 * 查询单个用户信息
	 * @throws SQLException 
	 */
	@SuppressWarnings("resource")
	public Scmuser searchScmuser(Scmuser su) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			return ScmuserDao.getInstance().searchScmuser(su);
		} finally {
			conn.close();
		}
			
	}
	/**
	 * 修改用户信息
	 * 删除usermodel相关信息
	 * 重新插入usermodel信息
	 * 更新scmuser
	 * @throws SQLException 
	 */
	public boolean updateScmuer(UserModel um,Scmuser su) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			int rowsD = UserModelDao.getInstance().deleteUserModel(um);
			int rowsA = UserModelDao.getInstance().addUserModel(um);
			boolean flag = ScmuserDao.getInstance().updateScmuer(su);
			if (rowsD > 0 && rowsA > 0 && flag == true) {
				conn.commit();
			} else {
				conn.rollback();
			}
			return true;
		} finally {
			conn.close();
		}
	}
	/**
	 * 登录校验
	 * @throws SQLException 
	 */
	public boolean loginCheck(Scmuser user) throws SQLException {
	//dd	try {
			conn = DBUtil.getConnection();
			return ScmuserDao.getInstance().loginCheck(user);
		//}
//		} finally {
//			conn.close();
//		}
	}
}
