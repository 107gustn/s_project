package com.care.root.member.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;

public interface MemberService {
	public int user_check(HttpServletRequest request);
	public void memberInfo(Model model);
	public void getUser(Model model, String id);
	public int register(MemberDTO dto);
	
	public void delete(String id);
	public void modifyForm(String id, Model model);
	public void modify(HttpServletRequest req, String addr);
	public void keepLogin(String id, String cookieId);
	public MemberDTO getCookieUser(String cookie);
}
