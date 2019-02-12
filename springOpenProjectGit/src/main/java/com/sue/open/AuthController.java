package com.sue.open;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sue.open.member.service.MemberService;

@Controller
public class AuthController {

	@Autowired
	private MemberService service;
	
	@RequestMapping("/mail/sendmail")
	public String sendmail() {
		return "/mail/sendmail";
	}
	
	@RequestMapping("/mail/{authCode}")
	public String authOK(Model model, @PathVariable("authCode") String authCode, @RequestParam("id") String id, HttpSession session) {
		boolean result = service.statusOK(authCode, id);
		if(result) {
			model.addAttribute("msg", "인증 성공, 다시 로그인 해 주세요.");
			session.invalidate();
		}else {
			model.addAttribute("msg", "인증 실패");
		}
		return "home";
	}
}
