package com.sue.open;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sue.open.member.Member;
import com.sue.open.member.service.MemberService;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MyBatisTests {

	@Autowired
	private MemberService service;
	
	@Test
	public void serviceTest() {
		
		List<Member> list = service.getList();
		list.forEach(member -> log.info(member));
		
	}
}
