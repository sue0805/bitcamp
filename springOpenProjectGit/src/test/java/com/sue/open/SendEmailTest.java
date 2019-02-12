package com.sue.open;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sue.open.member.Member;
import com.sue.open.member.service.MemberService;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value= {"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
@WebAppConfiguration
public class SendEmailTest {
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private MemberService service;
	
	
//	@Test
//	public void sendEmail() {
//		SimpleMailMessage simpleMessage = new SimpleMailMessage();
//		simpleMessage.setSubject("[공지] 테스트 메일 발송");
//		simpleMessage.setText("tesT");
//		simpleMessage.setTo("luckiness05@naver.com");
//		simpleMessage.setFrom("test@test.com");
//		
//		// 메일 발송
//		mailSender.send(simpleMessage);
//	}
	
	@Test
	public void getAuthCode() {
		Member m = service.selectById("luckiness05@naver.com");
		System.out.println(m);
	}
}
