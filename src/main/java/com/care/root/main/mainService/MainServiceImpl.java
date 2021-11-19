package com.care.root.main.mainService;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.main.memberDAO.MemberDAO;
import com.care.root.main.memberDTO.MemberDTO;

@Service
public class MainServiceImpl implements MainService {
	@Autowired
	MemberDAO dao;
	MemberDTO dto;
	BCryptPasswordEncoder encoder;// 비밀번호 암호화

	public MainServiceImpl() {
		encoder = new BCryptPasswordEncoder();
	}

	@Override
	public int insert(MemberDTO dto) {
		System.out.println("비번 변경 전: " + dto.getPwd());
		String securePw = encoder.encode(dto.getPwd());
		System.out.println("비번 변경 후: " + securePw);
		dto.setPwd(securePw);
		int result = 0;
		try {
			result = dao.insert(dto);// 실패하면 reult=0이고 성공하면 result에 같이들어갈것임
			// try덕에 에러가 터져도 화면상으로는 잘 띄워짐
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public void list(Model model) {
		model.addAttribute("list", dao.list());

	}

	@Override
	public MemberDTO chk(String id, String pwd, HttpSession session) {
		dto = dao.selectId(id);
		if (encoder.matches(pwd,dto.getPwd())||pwd.equals(dto.getPwd())) {//pwd.equals(dto.getPwd())
							//앞쪽에는 사용자가 입력한 평문, 뒤쪽에는 암호화된 값 순서
			session.setAttribute("dto", dao.selectId(id));
			return dto;
		} else {
			System.out.println("no");
			return null;
		}

	}

	@Override
	public void keepLogin(String sessionId, Date limDate, String id) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sessionId", sessionId);
		map.put("limDate", limDate);
		map.put("id", id);
		dao.keepLogin(map);
		
	}

	@Override
	public MemberDTO getUserSessionId(String sessionId) {
		return dao.getUserSessionId(sessionId);
	}

}
