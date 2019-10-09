package com.aowin.servlet.stock;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aowin.model.CheckStock;
import com.aowin.model.StockRecord;
import com.aowin.service.StockService;
import com.aowin.util.DateUtil;
import com.aowin.util.TransTypeUtil;

/**
 * 盘点库存保存的servlet
 */
@WebServlet("/stock/updateStockCountServlet")
public class UpdateStockCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取jsp传过来的参数
		String productCode = request.getParameter("productCode");
		int stockNum = Integer.parseInt(request.getParameter("stockNum"));
		String description = request.getParameter("description");
		String type = request.getParameter("type");
		int originNum = Integer.parseInt(request.getParameter("originNum"));
		
		//获取当前用户
		@SuppressWarnings("unused")
		String account = (String) request.getSession().getAttribute("account");
		
		//创建对象
		StockRecord sr = new StockRecord();
		sr.setProductCode(productCode);
		sr.setStockNum(stockNum);
		sr.setStockType(TransTypeUtil.getCountType(type));
		sr.setCreateUser("wangwenwen");
		sr.setStockTime(DateUtil.getDate(new Date()));
		
		CheckStock cs = new CheckStock();
		cs.setProductCode(productCode);
		cs.setStockTime(DateUtil.getDate(new Date()));
		cs.setCreateUser("wangwenwen");
		cs.setType(type);
		cs.setDescription(description);
		cs.setOriginNum(originNum);
		cs.setRealNum(TransTypeUtil.getCountType(type)==3?originNum+stockNum:originNum-stockNum);
		//更新产品表、更新库存记录表、更新盘点表 事务处理
			try {
				if (StockService.getInstance().updateStock(cs, sr)) {
					response.sendRedirect("/scm/stock/countServlet");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendRedirect("/scm/error.jsp");
			}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
