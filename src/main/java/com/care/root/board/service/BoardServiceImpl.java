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
	
}
