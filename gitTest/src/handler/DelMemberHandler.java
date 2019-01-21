package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.connection.ConnectionProvider;
import user.UserDAO;

public class DelMemberHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int idx = Integer.parseInt(request.getParameter("idx"));

		UserDAO dao = new UserDAO();
		int row = dao.removeUser(ConnectionProvider.getConnection(), idx); 
				
		if(row == -1) {
			request.setAttribute("msg", "삭제 실패");
		} else {
			request.setAttribute("msg", "삭제 성공");
		}
		
		return "/memberList.do";
	}

}
