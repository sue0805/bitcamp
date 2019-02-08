package com.sue.open;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sue.open.mapper.MemberMapper;
import com.sue.open.member.Member;
import com.sue.open.member.service.MemberService;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberServiceTests {
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private MemberMapper mapper;
	
//	@Test
//	public void getListTest() {
//		List<Member> list = service.getList();
//		list.forEach(mem -> log.info(mem));
//	}
	
	@Test
	public void updateTest() {
		Member m = service.selectById("hey");
		log.info(m);
		m.setName("헤이헤이");
		mapper.update(m);
		m = service.selectById("hey");
		log.info(m);
	}
}
