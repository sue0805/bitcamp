package com.sue.open.member.service;

import java.util.List;

import com.sue.open.member.Member;

public interface MemberService {
	public boolean login(String id, String password);
	public boolean signup(Member member);
	public List<Member> getList();
	public boolean delete(int idx);
	public boolean modify(Member member);
}
