package com.aowin.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aowin.model.Scmuser;
import com.aowin.model.UserModel;
import com.aowin.service.UserModelService;
import com.aowin.util.ModelCodeUtil;

/**
 * 收支管理的过滤器
 */
@WebFilter("/finance/*")
public class FinanceFilter implements Filter {

   
	@SuppressWarnings("unused")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		Scmuser user = (Scmuser) req.getSession().getAttribute("user");
	
		String uri = req.getRequestURI();
		
		//用户管理模块
		if (uri.contains("/finance/")) {
			if (user == null) {
				resp.sendRedirect("/scm/login.html");
			}else {
				UserModel um = new UserModel();
				um.setAccount(user.getAccount());
				try {
					UserModelService.getInstance().searchUserModel(um);
					boolean flag= ModelCodeUtil.isPermited(um.getModelUri(), "/finance/");
					if (flag) {
						chain.doFilter(request, response);									
					}else {
						resp.sendRedirect("/scm/blank.html");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}else {
			chain.doFilter(request, response);			
		}
	}
}
