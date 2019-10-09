package com.aowin.servlet.report;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aowin.model.PayRecord;
import com.aowin.service.FinanceReportService;
import com.aowin.util.CalculateUtil;
import com.aowin.util.TransToSqlUtil;

/**
 * 月度收支报表
 */
@WebServlet("/report/financeReportServlet")
public class FinanceReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//客户端获取数据
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		String con = TransToSqlUtil.transToSql(year, month);
		ArrayList<PayRecord> list = null;
		int proceedsCount = 0;
		int paymentCount = 0;
		float proceedsSum = 0f;
		float paymentSum = 0f;
		//输出流
		PrintWriter out = response.getWriter();
		//数据库查找数据
		try {
			list = FinanceReportService.getInstance().findAllFinanceReportOfProceeds(con);
			proceedsCount = list.size();  //总收款数
			proceedsSum = CalculateUtil.sumPayOrPro(list);  //总收款额
			list = FinanceReportService.getInstance().findAllFinanceReportOfPayment(con);
			paymentCount = list.size();  //总收款数
			paymentSum = CalculateUtil.sumPayOrPro(list);  //总收款额
			//返还给客户端
			String msg = "{\"proceedsCount\":"+proceedsCount+",\"proceedsSum\":"+proceedsSum+",\"paymentCount\":"+paymentCount+",\"paymentSum\":"+paymentSum+"}";
			out.print(msg);
			out.flush();
			out.close();
		} catch (SQLException e) {
			response.sendRedirect("/scm/error.jsp");
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
