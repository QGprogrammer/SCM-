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


@WebServlet("/scmuser/modifyScmuserServlet")
public class ModifyScmuserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Integer> list = new ArrayList<Integer>(6);
		//接收客户端传送的请求参数信息
		String account = request.getParameter("account");
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		int status = Integer.parseInt(request.getParameter("status"));
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
			boolean flag = ScmuserService.getInstance().updateScmuer(um, su);
			//返回客户端
			if (flag) {
				out.print("success");
			}else {
				out.print("failure");
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
