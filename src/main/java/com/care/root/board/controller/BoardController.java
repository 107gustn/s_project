package com.care.root.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.service.BoardFileService;
import com.care.root.board.service.BoardService;
import com.care.root.common.SessionName;


@Controller //컨트롤러 등록
@RequestMapping("board") //기본경로 설정
public class BoardController{
	
	@Autowired
	BoardService bs;
	
	@GetMapping("boardAllList")
	public String boardAllList(Model model) {
		bs.boardAllList(model);
		return "board/boardAllList";
	}
	
	@GetMapping("writeForm")
	public String writeForm() {
		return "board/writeForm";
	}
	
	@PostMapping("writeSave")
	public void writeSave(MultipartHttpServletRequest mul, HttpServletResponse response, HttpServletRequest request) throws Exception { //경로 리턴이 아닌 메세지 리턴
		
		String message = bs.writeSave(mul, request); //request.getContextPath()를 사용하기위해 request값을 넘겨줌
		
		response.setContentType("text/html; charser=utf-8");
		PrintWriter out = response.getWriter();
		out.print(message);
	}
	
	@GetMapping("contentView")
	public String contentView(int writeNo, Model model) {
		bs.contentView(writeNo, model);
		return "board/contentView";
	}
	
	@GetMapping("download")
	public void download(@RequestParam("imageFileName") String imageFileName, HttpServletResponse response) throws IOException { //넘어오는 값과 받는 값의 변수가 동일하면 @RequestParam 생략 가능
		response.addHeader("Content-disposition","attachment;fileName="+imageFileName); //(Content-disposition: 다운로드형식, attachment: 파일명을붙여서; 파일명 )
		File file = new File(BoardFileService.IMAGE_REPO+"/"+imageFileName);
		FileInputStream in = new FileInputStream(file);
		FileCopyUtils.copy(in, response.getOutputStream()); //(해당 경로로 읽어온 값을 , 사용자에게 전달해줌)
		in.close();
	}
	
	@GetMapping("modify_form")
	public String modify_form(int writeNo, Model model) {
		bs.modify_form(writeNo, model);
		return "board/modify_form";
	}
	
	@PostMapping("modify")
	public void modify(MultipartHttpServletRequest mul, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String message = bs.modify(mul, request); //메세지값으로 리턴시켜줌
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print( message );
	}
	
	@GetMapping("delete")
	public void dleete(int writeNo, String imageFileName, HttpServletResponse response, HttpServletRequest request) throws Exception { //HttpServletResponse: 메세지 전달시켜줌, HttpServletRequest: 메세지 만들어줌
		String msg = bs.delete(writeNo, imageFileName, request);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print( msg );
	}
	
}
