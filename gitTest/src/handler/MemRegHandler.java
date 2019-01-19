package handler;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.connection.ConnectionProvider;
import user.User;
import user.UserDAO;

public class MemRegHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String page = "";
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String photo = request.getParameter("photo");
		
		User userInfo = new User(id, password, name, photo);
		
		UserDAO dao = new UserDAO();
		int signup = dao.signUp(ConnectionProvider.getConnection(), userInfo);
		
		if(signup == -1){
			request.setAttribute("msg", "회원 가입 실패");
			page = "/member/memberRegister.jsp";
			RequestDispatcher rd = request.getRequestDispatcher("memberRegister.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("msg", "회원 가입 성공");
			page = "/member/login.jsp";
		}
		
		return page;
	}

}
