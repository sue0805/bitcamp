package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.service.DeleteMessageService;

public class GuestbookDeleteHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int message_id = Integer.parseInt(request.getParameter("message_id"));
		String password = request.getParameter("password");
		boolean invalidPassword = false;
		try {
			DeleteMessageService deleteService = DeleteMessageService.getInstance();
			deleteService.deleteMessage(message_id, password);
		} catch(Exception e) {
			invalidPassword = true;
		}
		
		if(invalidPassword) {
			request.setAttribute("msg", "암호가 틀립니다.");
		} else {
			request.setAttribute("msg", "삭제 성공");
		}
		
		return "/guestbookList.do";
	}

}
