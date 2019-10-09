package com.aowin.dao.purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aowin.model.Poitem;
import com.aowin.util.DBUtil;

/**
 * 销售明细单的dao
 * 
 * @author Peter
 *
 */
public class PoitemDao {
	/**
	 * 单例
	 */
	private PoitemDao() {
	}

	private static PoitemDao poitemDao = new PoitemDao();

	public static PoitemDao getInstance() {
		return poitemDao;
	}

	/**
	 * 查询明细单
	 */
	public Poitem searchPoitem(Poitem poi) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select * from poitem where poId=?";

		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sql);
			pstat.setLong(1, poi.getPoId());
			rs = pstat.executeQuery();
			if (rs.next()) {
				poi.setItemPrice(rs.getFloat("itemprice"));
				poi.setNum(rs.getInt("num"));
				poi.setUnitName(rs.getString("unitName"));
				poi.setUnitPrice(rs.getFloat("unitprice"));
				poi.setProductCode(rs.getString("productCode"));
			} 
			return poi;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}
}
