package com.zya.blog.zyablog.myshiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

public class MyShiroFilter extends RolesAuthorizationFilter {
	
	private static Logger logger = Logger.getLogger(MyShiroFilter.class);

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		logger.info("无权限的操作");
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setStatus(401);
		
		// 请求被拦截后直接返回json格式的响应数据
		response.getWriter()
				.write("无权限");
		response.setCharacterEncoding("UTF-8");
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		response.getWriter().flush();
		response.getWriter().close();
		return false;
	}

}
