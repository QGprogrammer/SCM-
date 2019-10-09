package com.aowin.servlet.scmuser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aowin.model.Pomain;
import com.aowin.model.Scmuser;
import com.aowin.model.Somain;
import com.aowin.model.UserModel;
import com.aowin.service.PurchaseService;
import com.aowin.service.SaleService;
import com.aowin.service.ScmuserService;

/**
 * 删除用户
 * @author Peter
 *
 */
@WebServlet("/scmuser/delScmuserServlet")
public class DelScmuserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收客户端数据
		String account = request.getParameter("account");
		//获得输出流
		PrintWriter out = response.getWriter();
		//创建对象
		Somain som = new Somain();
		som.setAccount(account);
		Pomain pom = new Pomain();
		pom.setAccount(account);
		Scmuser su = new Scmuser();
		su.setAccount(account);
		UserModel um = new UserModel();
		um.setAccount(account);
		//检查、删除
		try {
			boolean isSom = SaleService.getInstance().checkSomain(som);//检查销售单
			boolean isPom = PurchaseService.getInstance().checkPomain(pom);//检查采购单
			if (isSom || isPom) {
				//不予许删除
				out.print("false");
			}else {
				//删除用户权限表 删除用户表 事务处理
				if (ScmuserService.getInstance().deleteScmUser(su,um) > 0) {
						out.print("success");
				}
			}
		} catch (SQLException e) { 
			response.sendRedirect("/scm/error.jsp");
			e.printStackTrace();
		}
		//返回结果给客户端
		out.flush();
		out.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
