package com.care.root.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;

public class AutoLogin extends HandlerInterceptorAdapter {

	@Autowired
	MemberService ms;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Cookie cookie = WebUtils.getCookie(request, "loginCookie"); //사용자 요청으로부터 쿠키값이 있으면 객체값이 들어가고 쿠키값이 없으면 null값이 들어간다.
		if( cookie != null ) { //자동로그인 체크한 사용자
			MemberDTO dto = ms.getCookieUser(cookie.getValue()); //존재하면 사용자 정보를 가져와서 저장함
			if( dto != null) { //데이터를 가져왔으면
				request.getSession().setAttribute("loginUser", dto.getId()); //로그인을 시켜줌
			}
		}
		
		return true;
	}
	
}
