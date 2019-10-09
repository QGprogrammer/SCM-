package com.aowin.servlet.stock;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aowin.dao.product.ProductDao;
import com.aowin.model.Page;
import com.aowin.service.ProductService;

/**
 * 库存盘点的servlet
 * @author Peter
 *
 */
@WebServlet("/stock/countServlet")
public class CountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Page page = new Page();
		try {
			//设置分页的总记录数、总页数（内部调用方法）
			page.setPageSize(5);
			page.setCurrentPage(1);;
			page.setAllcount(ProductDao.getInstance().findAllCount());
			//从数据库中获取第一页page return page
			ProductService.getInstance().findProduct(page);
			
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("/scm/error.jsp");
		}
		//request请求 产品list、page信息
		request.setAttribute("productList", page.getData());
		request.setAttribute("page", page);
		//带请求跳转至jsp界面
		request.getRequestDispatcher("/stock/count.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
