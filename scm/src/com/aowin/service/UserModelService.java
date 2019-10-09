package com.aowin.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.aowin.dao.scmuser.UserModelDao;
import com.aowin.model.UserModel;
import com.aowin.util.DBUtil;

/**
 * 用户权限表的相关操作
 * @author Peter
 *
 */
public class UserModelService {
	/**
	 * 单例
	 */
	private UserModelService() {}
	private static UserModelService userModelService = new UserModelService();
	public static UserModelService getInstance() {
		return userModelService;
	}
	private Connection conn;
	/**
	 * 查询用户模块表 已知账号 查uri
	 * @return
	 * @throws SQLException 
	 */
	public UserModel searchUserModel(UserModel um) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			return UserModelDao.getInstance().searchUserModel(um);
		} finally {
			conn.close();
		}
	}
}
