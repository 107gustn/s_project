package com.care.root.mybatis.member;

import com.care.root.member.dto.MemberDTO;

public interface MemberMapper {
	
	public MemberDTO getUser( String id ); //MemberDTO형태로 결과값을 service로 돌려줌

}
