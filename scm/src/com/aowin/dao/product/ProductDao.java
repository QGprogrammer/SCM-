package com.aowin.dao.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aowin.model.CheckStock;
import com.aowin.model.Page;
import com.aowin.model.Product;
import com.aowin.util.DBUtil;

/**
 * 数据库查询产品表的操作
 * 
 * @author Peter
 *
 */
public class ProductDao {
	/**
	 * 单例
	 */
	private ProductDao() {
	}

	private static ProductDao productDao = new ProductDao();

	public static ProductDao getInstance() {
		return productDao;
	}

	/**
	 * 查询全部产品记录数
	 * 
	 * @throws SQLException
	 */
	public int findAllCount() throws SQLException {
		int number = 0;
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select count(*) number from product";

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
	 * 查询全部产品信息
	 * 
	 * @throws SQLException
	 */
	public ArrayList<Product> findAll() throws SQLException {
		ArrayList<Product> list = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select * from product";

		// 获取连接
		conn = DBUtil.getConnection();
		try {
			// 执行sql
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setCategoryId(rs.getInt("categoryId"));
				product.setCreateDate(rs.getString("createDate"));
				product.setName(rs.getString("name"));
				product.setNum(rs.getInt("num"));
				product.setPoNum(rs.getInt("poNum"));
				product.setPrice(rs.getFloat("price"));
				product.setProductCode(rs.getString("productCode"));
				product.setRemark(rs.getString("remark"));
				product.setSoNum(rs.getInt("soNum"));
				product.setUnitName(rs.getString("unitName"));
				list.add(product);
			} 
			return list;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}

	/**
	 * 分页查询产品信息
	 */
	public Page findProduct(Page page) throws SQLException {
		ArrayList<Product> list = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select * from product limit ?,?";

		// 获取连接
		conn = DBUtil.getConnection();
		// 执行sql

		try {
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, (page.getCurrentPage() - 1) * page.getPageSize());
			pstat.setInt(2, page.getPageSize());
			rs = pstat.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setCategoryId(rs.getInt("categoryId"));
				product.setCreateDate(rs.getString("createDate"));
				product.setName(rs.getString("name"));
				product.setNum(rs.getInt("num"));
				product.setPoNum(rs.getInt("poNum"));
				product.setPrice(rs.getFloat("price"));
				product.setProductCode(rs.getString("productCode"));
				product.setRemark(rs.getString("remark"));
				product.setSoNum(rs.getInt("soNum"));
				product.setUnitName(rs.getString("unitName"));
				list.add(product);
			}
			page.setData(list);
			return page;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}

	/**
	 * 更新产品信息
	 * 
	 * @throws SQLException
	 */
	public int updateProduct(CheckStock cs) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "update product set num=? where productCode=?";
		try {
			conn = DBUtil.getConnection();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, cs.getRealNum());
			pstat.setString(2, cs.getProductCode());
			return pstat.executeUpdate();
		} finally {
			DBUtil.close(pstat, rs);
		}
	}
	/**
	 * 月度产品报表---产品库存
	 * @throws SQLException 
	 */
	@SuppressWarnings("resource")
	public Page findProductReport(Page page, String con) throws SQLException {
		ArrayList<Product> list = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sqlPr = "select productcode,name,num from product limit ?,?";
		String sqlAdd = "select sum(stocknum) number from stockrecord where productcode=? and (stocktype=1 or stocktype=3) and stocktime "+ con;
		String sqlReduce = "select sum(stocknum) number from stockrecord where productcode=? and (stocktype=2 or stocktype=4) and stocktime "+ con;
		conn = DBUtil.getConnection();
		int count = 0;
		int add = 0;
		int reduce = 0;
		try {
			pstat = conn.prepareStatement(sqlPr);
			pstat.setInt(1, (page.getCurrentPage() - 1) * page.getPageSize());
			pstat.setInt(2, page.getPageSize());
			rs = pstat.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductCode(rs.getString("productcode"));
				product.setName(rs.getString("name"));
				count = rs.getInt("num"); //当前数量 
				//查询该期间增加的库存
				pstat = conn.prepareStatement(sqlAdd);
				pstat.setString(1, product.getProductCode());
				ResultSet rs2 = pstat.executeQuery();
				if (rs2.next()) {
					add = rs2.getInt("number");
				}
				////查询该期间减少的的库存
				pstat = conn.prepareStatement(sqlReduce);
				pstat.setString(1, product.getProductCode());
				rs2 = pstat.executeQuery();
				if (rs2.next()) {
					reduce = rs2.getInt("number");
				}
				product.setNum(count - add + reduce); //当前数量减去库存新增 加上库存减少
				list.add(product);
			}
			page.setData(list);
			return page;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}
	/**
	 * 月度产品报表---产品库存----明细单
	 * @throws SQLException 
	 */
	@SuppressWarnings("resource")
	public Product searchProductItemOfReport(Product product, String con) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sqlPr = "select name,num from product where productCode=?";
		String sqlAdd = "select sum(stocknum) number from stockrecord where productcode=? and (stocktype=1 or stocktype=3) and stocktime "+ con;
		String sqlReduce = "select sum(stocknum) number from stockrecord where productcode=? and (stocktype=2 or stocktype=4) and stocktime "+ con;
		conn = DBUtil.getConnection();
		int count = 0;
		int add = 0;
		int reduce = 0;
		try {
			pstat = conn.prepareStatement(sqlPr);
			pstat.setString(1, product.getProductCode());
			rs = pstat.executeQuery();
			if (rs.next()) {
				product.setName(rs.getString("name"));
				count = rs.getInt("num"); //当前数量 
				//查询该期间增加的库存
				pstat = conn.prepareStatement(sqlAdd);
				pstat.setString(1, product.getProductCode());
				ResultSet rs2 = pstat.executeQuery();
				if (rs2.next()) {
					add = rs2.getInt("number");
				}
				////查询该期间减少的的库存
				pstat = conn.prepareStatement(sqlReduce);
				pstat.setString(1, product.getProductCode());
				rs2 = pstat.executeQuery();
				if (rs2.next()) {
					reduce = rs2.getInt("number");
				}
				product.setNum(count - add + reduce); //当前数量减去库存新增 加上库存减少
			}
			return product;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}
	/**
	 * 查询全部产品数
	 * @throws SQLException 
	 */
	public Page findAllCount(Page page) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = "select productcode,name,num from product";
		int count = 0;
		conn = DBUtil.getConnection();
		try {
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while (rs.next()) {
				count++;
			}
			page.setAllcount(count);
			return page;
		} finally {
			DBUtil.close(pstat, rs);
		}
	}
}
