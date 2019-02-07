package com.sue.open.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		if(session != null && session.getAttribute("login") != null){
			return true;
		}
		request.setAttribute("msg", "로그인이 필요한 페이지입니다.");
		RequestDispatcher rdis = request.getRequestDispatcher("/login");
		rdis.forward(request, response);
		return false;
	}

}
