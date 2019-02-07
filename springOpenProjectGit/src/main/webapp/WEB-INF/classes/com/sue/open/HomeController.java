package com.sue.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String login(Model model, @RequestParam("id") String id, @RequestParam("password") String password) {
		String view = "";
		
		boolean result = service.login(id, password);
		view = result ? "login/loginOK" : "login";
		
		if(!result) model.addAttribute("msg", "로그인 실패");
		
		return view;
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signup() {
		return "signup/signup";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(Member member) {
		return "signup/signupOK";
	}
}
