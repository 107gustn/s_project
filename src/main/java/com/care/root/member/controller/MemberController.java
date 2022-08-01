package com.care.root.member.controller;

import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.root.common.SessionName;
import com.care.root.member.dto.MemberDTO;
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
		session.setAttribute( LOGIN, id ); //인터페이스 상속을 통해 세션 값 설정
		System.out.println("id : " + id);
		return "member/successLogin";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate(); //세션 삭제
		return "redirect:/index";
	}
	
	@GetMapping("memberInfo")
	public String memberInfo(Model model) { //JSP페이지로 값 전달을 위해 Model객체 사용
		ms.memberInfo( model );
		return "member/memberInfo"; //model 값을 이용해서 데이터를 꺼내올 수 있다.
	}
	
	@GetMapping("info")
	public String info(Model model, String id) {
		ms.getUser(model, id);
		return "member/info";
	}
	
	@GetMapping("register_form")
	public String register_form() {
		return "member/register";
	}
	
	@PostMapping("register")
	public String register(MemberDTO dto) {
		int result = ms.register(dto); //회원가입이 성공하면 1, 실패하면 0 을 돌려줌
		if(result == 1) {
			return "redirect:login";
		}
		return "redirect:register_form";
	}
	
}
