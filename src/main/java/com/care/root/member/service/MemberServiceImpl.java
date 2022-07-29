package com.care.root.member.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberMapper mapper;
	
	//오버라이딩
	public int user_check(HttpServletRequest request) {
		MemberDTO dto = mapper.getUser(request.getParameter("id")); //아이디가 존재하면 dto에 데이터값이 들어온다
		if(dto != null) { //아이디가 존재할시
			if( dto.getPw().equals( request.getParameter("pw")) ) { //비밀번호 확인
				return 0;
			}
		} //아이디, 비밀번호 오류
		return 1;
	}

}
