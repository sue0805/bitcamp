package com.sue.open.member.service;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sue.open.mapper.MemberMapper;
import com.sue.open.member.Member;
import com.sue.open.security.Aes256;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper mapper;
	
	@Inject
	private Aes256 aes;
	
	public boolean login(String id, String password) {
		String pw = "";
		
		Member member = null;
		try {
			member = mapper.selectById(aes.encrypt(id));
			member.setId(aes.decrypt(member.getId()));
			member.setPassword(aes.decrypt(member.getPassword()));
			member.setName(aes.decrypt(member.getName()));
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(member != null) pw = member.getPassword();
		
		return pw.equals(password); 
	}
	
	public Member selectById(String id) {
		Member member = mapper.selectById(id);
		
		try {
			member.setId(aes.decrypt(member.getId()));
			member.setPassword(aes.decrypt(member.getPassword()));
			member.setName(aes.decrypt(member.getName()));
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		mapper.getList().forEach(member -> {
			try {
				member.setId(aes.decrypt(member.getId()));
				member.setPassword(aes.decrypt(member.getPassword()));
				member.setName(aes.decrypt(member.getName()));
			} catch (UnsupportedEncodingException | GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.add(member);});
		
		return list;
	}

	@Override
	public boolean delete(int idx) {
		boolean result;
				
		result = mapper.delete(idx) == 1;
		
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
		return rowCnt == 1;
	}

	@Override
	public boolean statusOK(String authCode, String id) {
		
		int rowCnt = 0;
		
		try {
			rowCnt = mapper.statusOK(authCode, aes.encrypt(id));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return rowCnt == 1;
	}

}
