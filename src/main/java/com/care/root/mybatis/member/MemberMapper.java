package com.care.root.mybatis.member;

import java.util.List;
import java.util.Map;

import com.care.root.member.dto.MemberDTO;

public interface MemberMapper {
	
	public MemberDTO getUser( String id ); //MemberDTO형태로 결과값을 service로 돌려줌
	public List<MemberDTO> memberInfo(); //xml에서 동일 메소드명을 찾아가서 회원 정보를 리스트로 받아옴 //List형태로 값을 돌려준다
	public int register(MemberDTO dto); //성공시 1, 실패시 에러값 되돌려줌
	
	public void delete(String id);
	public void modify(MemberDTO dto);
	public void keepLogin(Map<String, Object> map);
	public MemberDTO getCookieUser(String cookie);
}
