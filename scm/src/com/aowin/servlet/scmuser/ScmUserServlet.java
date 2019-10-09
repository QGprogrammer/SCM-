package com.aowin.servlet.scmuser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.aowin.model.Scmuser;
import com.aowin.service.ScmuserService;

/**
 * 单个用户的信息查询servlet
 */
@WebServlet("/scmuser/scmUserServlet")
public class ScmUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取服务器数据
		String account = request.getParameter("account");
		//获得输出流
		PrintWriter out = response.getWriter();
		//创建对象
		Scmuser su = new Scmuser();
		su.setAccount(account);
		//数据库查询用户数据并返回
		try {
			ScmuserService.getInstance().searchScmuser(su);
			String scmUserJson = JSON.toJSONString(su);
			out.print(scmUserJson);
			out.flush();
			out.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
