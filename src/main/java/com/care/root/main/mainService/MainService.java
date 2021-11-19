package com.care.root.main.mainService;

import java.sql.Date;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.care.root.main.memberDTO.MemberDTO;

public interface MainService {

	int insert(MemberDTO dto);

	void list(Model model);

	MemberDTO chk(String id, String pwd,HttpSession session);

	void keepLogin(String id, Date limDate, String id2);

	MemberDTO getUserSessionId(String sessionId);


}
