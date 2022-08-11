package com.care.root.board.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface BoardService {
	
	public void boardAllList(Model model);
	public String writeSave(MultipartHttpServletRequest mul, HttpServletRequest request);
	
	public void contentView(int writeNo, Model model);
	public void modify_form(int writeNo, Model model);
	public String modify(MultipartHttpServletRequest mul, HttpServletRequest request);
	public String delete(int writeNo, String imageFileName, HttpServletRequest request);

}
