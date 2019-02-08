package com.sue.open;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sue.open.member.Member;
import com.sue.open.member.service.MemberService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private MemberService service;
	
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
			session.setAttribute("login", service.selectById(id));
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
		String uploadFolder = "/upload";
		String dir = request.getSession().getServletContext().getRealPath(uploadFolder);
		
		Member member = new Member();
		member.setId(id);
		member.setPassword(password);
		member.setName(name);
		member.setPhoto(photo[0].getOriginalFilename());
		
		boolean result = service.signup(member);
		view = result ? "signup/signupOK" : "signup/signup";
		
		if(!result) model.addAttribute("msg", "회원가입 실패");
		else {
			member = service.selectById(id);
			File saveFile = new File(dir, photo[0].getOriginalFilename());
			
			try {
				photo[0].transferTo(saveFile);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return view;
	}
	
	@RequestMapping("/upload")
	public String upload(HttpServletRequest request) {
		String uploadURI = "/upload";
		String dir = request.getSession().getServletContext().getRealPath(uploadURI);
		System.out.println(dir);
		
		return "home";
	}
}
