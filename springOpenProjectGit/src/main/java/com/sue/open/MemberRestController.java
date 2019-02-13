package com.sue.open;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sue.open.mail.MailSendService;
import com.sue.open.member.Member;
import com.sue.open.member.service.MemberService;
import com.sue.open.security.Aes256;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/member")
@Log4j
public class MemberRestController {
	
	@Autowired
	private MemberService service;
	
	@Inject
	private MailSendService mailService;
	
	@Inject
	private Aes256 aes;
	
	@PostMapping(value="/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody Member member){
		boolean result = service.signup(member);
		log.info("insert result : " + result);
		
		return result ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(value="/test001")
	public ResponseEntity<String> test(MultipartFile photo, String id, String name, String password, HttpServletRequest request){
		log.info("ins");
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
		
		String msg = "";
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
	
	@GetMapping(value="/list", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
	public ResponseEntity<List<Member>> getList(){
		return new ResponseEntity<>(service.getList(), HttpStatus.OK);
	}
	
	@GetMapping(value="/info", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
	public ResponseEntity<Member> getInfo(HttpSession session){
		Member m = (Member)session.getAttribute("login");
		String id = "";
		try {
			id = aes.encrypt(m.getId());
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(service.selectById(id), HttpStatus.OK);
	}

	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH} ,value="/{idx}"
			, consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody Member member, @PathVariable("idx") int idx){
		log.info(member);
		member.setIdx(idx);
		
		return service.modify(member) ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value="/{idx}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("idx") int idx){
		return service.delete(idx) ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
