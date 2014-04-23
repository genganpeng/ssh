package cn.gap.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gap.action.login.LoginAction;
import cn.gap.beans.UserLogin;

public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("创建LoginFilter过滤器");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String contextPath = httpServletRequest.getContextPath();
		String url = httpServletRequest.getServletPath();
		System.out.println("访问的URL：" + contextPath + url);
		if (url.equals("")) url += "/";
		//不是登陆页面
		if ((url.endsWith(".jsp") || url.endsWith(".action") || url.endsWith(".html") ) && url.startsWith("/") && !url.startsWith("/login") && !url.startsWith("/index.jsp")) {
			UserLogin user = (UserLogin)httpServletRequest.getSession().getAttribute(LoginAction.USER_SESSION);
			if (null == user) {
				httpServletResponse.sendRedirect(contextPath);
				return ;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("销毁LoginFilter过滤器");
	}

}	
