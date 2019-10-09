package com.aowin.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aowin.dao.product.ProductDao;
import com.aowin.model.CheckStock;
import com.aowin.model.Page;
import com.aowin.model.Product;
import com.aowin.util.DBUtil;

public class ProductService {
	/**
	 * 单例
	 */
	private ProductService() {}

	private static ProductService productService = new ProductService();

	public static ProductService getInstance() {
		return productService;
	}
	private Connection conn;
	/**
	 * 查询全部产品记录数
	 * 
	 * @throws SQLException
	 */
	public int findAllCount() throws SQLException {
		try {
			conn = DBUtil.getConnection();
			return ProductDao.getInstance().findAllCount();
		} finally {
			conn.close();
		}
	}

	/**
	 * 查询全部产品信息
	 * 
	 * @throws SQLException
	 */
	public ArrayList<Product> findAll() throws SQLException {
		try {
			conn = DBUtil.getConnection();
			return ProductDao.getInstance().findAll();
		} finally {
			conn.close();
		}
	}

	/**
	 * 分页查询产品信息
	 */
	public Page findProduct(Page page) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			return ProductDao.getInstance().findProduct(page);
		} finally {
			conn.close();
		}
	}

	/**
	 * 更新产品信息
	 * 
	 * @throws SQLException
	 */
	public int updateProduct(CheckStock cs) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			return ProductDao.getInstance().updateProduct(cs);
		} finally {
			conn.close();
		}
	}
	/**
	 * 月度产品报表---产品库存
	 * @throws SQLException 
	 */
	public Page findProductReport(Page page, String con) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			return ProductDao.getInstance().findProductReport(page, con);
		} finally {
			conn.close();
		}
	}
	/**
	 * 月度产品报表 查询全部产品数
	 */
	public Page findAllCount(Page page) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			return ProductDao.getInstance().findAllCount(page);
		} finally {
			conn.close();
		}
	}
	/**
	 * 月度产品报表---产品库存----明细单
	 * @throws SQLException 
	 */
	@SuppressWarnings("resource")
	public Product searchProductItemOfReport(Product product, String con) throws SQLException {
		try {
			conn = DBUtil.getConnection();
			return ProductDao.getInstance().searchProductItemOfReport(product, con);
		} finally {
			conn.close();
		}
	}
}
