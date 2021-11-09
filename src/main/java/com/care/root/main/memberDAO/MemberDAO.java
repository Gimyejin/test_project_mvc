package com.care.root.main.memberDAO;

import java.util.ArrayList;

import com.care.root.main.memberDTO.MemberDTO;

//root-context.xml에 의해서 그 안에 만들어진 인터페이스를 알아서 빈으로 등록하기 때문에 어노테이션이 필요없음
public interface MemberDAO {
	public void insert(MemberDTO dto);

	public ArrayList<MemberDTO> list();

	public MemberDTO selectId(String id);

}
