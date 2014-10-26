package PackageK;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccessDB {
	private Connection conn = null;
	
	public AccessDB(){
		
	}
	
	public void connect(){
		Statement stmt;
		String jdbcUrl = Configure.JDBC_URL;
		String DBName = Configure.DB_NAME;
		
		String userID = Configure.USER_ID;
		String userPass = Configure.USER_PASS;
		
		
		try {
			conn = DriverManager.getConnection(jdbcUrl, userID, userPass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			try{
				Class.forName("com.mysql.jdbc.Driver");
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
			stmt = conn.createStatement();
			stmt.execute("use "+DBName);
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public int getRowCount(){
		Statement stmt = null;
	    ResultSet rs = null;
	    int rowCount = -1;
	    try {
	      stmt = conn.createStatement();
	      rs = stmt.executeQuery("SELECT COUNT(*) FROM " + "content");
	      // get the number of rows from the result set
	      rs.next();
	      rowCount = rs.getInt(1);
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    
	    return	rowCount;
	}
}
