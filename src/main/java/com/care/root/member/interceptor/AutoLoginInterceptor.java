package com.care.root.member.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.care.root.common.MemberSessionName;
import com.care.root.main.mainService.MainService;
import com.care.root.main.memberDTO.MemberDTO;

public class AutoLoginInterceptor extends HandlerInterceptorAdapter implements MemberSessionName {
	@Autowired MainService ms;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		//loginCookie라는 이름의 쿠키를 가져옴
		System.out.println("loginCookie: "+loginCookie);
		if(loginCookie != null) {
			MemberDTO dto = ms.getUserSessionId(loginCookie.getValue());
			if(dto != null) {
				request.getSession().setAttribute(LOGIN, dto.getId());
			}
		}
		return true;
	}

}
