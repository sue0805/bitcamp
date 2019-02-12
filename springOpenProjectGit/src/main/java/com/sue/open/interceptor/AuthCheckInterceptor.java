package com.sue.open.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sue.open.member.Member;

public class AuthCheckInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		if(session != null && session.getAttribute("login") != null && ((Member)session.getAttribute("login")).getStatus() == 1){
			return true;
		}
		request.setAttribute("msg", "이메일 인증이 필요합니다.");
		RequestDispatcher rdis = request.getRequestDispatcher("/mail/sendmail");
		rdis.forward(request, response);
		return false;
	}
}
