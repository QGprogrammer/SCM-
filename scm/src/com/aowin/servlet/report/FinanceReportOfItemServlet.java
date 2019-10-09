package com.aowin.servlet.report;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.aowin.model.Page;
import com.aowin.service.FinanceReportService;
import com.aowin.util.TransToSqlUtil;

/**
 * 月度财务报表收款详情
 */
@WebServlet("/report/financeReportOfItemServlet")
public class FinanceReportOfItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收客户端数据
		int currentPage = Integer.parseInt(request.getParameter("goPage"));
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		String type = request.getParameter("type");
		String con = TransToSqlUtil.transToSql(year, month);
		//创建对象
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setPageSize(5);
		//数据库查找数据
		try {
			//收款
			if ("1".equals(type)) {
				FinanceReportService.getInstance().findAllSomainCount(page, con);  //总记录数
				FinanceReportService.getInstance().findSomainReport(page, con);  //分页数据
			}else {
				//付款
				FinanceReportService.getInstance().findAllPomainCount(page, con);  //总记录数
				FinanceReportService.getInstance().findPomainReport(page, con);  //分页数据
			}
			String result = JSONArray.toJSONString(page.getData());
			result = result.substring(0,result.length()-1);
			result += (",{\"pageCount\":"+page.getPageCount()+",\"allcount\":"+page.getAllcount()+"}]");
			PrintWriter out = response.getWriter();
			out.write(result);
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
