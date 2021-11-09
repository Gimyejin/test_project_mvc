package com.care.root.main.mainService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.main.memberDAO.MemberDAO;
import com.care.root.main.memberDTO.MemberDTO;

@Service
public class MainServiceImpl implements MainService{
	@Autowired MemberDAO dao;
	MemberDTO dto;

	@Override
	public void insert(MemberDTO dto) {
		dao.insert(dto);
		
	}

	@Override
	public void list(Model model) {
		model.addAttribute("list",dao.list());
		
	}

	@Override
	public MemberDTO chk(String id, String pwd,HttpSession session) {
		dto = dao.selectId(id);
		if(pwd.equals(dto.getPwd())) {
			session.setAttribute("dto", dao.selectId(id));
			return dto;
		}else {
			System.out.println("no");
			return null;
		}
		
	}

}
