package com.aowin.servlet.report;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.aowin.model.Page;
import com.aowin.service.ProductService;
import com.aowin.util.DateUtil;
import com.aowin.util.TransToSqlUtil;

/**
 * 产品库存报表的servlet
 */
@WebServlet("/report/productStockReportServlet")
public class ProductStockReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//客户端获取数据
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		int goPage = Integer.parseInt(request.getParameter("goPage"));
		String con = TransToSqlUtil.transToSql(year, month, DateUtil.getDate(new Date()));
		//获得输出流
		PrintWriter out = response.getWriter();
		//判断时间是否超过当前时间范围
		//boolean flag = DateUtil.isOutOfNow(year, month);
		//if (flag) {
			//创建对象
			Page page = new Page();
			page.setCurrentPage(goPage);
			page.setPageSize(5);
			//数据库查询
			try {
				ProductService.getInstance().findProductReport(page, con);  //分页数据
				ProductService.getInstance().findAllCount(page);  //总记录数
				String result = JSONArray.toJSONString(page.getData());
				result = result.substring(0,result.length()-1);
				result += (",{\"pageCount\":"+page.getPageCount()+",\"allcount\":"+page.getAllcount()+"}]");
				out.write(result);
				out.flush();
				out.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		/*}else {
			out.print("{\"status\":\"failure\"}");
			out.flush();
			out.close();
		}*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
