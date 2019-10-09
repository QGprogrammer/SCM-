package com.aowin.servlet.finance;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONArray;
import com.aowin.model.Page;
import com.aowin.service.ProceedsService;
import com.aowin.util.TransToSqlUtil;

/**
 * 销售单list分页的servlet
 */
@WebServlet("/finance/proceedsPageServlet")
public class ProceedsPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String payType = request.getParameter("payType");
		int currentPage = Integer.parseInt(request.getParameter("goPage"));
		payType = TransToSqlUtil.transToSql(payType);
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setPageSize(5);
		//数据库查询分页数据
		try {
			page.setAllcount(ProceedsService.getInstance().findAllCount(payType.substring(0,payType.length()-9)));
			ProceedsService.getInstance().findSomain(page,payType);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/scm/error.jsp");
		}
		String proceedsJson = JSONArray.toJSONString(page.getData());
		proceedsJson = proceedsJson.substring(0,proceedsJson.length()-1);
		proceedsJson += (",{\"pageCount\":"+page.getPageCount()+",\"allcount\":"+page.getAllcount()+"}]");
		PrintWriter out = response.getWriter();
		out.write(proceedsJson);
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
