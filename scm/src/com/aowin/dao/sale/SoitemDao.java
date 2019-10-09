package com.aowin.dao.sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aowin.model.Soitem;
import com.aowin.util.DBUtil;

/**
 * 销售明细单的dao
 * 
 * @author Peter
 *
 */
public class SoitemDao {
	/**
	 * 单例
	 */
	private SoitemDao() {
	}

	private static SoitemDao soitemDao = new SoitemDao();

	public static SoitemDao getInstance() {
		return soitemDao;
	}

	/**
	 * 查询明细单
	 */
	public Soitem searchSoitem(Soitem soi) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select * from soitem where soId=?";

		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sql);
			pstat.setLong(1, soi.getSoId());
			rs = pstat.executeQuery();
			if (rs.next()) {
				soi.setItemPrice(rs.getFloat("itemprice"));
				soi.setNum(rs.getInt("num"));
				soi.setUnitName(rs.getString("unitName"));
				soi.setUnitPrice(rs.getFloat("unitprice"));
				soi.setProductCode(rs.getString("productCode"));
			} 
			return soi;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}
}
