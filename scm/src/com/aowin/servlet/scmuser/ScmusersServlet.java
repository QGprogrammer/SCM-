package com.aowin.servlet.scmuser;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.aowin.model.Page;
import com.aowin.service.ScmuserService;

/**
 * 用户管理首页的用户list查询
 * @author Peter
 *
 */
@WebServlet("/scmuser/scmusersServlet")
public class ScmusersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查询分页信息
		Page page = new Page();
		try {
			//设置分页的总记录数、总页数（内部调用方法）
			page.setPageSize(5);
			page.setCurrentPage(1);;
			page.setAllcount(ScmuserService.getInstance().findAllCount(new String("")));
			//从数据库中获取第一页page return page
			ScmuserService.getInstance().findScmuser(page);
			//request请求 产品list、page信息
			request.setAttribute("scmuserList", page.getData());
			request.setAttribute("page", page);
			
			request.getRequestDispatcher("/scmuser/scmuser.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		//跳转
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
