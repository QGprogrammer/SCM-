package com.aowin.servlet.scmuser;

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
import com.aowin.service.ScmuserService;
import com.aowin.util.TransToSqlUtil;

/**
 * 用户list分页的servlet
 */
@WebServlet("/scmuser/scmuserPageServlet")
public class ScmuserPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = Integer.parseInt(request.getParameter("goPage"));
		String con = request.getParameter("con");//分页查询的条件 全部查询or过滤查询
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setPageSize(5);
		if (con.equals("0")) {
			//数据库查询分页数据
			try {
				page.setAllcount(ScmuserService.getInstance().findAllCount(new String("")));
				ScmuserService.getInstance().findScmuser(page);
			} catch (SQLException e) {
				response.sendRedirect("/scm/error.jsp");
				e.printStackTrace();
			}
			String scmusersJson = JSONArray.toJSONString(page.getData());
			scmusersJson = scmusersJson.substring(0,scmusersJson.length()-1);
			scmusersJson += (",{\"pageCount\":"+page.getPageCount()+",\"allcount\":"+page.getAllcount()+"}]");
			PrintWriter out = response.getWriter();
			out.write(scmusersJson);
			out.flush();
			out.close();
		}else {
			//过滤查询
			String account = request.getParameter("account");
			String status = request.getParameter("status");
			String startTime = request.getParameter("time1");
			String endTime = request.getParameter("time2");
			String name = request.getParameter("username");
			//转化成sql语句
			String condition = TransToSqlUtil.transToSql(account, name, startTime, endTime, status);
			//数据库查询
			try {
				page.setAllcount(ScmuserService.getInstance().findAllCount(condition.substring(0,condition.length()-9)));
				ScmuserService.getInstance().findScmuser(page, condition);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			String scmusersJson = JSONArray.toJSONString(page.getData());
			//System.out.println(scmusersJson);
			scmusersJson = scmusersJson.substring(0,scmusersJson.length()-1);
			scmusersJson += (",{\"pageCount\":"+page.getPageCount()+",\"allcount\":"+page.getAllcount()+"}]");
			//System.out.println(scmusersJson);
			PrintWriter out = response.getWriter();
			out.write(scmusersJson);
			out.flush();
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
