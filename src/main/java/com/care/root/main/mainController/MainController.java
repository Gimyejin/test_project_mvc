package com.care.root.main.mainController;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String memberShip(Model model, HttpSession session) {
		// if(session.getAttribute(LOGIN) != null) {
		ms.list(model);
		return "member/membership";
		// }else {
		// return "redirect:login";
		// }

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
	public String getLogin(@RequestParam(required = false) String autologin, HttpSession session,
			HttpServletResponse response) {
		String id = (String) session.getAttribute(LOGIN);
		System.out.println("autologin " + autologin);
		if (autologin != null) {
			int limitTime = 60 * 60 * 24 * 90;// 90일
			Cookie loginCookie = new Cookie("loginCookie", session.getId());
			// session.getId()는 거의 유일한 값임 -> F12에 application의 value값임
			// sql의 session_id 는 value값을 저장함
			loginCookie.setPath("/");
			loginCookie.setMaxAge(limitTime);
			response.addCookie(loginCookie);

			Calendar cal = Calendar.getInstance();// 이걸 써야 시간이 제대로 들어가짐
			cal.setTime(new Date());// 유틸의 date
			cal.add(Calendar.MONTH, 3);// 현재시간에서 3개월 후가 저장

			java.sql.Date limDate = new java.sql.Date(cal.getTimeInMillis());
			// ->3개월 설정 성공
			ms.keepLogin(session.getId(), limDate, id);
		}
		return "login/login";
	}

	@PostMapping("loginChk")
	public String idChk(Model model, @RequestParam("id") String id, @RequestParam("pwd") String pwd,
			@RequestParam(required = false) String autologin, HttpSession session, MemberDTO dto,
			RedirectAttributes rs) {
		dto = ms.chk(id, pwd, session);
		if (dto != null) {
			System.out.println(dto.getName());
			session.setAttribute(LOGIN, id);// MemberSessionName.LOGIN과 loginUser은 같음 또한 LOGIN도 같음(상속이 필요)
			session.setAttribute("pwd", dto.getPwd());
			session.setAttribute("name", dto.getName());
			session.setAttribute("addr", dto.getAddr());

			rs.addAttribute("autologin", autologin);
			return "redirect:login";
		} else {
			return "redirect:login";
		}
	}

	@RequestMapping("logout")
	public String logout(HttpSession session, HttpServletResponse response
			,@CookieValue(value = "loginCookie", required = false) Cookie loginCookie
			) {
		if (session.getAttribute(LOGIN) != null) {
			// 사용자의 쿠키를 종료시켜야함
			if(loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);//사용자에게 쿠키를 전달
				ms.keepLogin("nan", new java.sql.Date(System.currentTimeMillis()), (String)session.getAttribute(LOGIN));//쿠키값, 시간설정, 비교id
				session.invalidate();				//System.currentTimeMillis()는 현재시간임
			}session.invalidate();
		}
		return "login/logout";// redirect:/index해도 됨
	}

	@RequestMapping("mypage")
	public String myPage() {
		return "member/mypage";
	}
}
