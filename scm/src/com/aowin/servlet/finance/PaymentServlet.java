package com.aowin.servlet.finance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aowin.model.Page;
import com.aowin.service.PaymentService;

/**
 * 付款servlet
 * @author Peter
 *
 */
@WebServlet("/finance/paymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查询分页信息
		Page page = new Page();
		try {
			//设置分页的总记录数、总页数（内部调用方法）
			page.setPageSize(5);
			page.setCurrentPage(1);;
			page.setAllcount(PaymentService.getInstance().findAllCount(new String("")));
			//从数据库中获取第一页page return page
			PaymentService.getInstance().findPomain(page);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/scm/error.jsp");
		}
		//request请求 产品list、page信息
		request.setAttribute("pomainList", page.getData());
		request.setAttribute("page", page);

		//跳转
		request.getRequestDispatcher("/finance/payment.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
