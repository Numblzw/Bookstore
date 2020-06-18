package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
	
	private final static String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String MYSQL_URL = "jdbc:mysql://localhost/bookstore?"
			+ "useUnicode=true&characterEncoding=GBK&"
			+ "serverTimezone=GMT";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "123456";
	
	private final static int MAX_USE = 16;
	
	private static DBManager instance = null;
	
	private static Connection conn = null;
	
	private int use;
	
	static {
		try {
			Class.forName(MYSQL_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private DBManager() {}
	
	public static DBManager getInstance() {
		if (instance == null) {
			doSync();
		}
		return instance;
	}
	
	public Connection getConnection() {
		if (conn == null) {
			getConnection(MYSQL_URL, USERNAME, PASSWORD);
			use = 0;
		}
		use++;
		
		return conn;
	}
	
	public void closeConnection() {
		if (use >= MAX_USE) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List query(String sql) {
		List list = new ArrayList();

		Connection connection = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			ResultSetMetaData rsm = rs.getMetaData();
			int col = rsm.getColumnCount();
			
			while (rs.next()) {
				Object[] obj = new Object[col];
				for (int i = 0; i < col; i++) {
					obj[i] = rs.getObject(i + 1);
				}
				list.add(obj);
				
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection();
		}
		
		return list;
	}
	
	public int update(String sql) {
		int rows = -1;
		
		Connection connection = getConnection();
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			rows = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection();
		}
		return rows;
	}
	
	private synchronized static void doSync() {
		if (instance == null) {
			instance =  new DBManager();
		}
	}
	
	private synchronized void getConnection(String url, String username, String password) {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
