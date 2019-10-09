package com.aowin.servlet.scmuser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aowin.model.Scmuser;
import com.aowin.service.ScmuserService;

/**
 * 用户登录的servlet
 */
@WebServlet(name = "loginServlet", urlPatterns = { "/loginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得客户端数据
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		//创建对象
		Scmuser user = new Scmuser();
		user.setAccount(account);
		user.setPassword(password);
		//获得输出流
		PrintWriter out = response.getWriter();
		//数据库校验
		try {
			boolean flag = ScmuserService.getInstance().loginCheck(user);
			if (flag) {
				request.getSession().setAttribute("user", user);
				out.print("success");
			}else {
				out.print("failure");
			}
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
