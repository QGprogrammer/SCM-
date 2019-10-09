package com.aowin.servlet.finance;

/**
 * 收款登记的servlet
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aowin.model.Somain;
import com.aowin.service.ProceedsService;
import com.aowin.model.PayRecord;
import com.aowin.util.DateUtil;


@WebServlet("/finance/proceedsCheckinServlet")
public class ProceedsCheckinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从客户端获取数据
		String soId = request.getParameter("soId");
		//response
		PrintWriter out = response.getWriter();
		//创建somian对象
		Somain som = new Somain();
		String time = DateUtil.getDate(new Date());
		som.setSoId(Long.parseLong(soId));
		som.setPayTime(time);
		try {
			ProceedsService.getInstance().searchSomain(som);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//创建payRecord对象
		PayRecord pr = new PayRecord();
		pr.setAccount("财务");
		pr.setOrderCode(som.getSoId());
		pr.setPayTime(time);
		if (som.getPayType() == 1 || som.getPayType() == 2) {
			//货到付款  or 款到发货 一次性收款
			pr.setPayPrice(som.getPoTotal());
			pr.setPayType(som.getPayType());
		}else {
			//预付款到发货
			if (som.getStatus() == 1) {
				//新增的销售单 收预付款
				pr.setPayPrice(som.getPrePayFee());
				pr.setPayType(som.getPayType());
			}else {
				//已收货，收余款
				pr.setPayPrice(som.getPoTotal() - som.getPrePayFee());
				pr.setPayType(som.getPayType());
			}
		}
		//数据库操作
		//新增首付款记录单      修改somain  事务
		try {
			boolean flag = ProceedsService.getInstance().checkin(som, pr);
			if (flag) {
				out.print("success");
			}else {
				out.print("failure");
			}
			out.flush();
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
