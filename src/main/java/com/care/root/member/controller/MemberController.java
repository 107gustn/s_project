package com.care.root.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
			//rs.addAttribute( "id", request.getParameter("id") ); //세션 생성을 위해 아이디값 담아서 넘겨줌 //addFlashAttribute: 객체 전달 가능
			
			rs.addAttribute("id", request.getParameter("id"));
			rs.addAttribute("autoLogin", request.getParameter("autoLogin"));
			
			return "redirect:successLogin"; //로그인 성공시 이동
		}
		return "redirect:login";
	}
	
	@GetMapping("successLogin")
	public String successLogin(@RequestParam String id, @RequestParam(required = false) String autoLogin, HttpSession session, HttpServletResponse response) {
		//session.setAttribute("loginUser", id); //세션 생성
		System.out.println("autoLogin : " + autoLogin);
		System.out.println("id : " + id);
		
		if( autoLogin != null ) { //자동 로그인 체크했으면
			int time = 60*60*24*90; //90일
			Cookie cookie = new Cookie("loginCookie", id); //자동로그인 쿠키값 전달
			cookie.setMaxAge(time); //자동로그인 시간 설정
			cookie.setPath("/");
			response.addCookie(cookie); //사용자 쿠키값 전달
			
			ms.keepLogin(id, id);
		}
		
		session.setAttribute( LOGIN, id ); //인터페이스 상속을 통해 세션 값 설정 //사용자가 입력한 아이디로 LOGIN이름으로 세션이 만들어줌
		return "member/successLogin";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session, @CookieValue(required = false) Cookie loginCookie, HttpServletResponse response) {
		if( loginCookie != null) {
			loginCookie.setMaxAge(0);
			loginCookie.setPath("/");
			response.addCookie(loginCookie);
			ms.keepLogin((String)session.getAttribute(LOGIN), "nan");
		}
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
	
	@GetMapping("delete")
	public String delete(String id) {
		ms.delete(id);
		return "redirect:logout";
	}
	
	@GetMapping("modify_form")
	public String modify_form(String id, Model model) { //데이터 전달을 위해 Model 사용
		ms.modifyForm( id, model );
		return "member/modify_form";
	}
	
	@PostMapping("modify")
	public String modify(HttpServletRequest req, String addr) { //dto로 값을 받을려면 name의 변수와 dto의 변수가 일치해야 한다
		System.out.println(req.getParameter("id"));
		System.out.println(req.getParameter("new_pw"));
		System.out.println(req.getParameter("old_pw"));
		System.out.println(addr);
		ms.modify(req,  addr);
		return "redirect:info?id="+req.getParameter("id");
	}
	
	
}
