package Ewok;

public class GlobalConfigure {
//	private static final String[] TYPE_OF_DB = {"MEM", "FILE", "MYSQL", "CASSANDRA", "MONGODB"};
	public static enum TYPE_OF_DB {MEM, FILE, MYSQL, CASSANDRA, MONGODB};
	private static final TYPE_OF_DB SELECTED_DB = TYPE_OF_DB.MEM;
	
	public static TYPE_OF_DB getSelectedDb() {
		return SELECTED_DB;
	}
	
	
	
}
