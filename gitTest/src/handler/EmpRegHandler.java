package handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.connection.ConnectionProvider;
import user.Emp;
import user.EmpDAO;

public class EmpRegHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String page = "/empList.do";
		
		String empno = request.getParameter("empno");
		String ename = request.getParameter("ename");
		String job = request.getParameter("job");
		String mgr = request.getParameter("mgr");
		String hiredate = request.getParameter("hiredate");
		String sal = request.getParameter("sal");
		String comm = request.getParameter("comm");
		String deptno = request.getParameter("deptno");
		
		int row = -1;
		
		Emp emp = new Emp(empno, ename, job, mgr, hiredate, sal, comm, deptno);
		
		EmpDAO dao = new EmpDAO();
		
    	try{
    		row = dao.insertEmp(ConnectionProvider.getConnection(), emp);
    	} catch (Exception e){
    		request.setAttribute("msg", "등록 실패");
    		e.printStackTrace();
    	}
    	if(row == -1){
    		page = "/emp/EmpRegister.jsp";
    	}
		
		return page;
	}

}
