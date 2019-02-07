package jdbc;

import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * Servlet implementation class DBCPInit
 */
@WebServlet("/DBCPInit")
public class DBCPInit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	public void init() throws ServletException {
		loadJDBCDriver();
		initConnectionPool();
	}
	
	public void loadJDBCDriver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("fail to load JDBC Driver", e);
		}
	}
	
	private void initConnectionPool() {
		try {
			String jdbcDriver = "jdbc:oracle:thin:@localhost:1521:xe";
			String username = "student";
			String pw = "1234";
			
			ConnectionFactory conFac = new DriverManagerConnectionFactory(jdbcDriver, username, pw);
			
			PoolableConnectionFactory poolConFac = new PoolableConnectionFactory(conFac, null);
			poolConFac.setValidationQuery("select 1");
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L*60L*5L);
			poolConfig.setTestWhileIdle(true);
			
			poolConfig.setMinIdle(4);
			poolConfig.setMaxTotal(50);
			
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolConFac, poolConfig);
			poolConFac.setPool(connectionPool);
			
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			
			PoolingDriver driver = (PoolingDriver)DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			driver.registerPool("guestbook", connectionPool);
			System.out.println("initPool Connect Success");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
