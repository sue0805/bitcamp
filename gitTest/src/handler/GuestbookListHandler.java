package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.MessageListView;
import guestbook.service.GetMessageListService;

public class GuestbookListHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String pageNumberStr = request.getParameter("page");
		int pageNumber = 1;
		if(pageNumberStr != null){pageNumber = Integer.parseInt(pageNumberStr);}
		
		GetMessageListService service = GetMessageListService.getInstance();
		MessageListView viewData = service.getMessageList(pageNumber);
		
		request.setAttribute("data", viewData.getMessageList().toArray());
		
		return "/guestbook/list.jsp";
	}

}
