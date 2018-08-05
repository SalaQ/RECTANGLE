package com.seu.wsn.Common.Interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.seu.wsn.Core.StaticConst.WebConst;

/**
 * 
 * @ClassName: LoginInterceptor 
 * @Description: 登录拦截器
 * @author: CSS
 * @date: 2016-10-30 上午10:45:05
 */
public class LoginInterceptor implements HandlerInterceptor{
	private List<String> allowedPass;		//设置允许不被拦击的URL
	/**
	 * 
	 * @Title: setAllowedPadd 
	 * @Description:注入allowedPass
	 * @param allowedPadd
	 * @return: void
	 */
	public void setAllowedPass(List<String> allowedPass) {
		this.allowedPass = allowedPass;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(request.getSession().getAttribute(WebConst.USERNAME)!=null) return true;
		String url = request.getRequestURI().toString();
		for(String temp:allowedPass){
			if(url.endsWith(temp)){
				return true;
			}
		}
		response.sendRedirect("index.jsp");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
