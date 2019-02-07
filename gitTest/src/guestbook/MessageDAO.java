package guestbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jdbc.JdbcUtil;

public class MessageDAO {
	public static MessageDAO dao = new MessageDAO();
	public static MessageDAO getInstance() {
		return dao;
	}
	
	private MessageDAO() {}
	
	public int insert(Connection conn, Message message) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			String query = "insert into guestbook_message values(message_id_seq.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, message.getGuest_name());
			pstmt.setString(2, message.getPassword());
			pstmt.setString(3, message.getMessage());
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public Message select(Connection conn, int messageId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
			"select * from guestbook_message where message_id = ?");
			pstmt.setInt(1, messageId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return makeMessageFromResultSet(rs);
			} else {
				return null;
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int selectCount(Connection conn) throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String query = "select count(*) from guestbook_message";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} finally {
			JdbcUtil.close(rs);
			stmt.close();
		}
		return count;
	}
	
	private Message makeMessageFromResultSet(ResultSet rs) throws SQLException{
		Message msg = new Message();
		msg.setMessage_id(rs.getInt("message_id"));
		msg.setGuest_name(rs.getString("guest_name"));
		msg.setPassword(rs.getString("password"));
		msg.setMessage(rs.getString("message"));
		
		return msg;
	}
	
	public List<Message> selectList(Connection conn, int firstRow, int endRow) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String query = "select message_id, guest_name, password, message from ( "
					+ " select rownum rn, message_id, guest_name, password, message from ( "
					+ " select * from guestbook_message m order by m.message_id desc "
					+ " ) where rownum <= ? ) where rn >= ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, firstRow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				List<Message> list = new ArrayList<Message>();
				do {
					list.add(makeMessageFromResultSet(rs));
				} while(rs.next());
				return list;
			} else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int delete(Connection conn, int messageId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String query = "delete from guestbook_message where message_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, messageId);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
