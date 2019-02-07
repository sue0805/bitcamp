package com.sue.open.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sue.open.member.Member;
import com.sue.open.member.dao.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO dao;
	
	@Override
	public boolean login(String id, String password) {
		return dao.login(id, password);
	}

	@Override
	public boolean signup(Member member) {
		return dao.signup(member);
	}

	@Override
	public List<Member> getList() {
		return dao.getList();
	}

	@Override
	public boolean delete(int idx) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modify(Member member) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Member selectById(String id) {
		return dao.selectById(id);
	}

}
