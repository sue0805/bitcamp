package handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.connection.ConnectionProvider;
import user.User;
import user.UserDAO;

public class MemberListHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		UserDAO dao = new UserDAO();  
		List<User> list = dao.getUserList(ConnectionProvider.getConnection());
		request.setAttribute("user", list);
		
		return "/member/memberList.jsp";
	}

}
