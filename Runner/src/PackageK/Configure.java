package PackageK;

public class Configure {
	static public final String JDBC_URL = "jdbc:mysql://localhost:3333/webcrawler_v01?characterEncoding=utf8";
	static public final String DB_NAME = "webcrawler_v01";
	static public final String USER_ID = "root";
	static public final String USER_PASS = "1q2w3e";
	
	static public final int HEART_BEAT_PORT = 50001;
	static public final int HEART_BEAT_LIMIT = 20 * (60 * 1000);
}
