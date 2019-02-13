package com.sue.open;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sue.open.mail.MailSendService;
import com.sue.open.member.Member;
import com.sue.open.member.service.MemberService;
import com.sue.open.security.Aes256;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class JoinRestController {
	
	@Autowired
	private MemberService service;
	
	@Inject
	private MailSendService mailService;
	
	@Inject
	private Aes256 aes;

	@PostMapping(value="/join")
	public ResponseEntity<String> test(MultipartFile photo, String id, String name, String password, HttpServletRequest request){
		log.info("j");
		log.info(photo.getOriginalFilename());
		log.info(id+name+password);
		
		String uploadFolder = "/resources/upload";
		String dir = request.getSession().getServletContext().getRealPath(uploadFolder);
		String authCode = "";
		
		Member member = new Member();
		member.setId(id);
		member.setPassword(password);
		member.setName(name);
		member.setPhoto(photo.getOriginalFilename());
		
		boolean result = service.signup(member);
		
		String msg = "Sign Up Success! ";
		if(result) {
			try {
				member = service.selectById(aes.encrypt(id));
				authCode = member.getAuthCode() + "";
			} catch (UnsupportedEncodingException | GeneralSecurityException e1) {
				e1.printStackTrace();
			}
			File saveFile = new File(dir, member.getPhoto());
			
			try {
				photo.transferTo(saveFile);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				mailService.mailSend(id, authCode);
				msg = "1";
			} catch(Exception e) {
				msg = "2";
			}
			
		}
		
		return result ? new ResponseEntity<>(msg, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
