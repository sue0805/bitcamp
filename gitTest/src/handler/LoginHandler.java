package handler;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.connection.ConnectionProvider;
import user.User;
import user.UserDAO;

public class LoginHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		
		UserDAO dao = new UserDAO();
		String id = (String)request.getParameter("id");
		String password = (String)request.getParameter("password");
		String page = "";
		
		boolean login = dao.login(ConnectionProvider.getConnection(), id, password) == -1 ? false : true;
		if(!login){
			request.setAttribute("msg", "로그인 실패");
			page = "/member/login.jsp";
		} else {
			User u = dao.userInfo(ConnectionProvider.getConnection(), id);
			HttpSession session = request.getSession();
			session.setAttribute("login", u);
			page = "/member/loginSuccess.jsp";
		}
		
		
		return page;
	}

}
