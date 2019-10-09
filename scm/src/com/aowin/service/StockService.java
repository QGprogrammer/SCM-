package com.aowin.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.aowin.dao.product.ProductDao;
import com.aowin.dao.stock.CheckStockDao;
import com.aowin.dao.stock.StockRecordDao;
import com.aowin.model.CheckStock;
import com.aowin.model.StockRecord;
import com.aowin.util.DBUtil;

/**
 * 库存盘点的service
 * 
 * @author Peter
 *
 */
public class StockService {
	/**
	 * 单例
	 */
	private StockService() {
	}

	private static StockService stockService = new StockService();

	public static StockService getInstance() {
		return stockService;
	}

	private Connection conn;

	/**
	 * 盘点保存 更新产品表 新增库存记录表 新增盘点表
	 * 
	 * @throws SQLException
	 */
	public boolean updateStock(CheckStock cs, StockRecord sr) throws SQLException {
		conn = DBUtil.getConnection();
		conn.setAutoCommit(false);
		try {
			int rowsP = ProductDao.getInstance().updateProduct(cs); // 更新产品表
			int rowsS = StockRecordDao.getInstance().addStockRecord(sr); // 新增库存记录表
			int rowsC = CheckStockDao.getInstance().addCheckStock(cs); /// 新增盘点表
			if (rowsP > 0 && rowsS > 0 && rowsC > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			return true;

		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			conn.close();
		}
	}
}
