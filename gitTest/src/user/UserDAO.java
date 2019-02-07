package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int signUp(Connection conn, User u) {
		int result = -1;
		
		String query = "INSERT INTO USER_INFO VALUES(?,?,?,?,seq_user_info.nextval, sysdate)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, u.getId());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getName());
			pstmt.setString(4, u.getPhoto());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return result;
	}
	
	public int removeUser(Connection conn, int idx) {
		int result = -1;
		
		String query = "delete from user_info where idx=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int updateUser(Connection conn, User u) {
		int result = -1;
		
		String query = "update user_info set id=?, password=?, name=?, photo=? where idx=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, u.getId());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getName());
			pstmt.setString(4, u.getPhoto());
			pstmt.setInt(5, u.getIdx());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	public List<User> getUserList(Connection conn){
		List<User> list = new ArrayList<>();
		
		String query = "SELECT * FROM USER_INFO order by idx asc";
		
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				User u = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6));
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return list;
	}
	
	public User userInfo(Connection conn, String id) {
		User u = null;
		
		String query = "SELECT * FROM USER_INFO WHERE ID=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				u = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDate(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return u;
	}
	
	public int login(Connection conn, String id, String password) {
		int result = -1;
		
		String query = "SELECT PASSWORD FROM USER_INFO WHERE ID=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String pw = rs.getString(1);
				if(pw.equals(password)) {
					result = 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return result;
	}
}
