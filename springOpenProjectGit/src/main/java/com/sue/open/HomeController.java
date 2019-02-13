package com.sue.open;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sue.open.mail.MailSendService;
import com.sue.open.member.Member;
import com.sue.open.member.service.MemberService;
import com.sue.open.security.Aes256;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private MemberService service;
	
	@Inject
	private MailSendService mailService;
	
	@Autowired
	private Aes256 aes;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "login/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model, @RequestParam("id") String id, @RequestParam("password") String password
			, HttpSession session) {
		String view = "";
		
		boolean result = service.login(id, password);
		view = result ? "login/loginOK" : "login/login";
		
		if(!result) model.addAttribute("msg", "로그인 실패");
		else {
			try {
				session.setAttribute("login", service.selectById(aes.encrypt(id)));
			} catch (UnsupportedEncodingException | GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return view;
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "home";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signup() {
		return "signup/signup";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(Model model, MultipartFile[] photo, @RequestParam("id") String id
			, @RequestParam("password") String password, @RequestParam("name") String name, HttpServletRequest request) {
		String view = "";
		String uploadFolder = "/resources/upload";
		String dir = request.getSession().getServletContext().getRealPath(uploadFolder);
		String authCode = "";
		
		Member member = new Member();
		member.setId(id);
		member.setPassword(password);
		member.setName(name);
		member.setPhoto(photo[0].getOriginalFilename());
		
		boolean result = service.signup(member);
		view = result ? "mail/sendmailOK" : "signup/signup";
		
		if(!result) model.addAttribute("msg", "회원가입 실패");
		else {
			try {
				member = service.selectById(aes.encrypt(id));
				authCode = member.getAuthCode() + "";
			} catch (UnsupportedEncodingException | GeneralSecurityException e1) {
				e1.printStackTrace();
			}
			File saveFile = new File(dir, member.getPhoto());
			
			try {
				photo[0].transferTo(saveFile);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			
			try {
				mailService.mailSend(id, authCode);
			} catch(Exception e) {
				model.addAttribute("msg", "이메일 발송이 실패했습니다. 메일 주소를 다시 확인해 주세요.");
			}
		}
		
		return view;
	}
	
	@RequestMapping("/upload")
	public String upload(HttpServletRequest request) {
		String uploadURI = "/resources/upload";
		String dir = request.getSession().getServletContext().getRealPath(uploadURI);
		System.out.println(dir);
		
		return "home";
	}
	
	@RequestMapping("/test")
	public String test() {
		return "test";
	}

}
