package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.connection.ConnectionProvider;
import user.EmpDAO;

public class DelEmpHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String empno = request.getParameter("empno");

		EmpDAO dao = new EmpDAO();
		int row = dao.deleteEmp(ConnectionProvider.getConnection(), empno);
		
		if(row == -1) {
			request.setAttribute("msg", "삭제 실패");
		} else {
			request.setAttribute("msg", "삭제 성공");
		}
		
		return "/empList.do";
	}

}
