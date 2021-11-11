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

import com.care.root.common.MemberSessionName;
import com.care.root.main.mainService.MainService;
import com.care.root.main.memberDTO.MemberDTO;

@Controller
public class MainController implements MemberSessionName {
	@Autowired
	MainService ms;

	@GetMapping("main")
	public String main() {
		System.out.println("컨트롤러의 index실행");
		return "default/main";
	}

	@RequestMapping("membership")
	public String memberShip(Model model,HttpSession session) {
		//if(session.getAttribute(LOGIN) != null) {
			ms.list(model);
			return "member/membership";
		//}else {
		//	return "redirect:login";
		//}
		
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
		int result = ms.insert(dto);
		if (result == 1) {
			return "redirect:main";
		} else {
			return "redirect:insert";
		}
	}

	@GetMapping("login")
	public String getLogin() {
		return "login/login";
	}

	@PostMapping("loginChk")
	public String idChk(Model model, @RequestParam("id") String id, @RequestParam("pwd") String pwd,
			HttpSession session, MemberDTO dto) {
		dto = ms.chk(id, pwd, session);
		if (dto != null) {
			System.out.println(dto.getName());
			session.setAttribute(LOGIN, id);// MemberSessionName.LOGIN과 "loginUser은 같음 또한 LOGIN도 같음(상속이 필요)
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
		if (session.getAttribute(LOGIN) != null)
			session.invalidate();
		return "login/logout";// redirect:/index해도 됨
	}

	@RequestMapping("mypage")
	public String myPage() {
		return "member/mypage";
	}
}
