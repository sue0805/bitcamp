package com.sue.open;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sue.open.member.Member;
import com.sue.open.member.service.MemberService;
import com.sue.open.security.Aes256;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SecurityTests {

	@Inject
	private MemberService service;
	
	@Inject
	private Aes256 aes;
	
	@Test
	public void aes256Test() {
		
		List<Member> list = service.getList();
		list.forEach(m -> {
			if(m.getIdx() != 28) {
				try {
					m.setId(aes.encrypt(m.getId()));
					m.setPassword(aes.encrypt(m.getPassword()));
					m.setName(aes.encrypt(m.getName()));
				} catch (UnsupportedEncodingException | GeneralSecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				service.modify(m);
				log.info(m);
			}
		});
	}
}
