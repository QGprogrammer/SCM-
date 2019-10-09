package com.aowin.dao.stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.aowin.model.CheckStock;
import com.aowin.util.DBUtil;

/**
 * 盘点表的数据库操作
 * 
 * @author Peter
 *
 */
public class CheckStockDao {
	/**
	 * 单例
	 */
	private CheckStockDao() {
	}

	private static CheckStockDao checkStockDao = new CheckStockDao();

	public static CheckStockDao getInstance() {
		return checkStockDao;
	}

	/**
	 * 增加记录
	 * 
	 * @throws SQLException
	 */
	public int addCheckStock(CheckStock cs) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		String sql = "insert into checkStock(productCode,originNum,realNum,stockTime,createUser,description,type)"
				+ "values(?,?,?,?,?,?,?)";

		conn = DBUtil.getConnection();
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cs.getProductCode());
			pstat.setInt(2, cs.getOriginNum());
			pstat.setInt(3, cs.getRealNum());
			pstat.setString(4, cs.getStockTime());
			pstat.setString(5, cs.getCreateUser());
			pstat.setString(6, cs.getDescription());
			pstat.setString(7, cs.getType());
			return pstat.executeUpdate();
		} finally {
			DBUtil.close(pstat, null);
		}

	}
}
