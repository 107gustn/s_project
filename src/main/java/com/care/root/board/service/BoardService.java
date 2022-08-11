package com.care.root.board.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardRepDTO;

public interface BoardService {
	
	public void boardAllList(Model model);
	public String writeSave(MultipartHttpServletRequest mul, HttpServletRequest request);
	
	public void contentView(int writeNo, Model model);
	public void modify_form(int writeNo, Model model);
	public String modify(MultipartHttpServletRequest mul, HttpServletRequest request);
	public String delete(int writeNo, String imageFileName, HttpServletRequest request);
	
	public void addReply( Map<String, String> map, String userId );
	public List<BoardRepDTO> getRepList(int write_group);

}
