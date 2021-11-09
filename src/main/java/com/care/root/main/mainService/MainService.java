package com.care.root.main.mainService;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.care.root.main.memberDTO.MemberDTO;

public interface MainService {

	void insert(MemberDTO dto);

	void list(Model model);

	MemberDTO chk(String id, String pwd,HttpSession session);


}
