package com.care.root.member.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;

public interface MemberService {
	public int user_check(HttpServletRequest request);
	public void memberInfo(Model model);
	public void getUser(Model model, String id);
	
	public int register(MemberDTO dto);

}
