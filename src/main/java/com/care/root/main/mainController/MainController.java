package com.care.root.main.mainController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.root.main.mainService.MainService;
import com.care.root.main.memberDTO.MemberDTO;

@Controller
public class MainController {
	@Autowired
	MainService ms;

	@RequestMapping("main")
	public String main() {
		return "default/main";
	}

	@RequestMapping("membership")
	public String memberShip(Model model) {
		ms.list(model);
		return "member/membership";
	}

	@GetMapping("insert")
	public String getInsert() {
		return "member/insert";
	}

	@PostMapping("insert")
	public String postInsert(HttpServletRequest request, MemberDTO dto) {
		dto.setId(request.getParameter("id"));
		dto.setPwd(request.getParameter("pwd"));
		dto.setName(request.getParameter("name"));
		dto.setAddr(request.getParameter("addr"));
		ms.insert(dto);
		return "redirect:main";
	}

	@GetMapping("login")
	public String getLogin() {
		return "login/login";
	}

	@PostMapping("loginChk")
	public String idChk(Model model,@RequestParam("id") String id, @RequestParam("pwd") String pwd, HttpSession session,MemberDTO dto) {
		dto = ms.chk(id, pwd,session);
		if (dto != null) {
			System.out.println(dto.getName());
			session.setAttribute("id", id);
			session.setAttribute("pwd", dto.getPwd());
			session.setAttribute("name", dto.getName());
			session.setAttribute("addr", dto.getAddr());
			return "redirect:login";
		} else {
			return "redirect:login";
		}
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login/logout";
	}

	@RequestMapping("mypage")
	public String myPage() {
		return "member/mypage";
	}
}
