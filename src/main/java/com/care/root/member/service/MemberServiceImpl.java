package com.care.root.member.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberMapper mapper;
	
	//암호화 처리
	BCryptPasswordEncoder en = new BCryptPasswordEncoder(); //new 연산자를 통해 객체화 시킴
	
	//오버라이딩
	public int user_check(HttpServletRequest request) {
		MemberDTO dto = mapper.getUser(request.getParameter("id")); //아이디가 존재하면 dto에 데이터값이 들어온다
		if(dto != null) { //아이디가 존재할시
			if( en.matches(request.getParameter("pw"), dto.getPw() ) || dto.getPw().equals( request.getParameter("pw")) ) { //matches(사용자로부터 가져온 평문비밀번호, DB에서 가져온 암호화된 비밀번호) || DB로부터 가져온 비밀번호와 사용자가 입력한 비밀번호 확인
				return 0;
			}
		} //아이디, 비밀번호 오류
		return 1;
	}
	
	public void memberInfo(Model model) {
		List<MemberDTO> list = mapper.memberInfo(); //mapper와 연결해줌
		model.addAttribute("list", list ); //DB에서 회원 정보를 가져와서 저장
		//model.addAttribute("list", mapper.memberInfo() );
	}
	
	public void getUser(Model model, String id) {
		model.addAttribute("info", mapper.getUser(id) ); //돌려받은 값을 info라는 이름으로 저장함
	}
	
	public int register(MemberDTO dto) {
		//insert into membership(id, pw, addr) value(?,?,?); //insert 성공시 1을 되돌려줌, 실패 시 에러메시지를 되돌려줌
		System.out.println("암호화 전 : " + dto.getPw());
		String seq = en.encode( dto.getPw() ); //인코더를 통해 평문으로 되어있는 비밀번호를 암호화 시켜줌
		System.out.println("암호화 후 : " + seq);
		
		dto.setPw( seq ); //암호화한 값으로 바꿔줌
		
		try {
			return mapper.register( dto ); //DB 연결 코드
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
