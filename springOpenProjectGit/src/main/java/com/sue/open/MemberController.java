package com.sue.open;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sue.open.member.Member;
import com.sue.open.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@RequestMapping("/mypage")
	public String mypage() {
		return "/member/mypage";
	}
	
	@RequestMapping("/memberList")
	public String memberList(Model model) {
		
		List<Member> list = service.getList();
		model.addAttribute("list", list);
		
		return "/member/memList";
	}
	
}
