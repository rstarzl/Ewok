package Ewok.DB;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import Ewok.Processor.QueueEntry;
import Ewok.Render.Article;

public class MySQLDB {
	String 		tableName=null;
	URLColumm 		uc = new URLColumm();
	ContentColumn 	cc = new ContentColumn();
	static String DBName="webcrawler_v01";
	static java.sql.Connection conn;
	
	public MySQLDB(String tableName){
		//tableName : "url" or "content"
		super();
		this.tableName = tableName;
	}
	
	/**
	 * 
	 * @param QueueEntry
	 * @return 
	 */
	public boolean add(QueueEntry data){
		
		String query = data.getUrl().getUrl();
		java.sql.PreparedStatement stmt;
		String value=null;
		String cmd = null;
		
		if(tableName.equals("url")){
			value = getURLColumnValue(data);
			cmd = "insert into " + tableName +" "+uc.column+" values "+value+";";	
		}else{
			value = getContentColumnValue(data);
			cmd = "insert into " + tableName +" "+cc.column+" values "+value+";";
		}
		
		try {
			System.out.println(cmd);
			stmt = conn.prepareStatement(cmd);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public boolean delete(QueueEntry data){
		
		String query = "\""+data.getUrl().getUrl()+"\"";
		java.sql.PreparedStatement stmt;

		try {
			String cmd = "delete from " + tableName + " where url=" + query + ";";
			System.out.println(cmd);
			stmt = conn.prepareStatement(cmd);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public ArrayList<QueueEntry> query(String queryURL){
		ArrayList<QueueEntry> result = new ArrayList<QueueEntry>();
		String query = "\""+queryURL+"\"";
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		try {
			String cmd = "select * from " + tableName + " where url=" + query + ";";
			System.out.println(cmd);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(cmd);
			
			if(tableName.equals("URL")){
				while(rs.next()){
					QueueEntry QueueEntrytemp = new QueueEntry();
					URL urlTemp = new URL(rs.getString(1));
					QueueEntrytemp.setUrl(urlTemp);
					result.add(QueueEntrytemp);					
				}
			}else{
				while(rs.next()){
					QueueEntry QueueEntrytemp = new QueueEntry();
					URL urlTemp = new URL(rs.getString(4));
					Article articleTemp = new Article();
					
					articleTemp.date = rs.getString(2);
					articleTemp.press = rs.getString(3);
					articleTemp.title = rs.getString(5);
					articleTemp.content = rs.getString(6);
					
					QueueEntrytemp.setUrl(urlTemp);
					QueueEntrytemp.setArticle(articleTemp);
					result.add(QueueEntrytemp);					
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public static void connectMySQLDB(){
		java.sql.Statement stmt;
		String jdbcUrl = "jdbc:mysql://192.168.1.7:3306/test";
		String userID = "root";
		String userPass = "1q2w3e4r";
		
		try{
			conn = DriverManager.getConnection(jdbcUrl, userID, userPass);
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
	
	public class ContentColumn {
		String column = "(collectdate, newsdate, pressname, url, title, body, comments, crawlerversion)";
		String collectdate;
		String newsdate;
		String pressname;
		String url;
		String title;
		String body;
		String comments;
		String crawlerversion;
	}
	
	public class URLColumm {
		String column ="(url, portalname, time)";
		String url;
		String portalname;
		String time;
	}
	
	private String getContentColumnValue(QueueEntry data) {
		String collectdate="\"test\"";
		String newsdate="\""+data.getArticle().date+"\"";
		String pressname="\""+data.getSiteURL()+"\"";
		String url="\""+data.getUrl().getUrl()+"\"";
		String title="\""+data.getArticle().title+"\"";
		String body="\""+data.getArticle().content+"\"";
		String comments="\"test\"";
		String crawlerversion="\"v01\"";
		
		String value = "("+collectdate+","+newsdate+","+pressname+","+url+","+title+","+body+","+comments+","+crawlerversion+")"; 
		
		return value;
	}

	private String getURLColumnValue(QueueEntry data) {
		String url = "\""+data.getUrl().getUrl()+"\"";
		String sitename = "\""+data.getSiteURL()+"\"";
		String time = "\"test\"";
		
		String value = "("+url+","+sitename+","+time+")";
		
		return value;
	}
}
