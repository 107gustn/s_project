package com.care.root.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.root.board.dto.BoardRepDTO;
import com.care.root.board.service.BoardService;
import com.care.root.common.SessionName;

@RestController //페이지를 보여주는것이 아닌 데이터를 받고 돌려주는 역할
@RequestMapping("board")
public class BoardRepController implements SessionName { //데이터만 받아서 저장하고 끝남
	
	@Autowired
	BoardService bs;
	
	@PostMapping(value = "addReply", produces = "application/json;charset=utf8")
	public void addReply(@RequestBody Map<String, String> map, HttpSession session) { //ajax의 데이터 값을 받아줌 //dto에 없는 변수도 있으므로 Map으로 처리
		bs.addReply( map, (String)session.getAttribute(LOGIN) );
	}
	
	@GetMapping(value = "replyData/{write_group}", produces = "application/json;charset=utf8")
	public List<BoardRepDTO> replyData(@PathVariable int write_group) {
		return bs.getRepList(write_group);
	}
}
