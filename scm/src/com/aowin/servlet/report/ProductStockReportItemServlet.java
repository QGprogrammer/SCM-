package com.aowin.servlet.report;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aowin.model.Product;
import com.aowin.service.ProductService;
import com.aowin.util.DateUtil;
import com.aowin.util.TransToSqlUtil;

/**
 * 产品报表的明细单
 */
@WebServlet(name = "productStockReportItemServlet", urlPatterns = { "/report/productStockReportItemServlet" })
public class ProductStockReportItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户端数据
		String productCode = request.getParameter("productCode");
		//客户端获取数据
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		String con = TransToSqlUtil.transToSql(year, month, DateUtil.getDate(new Date()));
		//获得输出流
		PrintWriter out = response.getWriter();
		//创建对象
		Product product = new Product();
		product.setProductCode(productCode);
		//数据库查询
		try {
			ProductService.getInstance().searchProductItemOfReport(product, con);
			//返还给客户端
			String msg = "{\"productCode\":"+product.getProductCode()+",\"name\":\""+product.getName()+"\",\"num\":"+product.getNum()+"}";
			out.print(msg);
			out.flush();
			out.close();
		} catch (SQLException e) {
			response.sendRedirect("/scm/error.jsp");
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
