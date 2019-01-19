package handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.connection.ConnectionProvider;
import user.Emp;
import user.EmpDAO;

public class EmpListHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		EmpDAO dao = new EmpDAO();
	    List<Emp> list = dao.selectEmp(ConnectionProvider.getConnection());
	    request.setAttribute("list", list);
	    	
		return "/emp/empList.jsp";
	}

}
