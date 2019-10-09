package com.aowin.servlet.finance;

/**
 * 付款登记的servlet
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

import com.aowin.model.Pomain;
import com.aowin.service.PaymentService;
import com.aowin.model.PayRecord;
import com.aowin.util.DateUtil;


@WebServlet("/finance/paymentCheckinServlet")
public class PaymentCheckinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从客户端获取数据
		String poId = request.getParameter("poId");
		//response
		PrintWriter out = response.getWriter();
		//创建somian对象
		Pomain pom = new Pomain();
		String time = DateUtil.getDate(new Date());
		pom.setPoId(Long.parseLong(poId));
		pom.setPayTime(time);
		try {
			PaymentService.getInstance().searchPomain(pom);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//创建payRecord对象
		PayRecord pr = new PayRecord();
		pr.setAccount("财务");
		pr.setOrderCode(pom.getPoId());
		pr.setPayTime(time);
		if (pom.getPayType() == 1 || pom.getPayType() == 2) {
			//货到付款  or 款到发货 一次性收款
			pr.setPayPrice(pom.getPoTotal());
			pr.setPayType(pom.getPayType());
		}else {
			//预付款到发货
			if (pom.getStatus() == 1) {
				//新增的销售单 收预付款
				pr.setPayPrice(pom.getPrePayFee());
				pr.setPayType(pom.getPayType());
			}else {
				//已收货，收余款
				pr.setPayPrice(pom.getPoTotal() - pom.getPrePayFee());
				pr.setPayType(pom.getPayType());
			}
		}
		//数据库操作
		//新增首付款记录单      修改somain  事务
		try {
			boolean flag = PaymentService.getInstance().checkin(pom, pr);
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
