package Ewok.DB;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import com.mysql.jdbc.PreparedStatement;

import Ewok.GlobalContext;
import Ewok.Processor.QueueEntry;
import Ewok.Render.Article;

public class MySQLDB implements PhysicalDB{
	static private java.sql.Connection conn;
	
	private String 		tableName=null;
	private URLColumm 		uc = new URLColumm();
	private ContentColumn 	cc = new ContentColumn();
	
	
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
			stmt = conn.prepareStatement(cmd);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
//		System.out.println(data);
		
		return true;
	}

	public boolean delete(QueueEntry data){
		
		String query = "\""+data.getUrl().getUrl()+"\"";
		java.sql.PreparedStatement stmt;

		try {
			String cmd = "delete from " + tableName + " where url=" + query + ";";
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
			stmt = conn.createStatement();
			rs = stmt.executeQuery(cmd);
			
			if(tableName.equals("url")){
				while(rs.next()){
					QueueEntry QueueEntrytemp = new QueueEntry();
					QueueEntrytemp.setSiteURL(rs.getString(1));
					result.add(QueueEntrytemp);		
				}
			}else{
				while(rs.next()){
					QueueEntry QueueEntrytemp = new QueueEntry();
					Article articleTemp = new Article();
					QueueEntrytemp.setSiteURL(rs.getString(5));
					articleTemp.date = rs.getString(2);
					articleTemp.press = rs.getString(4);
					articleTemp.title = unescape(rs.getString(5));
					articleTemp.content = unescape(rs.getString(6));
					
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
		String jdbcUrl = GlobalContext.JDBC_URL;
		String DBName = GlobalContext.DB_NAME;
		
		String userID = GlobalContext.USER_ID;
		String userPass = GlobalContext.USER_PASS;
		
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
		String column = "(collectdate, newsdate, sitename, pressname, url, title, body, comments, crawlerversion)";
		String collectdate;
		String newsdate;
		String sitename;
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
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String collectdate="\""+df.format(new Date())+"\"";
		String newsdate="\""+data.getArticle().date+"\"";
		String sitename="\""+data.getSiteName()+"\"";
		String pressname="\""+data.getArticle().press+"\"";
		String url="\""+data.getUrl().getUrl()+"\"";	
		String title = escape(data.getArticle().title);
		title="\""+title+"\"";
		String body=escape(data.getArticle().content);
		body="\""+body+"\"";
		String comments="\"test\"";
		String crawlerversion="\"v01\"";
		
		String value = "("+collectdate+","+newsdate+","+sitename+","+pressname+","+url+","+title+","+body+","+comments+","+crawlerversion+")"; 
		
		return value;
	}

	private String escape(String text) {
		String result = StringEscapeUtils.escapeHtml4(text);
		return result;
	}
	
	private String unescape(String text) {
		String result = StringEscapeUtils.unescapeHtml4(text);
		return result;
	}

	private String getURLColumnValue(QueueEntry data) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		String url = "\""+data.getUrl().getUrl()+"\"";
		String sitename = "\""+data.getSiteName()+"\"";
		String time =  "\""+df.format(new Date())+"\"";
		
		String value = "("+url+","+sitename+","+time+")";
		
		return value;
	}
}
