package com.care.root;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.care.root.main.mainController.MainController;
import com.care.root.main.mainService.MainService;
import com.care.root.main.memberDAO.MemberDAO;
import com.care.root.main.memberDTO.MemberDTO;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {
		"classpath:TestMember.xml"
		,"file:src/main/webapp/WEB-INF/spring/root-context.xml"//이게 잇어야dao잇어야함
})
public class TestMember {
	@Autowired MainController mc;
	@Autowired MainService ms;
	@Autowired MemberDAO dao;
	@Test
	public void testMc() {
		assertNotNull(mc);
	}
	@Test
	public void testMs() {
		assertNotNull(ms);
	}
	
	@Test
	public void testDao() {
		assertNotNull(dao);
	}
	
	@Test
	public void testDaoIns() {
		MemberDTO dto = new MemberDTO();
		dto.setId("11");dto.setPwd("a");dto.setName("a");dto.setAddr("a");
		dao.insert(dto);
	}
	@Test
	public void testDaoMemberView() {
		dao.list();
	}
	
	@Test
	public void testDaoSelectId() {
		MemberDTO dto = new MemberDTO();
		dto.setId("11");
		dao.selectId(dto.getId());
	}
}
