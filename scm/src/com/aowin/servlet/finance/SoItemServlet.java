package com.aowin.servlet.finance;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.aowin.model.Soitem;
import com.aowin.service.ProceedsService;

/**
 * 返回销售明细单的servlet
 */
@WebServlet("/finance/soItemServlet")
public class SoItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求参数
		Long soId = Long.parseLong(request.getParameter("soId"));
		//输出流
		PrintWriter out = response.getWriter();
		//创建对象
		Soitem soi = new Soitem();
		soi.setSoId(soId);
		//数据库查询
		try {
			ProceedsService.getInstance().searchSoitem(soi);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//转化json并传送
		String soitemJson = JSON.toJSONString(soi);
		out.print(soitemJson);
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
