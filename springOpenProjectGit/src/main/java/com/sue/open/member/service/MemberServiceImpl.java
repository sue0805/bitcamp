package com.sue.open.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sue.open.mapper.MemberMapper;
import com.sue.open.member.Member;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper mapper;
	
	public boolean login(String id, String password) {
		String pw = "";
		
		Member member = mapper.selectById(id);
		
		if(member != null) pw = member.getPassword();
		
		return pw.equals(password); 
	}
	
	public Member selectById(String id) {
		Member member = mapper.selectById(id);
		
		return member;
	}
	
	public boolean signup(Member member) {
		
		try {
			mapper.insertMember(member);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public List<Member> getList() {
		List<Member> list = new ArrayList<>();
		mapper.getList().forEach(member -> list.add(member));
		
		return list;
	}

	@Override
	public boolean delete(int idx) {
		boolean result;
				
		result = mapper.delete(idx) == 1 ? true : false;
		
		return result;
	}

	@Override
	public boolean modify(Member member) {
		
		int rowCnt = 0;
		
		try {
			rowCnt = mapper.update(member);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return rowCnt == 1 ? true : false;
	}

}
