package com.sue.open.mapper;

import java.util.List;

import com.sue.open.member.Member;

public interface MemberMapper {
	public Member selectById(String id);
	
	public void insertMember(Member member);
	
	public List<Member> getList();
	
	public int delete(int idx);
	
	public int update(Member member);
}
