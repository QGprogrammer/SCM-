package com.aowin.servlet.scmuser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aowin.model.Scmuser;
import com.aowin.model.UserModel;
import com.aowin.service.ScmuserService;
import com.aowin.util.DateUtil;
import com.aowin.util.ModelCodeUtil;

/**
 * Servlet implementation class AddScmuser
 */
@WebServlet("/scmuser/addScmuserServlet")
public class AddScmuserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户端传送过来的数据
		ArrayList<Integer> list = new ArrayList<Integer>(6);
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String name = request.getParameter("username");
		Integer status = Integer.parseInt(request.getParameter("status"));
		String[] modelCodes = request.getParameterValues("modelCode");
		list = ModelCodeUtil.getModelCodeList(modelCodes);
		PrintWriter out = response.getWriter();				
		//创建对象
		Scmuser su = new Scmuser();
		su.setAccount(account);
		su.setCreateDate(DateUtil.getDate(new Date()));
		su.setName(name);
		su.setPassword(password);
		su.setStatus(status);
		UserModel um = new UserModel();
		um.setAccount(account);
		um.setModelCode(list);
		//数据库操作
		try {
			if (ScmuserService.getInstance().addScmuser(um, su)) {
				//返回给客户端 成功
				out.print("success");
			}else {
				//返回给客户端 失败
				out.print("failue");
			}
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
