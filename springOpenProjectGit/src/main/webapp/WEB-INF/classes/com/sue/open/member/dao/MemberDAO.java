package com.sue.open.member.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sue.open.mapper.MemberMapper;
import com.sue.open.member.Member;

public class MemberDAO {
	
	@Autowired
	private MemberMapper mapper;
	
	public boolean login(String id, String password) {
		
		Member member = mapper.selectById(id);
		String pw = member.getPassword();
		
		return pw.equals(password); 
	}
	
	public boolean signup(Member member) {
		
		try {
			mapper.insertMember(member);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public List<Member> getList() {
		List<Member> list = new ArrayList<>();
		mapper.getList().forEach(member -> list.add(member));
		
		return list;
	}
	
}
