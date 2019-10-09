package com.aowin.servlet.stock;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONArray;
import com.aowin.dao.product.ProductDao;
import com.aowin.model.Page;

/**
 * 分页的servlet
 */
@WebServlet("/stock/countPageServlet")
public class CountPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = Integer.parseInt(request.getParameter("goPage"));
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setPageSize(5);
		//数据库查询分页数据
		try {
			ProductDao.getInstance().findProduct(page);
		} catch (SQLException e) {
			response.sendRedirect("/scm/error.jsp");
			e.printStackTrace();
		}
		String productsJson = JSONArray.toJSONString(page.getData());
		PrintWriter out = response.getWriter();
		out.write(productsJson);
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
