package com.sue.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sue.open.guestbook.Criteria;
import com.sue.open.guestbook.Guestbook;
import com.sue.open.guestbook.Page;
import com.sue.open.guestbook.service.GuestbookService;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService service;
	
	@RequestMapping("/list")
	public String list(Criteria cri, Model model) {
		cri.calcStartNum();
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("page", new Page(cri, service.countList()));
		return "/guestbook/list";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() {
		return "/guestbook/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(Model model, Guestbook guestbook) {
		String view = "";
		boolean result = service.insertContent(guestbook);
		
		if(!result) {
			model.addAttribute("msg", "작성 실패");
			view = "/guestbook/write";
		} else {
			view = "/guestbook/list";
		}
		
		return view;
	}
}
