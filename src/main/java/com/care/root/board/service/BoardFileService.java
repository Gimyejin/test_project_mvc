package com.care.root.board.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface BoardFileService {
	public static final String IMAGE_REPO="C:/spring/image_repo1";
	
	public String getMessage(int num, HttpServletRequest request);
	public String getMessage(HttpServletRequest request, String msg, String url);
	public String saveFile(MultipartFile file);
	public void deleteImage(String originFileName);
}
