package com.sue.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String write(Model model, Guestbook guestbook, Criteria cri) {
		boolean result = service.insertContent(guestbook);
		
		if(!result) {
			model.addAttribute("msg", "작성 실패");
			return "/guestbook/write";
		} else {
			return list(cri, model);
		}
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@RequestParam("no") int no, Model model) {
		model.addAttribute("no", no);
		
		return "/guestbook/delete";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(Model model, @RequestParam("no") int no, @RequestParam("gpassword") String gpassword, Criteria cri) {
		
		
		boolean result = service.checkPW(no, gpassword);
		String msg = "삭제 실패";
		
		if(result) {
			boolean result2 = service.delete(no);
			if(result2) msg = "삭제 성공";
		}
		
		model.addAttribute("msg", msg);
		
		return list(cri, model);
	}
}
