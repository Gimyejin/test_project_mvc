package com.care.root.main.mainService;

import org.springframework.ui.Model;

import com.care.root.main.memberDTO.MemberDTO;

public interface MainService {

	void insert(MemberDTO dto);

	void list(Model model);

	int chk(String id, String pwd);

}
