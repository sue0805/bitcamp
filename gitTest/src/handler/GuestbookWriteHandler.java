package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.Message;
import guestbook.service.WriteMessageService;

public class GuestbookWriteHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		Message message = new Message();
		message.setGuest_name(request.getParameter("guest_name"));
		message.setMessage(request.getParameter("message"));
		message.setPassword(request.getParameter("password"));
		
		WriteMessageService service = WriteMessageService.getInstance();
		service.write(message);
		
		return "/guestbookList.do";
	}
	
}
