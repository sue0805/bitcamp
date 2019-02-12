package com.sue.open;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sue.open.member.Member;
import com.sue.open.member.service.MemberService;

@Controller
public class DownloadController{
	
	@Autowired
	private MemberService service;
	
	@RequestMapping("/down")
	public ModelAndView handleRequestInternal() {
		List<Member> members = service.getList();
		return new ModelAndView("members", "members", members);
	}
	
	@RequestMapping("/pdown")
	public ModelAndView handleRequestInternalP() {
		List<Member> members = service.getList();
		return new ModelAndView("pmembers", "members", members);
	}
}
