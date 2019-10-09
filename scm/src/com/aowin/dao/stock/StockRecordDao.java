package com.aowin.dao.stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aowin.model.StockRecord;
import com.aowin.util.DBUtil;

/**
 * 库存记录表的数据库操作
 * 
 * @author Peter
 *
 */
public class StockRecordDao {
	/**
	 * 单例
	 */
	private StockRecordDao() {
	}

	private static StockRecordDao stockRecordDao = new StockRecordDao();

	public static StockRecordDao getInstance() {
		return stockRecordDao;
	}

	/**
	 * 增加记录
	 * 
	 * @throws SQLException
	 */
	public int addStockRecord(StockRecord sr) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "insert into stockRecord(productCode,stockNum,stockType,stockTime,createUser)"
				+ "values(?,?,?,?,?)";

		conn = DBUtil.getConnection();
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sr.getProductCode());
			pstat.setInt(2, sr.getStockNum());
			pstat.setInt(3, sr.getStockType());
			pstat.setString(4, sr.getStockTime());
			pstat.setString(5, sr.getCreateUser());
			return pstat.executeUpdate();
		} finally {
			DBUtil.close(pstat, rs);
		}
	}
}
