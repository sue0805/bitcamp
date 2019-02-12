package com.sue.open;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sue.open.mail.MailSendService;
import com.sue.open.member.Member;

@Controller
@RequestMapping("/mail")
public class MailSenderController {
	
	@Inject
	private MailSendService mailService;

	@RequestMapping("/simplemailsend")
	public String simpleMailSend(HttpServletRequest request) {
		
		Member m = (Member)request.getSession().getAttribute("login");
		System.out.println(m);
		String email = m.getId();
		String authCode = m.getAuthCode() + "";
		mailService.mailSend(email, authCode);
		
		return "mail/sendmailOK";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
