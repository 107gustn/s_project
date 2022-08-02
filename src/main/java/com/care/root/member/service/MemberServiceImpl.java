package com.care.root.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public void delete(String id) {
		mapper.delete(id);
	}
	
	public void modifyForm(String id, Model model) {
		MemberDTO dto = mapper.getUser(id); //사용자와 같은 정보를 가져옴
		System.out.println(dto.getAddr());
		String[] a = dto.getAddr().split(","); //split: 특정값을 기준으로 영역을 쪼개줌
		System.out.println(a[0]);
		System.out.println(a[1]);
		System.out.println(a[2]);
		
		model.addAttribute("dto", dto); //가져온 값 model에 저장
		model.addAttribute("addrs", a); //배열 형태로 저장된다
		
	}
	
	public void modify(HttpServletRequest req, String addr) {
		
		MemberDTO dto = new MemberDTO();
		dto.setId(req.getParameter("id"));
		dto.setAddr( addr );
		if( req.getParameter("new_pw").equals("******") ) { //비밀번호가 수정이 안되었으면
			dto.setPw(req.getParameter("old_pw"));
		}else { //비밀번호가 수정이 되면 암호화 시켜서 값 저장
			dto.setPw( en.encode( req.getParameter("new_pw") ) ); //인코더를 통해 평문으로 되어있는 비밀번호를 암호화
		}
		mapper.modify( dto );
	}
	
	public void keepLogin(String id, String cookieId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("cookieId", cookieId);
		mapper.keepLogin(map);
	}
	
	public MemberDTO getCookieUser(String cookie) {
		return mapper.getCookieUser( cookie );
	}

}
