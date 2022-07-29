package com.care.root.member.controller;

import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.root.common.SessionName;
import com.care.root.member.service.MemberService;

@Controller
@RequestMapping("member") //경로 중간값 설정
public class MemberController implements SessionName{
	
	@Autowired
	MemberService ms;
	
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/user_check")
	public String user_check(HttpServletRequest request, RedirectAttributes rs) {
		int result = ms.user_check(request); //로그인 성공시 0, 실패시 1
		if(result == 0) {
			rs.addAttribute( "id", request.getParameter("id") ); //세션 생성을 위해 아이디값 담아서 넘겨줌 //addFlashAttribute: 객체 전달 가능
			return "redirect:successLogin";
		}
		return "redirect:login";
	}
	
	@GetMapping("successLogin")
	public String successLogin(String id, HttpSession session) {
		//session.setAttribute("loginUser", id); //세션 생성
		session.setAttribute( LOGIN, id ); //인터페이스 상속을 통해 값 설정
		System.out.println("id : " + id);
		return "member/successLogin";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}

}
