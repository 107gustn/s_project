package com.care.root.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//사용자가 로그인이 되어있는지 되어있지 않은지 판단
public class LoginCheckIn extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null) { //로그인이 되어있지 않다면
			//response.sendRedirect("login");
			response.setContentType("text/html; charset=utf-8"); //사용자 웹 브라우저로 어떠한 타입으로 사용자에게 응답할 것인지 //응답 방식
			PrintWriter out = response.getWriter(); //사용자에게 메시지 전달해줌
			out.print("<script>alert('로그인 먼저 진행!!!');location.href='login';</script>"); //사용자에게 메시지 전달됨
			return false; //사용자가 요청한 경로로 연결하지 않음
		}
		return true; //사용자가 요청한 경로로 연결
	}

}
