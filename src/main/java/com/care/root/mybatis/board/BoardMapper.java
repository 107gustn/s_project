package com.care.root.mybatis.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;
import com.care.root.member.dto.MemberDTO;

public interface BoardMapper {
	
	public List<BoardDTO> boardAllList(@Param("s") int start, @Param("e") int end); //넘겨주는 매개변수가 2개 이상이면 @param(xml에서 사용할 이름)을 써줘야 한다
	public int writeSave(BoardDTO dto);
	
	public BoardDTO contentView(int writeNo);
	public void upHit(int writeNo);
	public int modify( BoardDTO dto );
	public int delete(int writeNo );
	
	public void addReply( Map<String, String> map );
	public List<BoardRepDTO> getRepList(int write_group);
	
	public int selectBoardCount();
}
