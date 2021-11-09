package com.care.root.main.mainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.main.memberDAO.MemberDAO;
import com.care.root.main.memberDTO.MemberDTO;

@Service
public class MainServiceImpl implements MainService{
	@Autowired MemberDAO dao;

	@Override
	public void insert(MemberDTO dto) {
		dao.insert(dto);
		
	}

	@Override
	public void list(Model model) {
		model.addAttribute("list",dao.list());
		
	}

	@Override
	public int chk(String id, String pwd) {
		MemberDTO dto = dao.selectId(id);
		if(pwd.equals(dto.getPwd())) {
			System.out.println("ok");
			return 1;
		}else {
			System.out.println("no");
			return 0;
		}
		
	}
}
