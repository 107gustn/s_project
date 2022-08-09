package com.care.root.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
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
	public void writeSave(MultipartHttpServletRequest mul, HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		String message = bs.writeSave(mul, request); //request.getContextPath()를 사용하기위해 request값을 넘겨줌
		
		response.setContentType("text/html; charser=utf-8");
		PrintWriter out = response.getWriter();
		out.print(message);
	}
}
