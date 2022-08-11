package com.care.root.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.board.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardMapper mapper;
	
	@Autowired
	BoardFileService bfs;
	
	public void boardAllList(Model model) {
		model.addAttribute("boardList", mapper.boardAllList() );
	}
	
	public String writeSave(MultipartHttpServletRequest mul, HttpServletRequest request) {
		
		BoardDTO dto = new BoardDTO();
		dto.setTitle( mul.getParameter("title"));
		dto.setContent( mul.getParameter("content"));
		dto.setId( mul.getParameter("id"));
		
		MultipartFile file = mul.getFile("image_file_name"); //이미지 처리
		if( file.getSize() != 0) { //이미지 선택시
			dto.setImageFileName( bfs.saveFile(file) );
		}else { //이미지 없는 경우
			dto.setImageFileName("nan");
		}
		int result = 0;
		result = mapper.writeSave(dto); //DB에 저장 //저장 성공시 1, 실패시 0
		
		String msg, url;
		if( result == 1) { //성공시
			msg = "새글이 추가되었습니다!!!"; //메세지를 보내줌
			url = request.getContextPath() + "/board/boardAllList"; //절대경로로 연결
		}else { //실패시
			msg = "문제가 발생했습니다";
			url = request.getContextPath() + "/board/wirteForm";
		}
		return bfs.getMessage(msg, url); //스크립트 만들어주는 역할
	}
	
	private void upHit(int writeNo) {
		mapper.upHit(writeNo);
	}
	
	public void contentView(int writeNo, Model model) {
		upHit(writeNo);
		model.addAttribute("dto", mapper.contentView(writeNo) ); //한명에 대한 정보를 가져옴
	}
	
	public void modify_form(int writeNo, Model model) {
		model.addAttribute("dto", mapper.contentView(writeNo) );
	}
	
	public String modify(MultipartHttpServletRequest mul, HttpServletRequest request) {
		BoardDTO dto = new BoardDTO();
		dto.setWriteNo( Integer.parseInt(mul.getParameter("writeNo")) );
		dto.setTitle( mul.getParameter("title") );
		dto.setContent( mul.getParameter("content") );
		
		MultipartFile file = mul.getFile("imageFileName");
		if( file.getSize() != 0) { //이미지 변경
			dto.setImageFileName( bfs.saveFile(file) ); //새로운 이미지 이름으로 변경
			bfs.deleteImage( mul.getParameter("originFileName") ); //이미지 변경시 원본 이미지 삭제
		}else { //이미지 변경되지 않음
			dto.setImageFileName( mul.getParameter("originFileName") ); //원본 이미지값을 넣어줌
		}
		int result = mapper.modify( dto ); //수정 성공시 1 돌아옴, 실패시 0 돌아옴
		String msg, url;
		if(result == 1) { //저장 성공
			msg = "성공적으로 수정되었습니다";
			url = request.getContextPath() + "/board/contentView?writeNo=" + dto.getWriteNo();
		}else { //문제 발생
			msg = "수정 중 문제 발생!!!!";
			url = request.getContextPath() + "/board/modify_form?writeNo=" + dto.getWriteNo();
		}
		return bfs.getMessage(msg, url);
	}
	
	public String delete(int writeNo, String imageFileName, HttpServletRequest request) {
		int result = mapper.delete( writeNo ); //삭제 성공시 1, 실패시 0 돌아옴
		
		String msg, url;
		if( result == 1) { //삭제 성공
			bfs.deleteImage(imageFileName); //이미지 삭제
			msg = "성공적으로 삭제 되었습니다!!!";
			url = request.getContextPath() + "/board/boardAllList";
		}else {
			msg = "삭제 실패!!!";
			url = request.getContextPath() + "/board/contextView?writeNo=" + writeNo;
		}
		return bfs.getMessage(msg, url);
	}
	
}
