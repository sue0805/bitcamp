package user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;

public class EmpDAO {
	private PreparedStatement pstmt = null;
	private ResultSet rs;
	
	public List<Emp> selectEmp(Connection conn){
		List<Emp> list = new ArrayList<>();
		
		String query = "SELECT * FROM EMP";
		
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Emp e = new Emp(rs.getString(1), rs.getString(2)
						,rs.getString(3),rs.getString(4),rs.getString(5)
						,rs.getString(6),rs.getString(7),rs.getString(8));
				list.add(e);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return list;
	}
	
	public int insertEmp(Connection conn, Emp e) {
		int result = -1;
		
		String query = "INSERT INTO EMP VALUES(?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(e.getEmpno()));
			pstmt.setString(2, e.getEname());
			pstmt.setString(3, e.getJob());
			pstmt.setInt(4, Integer.parseInt(e.getMgr()));
			pstmt.setDate(5, Date.valueOf(e.getHiredate()));
			pstmt.setInt(6, Integer.parseInt(e.getSal()));
			pstmt.setInt(7, Integer.parseInt(e.getComm()));
			pstmt.setInt(8, Integer.parseInt(e.getDeptno()));
			
			result = pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return result;
	}
	
	public int updateEmp(Connection conn, Emp e, int empno) {
		int result = -1;
		String query = "UPDATE EMP SET EMPNO=?, ENAME=?, JOB=?, MGR=?, HIREDATE=?, SAL=?, COMM=?, DEPTNO=? WHERE EMPNO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(e.getEmpno()));
			pstmt.setString(2, e.getEname());
			pstmt.setString(3, e.getJob());
			pstmt.setInt(4, Integer.parseInt(e.getMgr()));
			pstmt.setDate(5, Date.valueOf(e.getHiredate()));
			pstmt.setInt(6, Integer.parseInt(e.getSal()));
			pstmt.setInt(7, Integer.parseInt(e.getComm()));
			pstmt.setInt(8, Integer.parseInt(e.getDeptno()));
			
			pstmt.setInt(9, Integer.parseInt(e.getEmpno()));
			result = pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	public int deleteEmp(Connection conn, String empno) {
		int result = -1;
		
		String query = "DELETE FROM EMP WHERE EMPNO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(empno));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return result;
	}
}
