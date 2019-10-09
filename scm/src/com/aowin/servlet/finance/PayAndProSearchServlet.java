package com.aowin.servlet.finance;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.aowin.model.Page;
import com.aowin.service.PayAndProSearchService;
import com.aowin.util.TransToSqlUtil;

/**
 * 收支查询
 */
@WebServlet("/finance/payAndProSearchServlet")
public class PayAndProSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户端数据
		String orderId = request.getParameter("ordercode");
		String startTime = request.getParameter("time1");
		String endTime = request.getParameter("time2");
		int payType = Integer.parseInt(request.getParameter("payType"));
		String type = request.getParameter("type");
		Integer goPage = Integer.valueOf(request.getParameter("goPage"));
		//page设置
		Page page = new Page();
		page.setPageSize(5);
		page.setCurrentPage(goPage);
		//根据付款or收款 分别进行不同操作
		//收款 type==1
		//解析成sql拼接语句
		String sql = TransToSqlUtil.transToSql(orderId, startTime, endTime, payType, type);
		if ("1".equals(type)) {
			//查询
			try {
				PayAndProSearchService.getInstance().searchProceeds(page, sql);
				//转成json语句
				String result = JSONArray.toJSONString(page.getData());
				result = result.substring(0,result.length()-1);
				result += (",{\"pageCount\":"+page.getPageCount()+",\"allcount\":"+page.getAllcount()+"}]");
				PrintWriter out = response.getWriter();
				out.write(result);
				out.flush();
				out.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			//付款 type==0
			//查询
			try {
				PayAndProSearchService.getInstance().searchPayment(page, sql);
				//转成json语句
				String result = JSONArray.toJSONString(page.getData());
				result = result.substring(0,result.length()-1);
				result += (",{\"pageCount\":"+page.getPageCount()+",\"allcount\":"+page.getAllcount()+"}]");
				PrintWriter out = response.getWriter();
				out.write(result);
				out.flush();
				out.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
