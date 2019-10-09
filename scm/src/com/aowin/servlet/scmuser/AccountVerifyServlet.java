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
 * Servlet implementation class AccountVerifyServlet
 */
@WebServlet("/scmuser/accountVerifyServlet")
public class AccountVerifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		String msg = "true";
		//创建对象
		Scmuser su = new Scmuser();
		su.setAccount(account);
		//数据库校验账号
		try {
			if (ScmuserService.getInstance().checkAccount(su)) {
				msg = "×账号已存在";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("/scm/error.jsp");
		}
		//返回结果给客户端
		PrintWriter out = response.getWriter();
		out.write(msg);
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
