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

import com.aowin.model.Poitem;
import com.aowin.service.PaymentService;

/**
 * 返回采购单明细单的servlet
 */
@WebServlet("/finance/poItemServlet")
public class PoItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求参数
		Long poId = Long.parseLong(request.getParameter("poId"));
		//输出流
		PrintWriter out = response.getWriter();
		//创建对象
		Poitem poi = new Poitem();
		poi.setPoId(poId);
		//数据库查询
		try {
			PaymentService.getInstance().searchPoitem(poi);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//转化json并传送
		String poitemJson = JSON.toJSONString(poi);
		out.print(poitemJson);
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
