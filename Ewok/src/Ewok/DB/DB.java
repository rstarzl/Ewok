package Ewok.DB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import Ewok.GlobalConfigure;
import Ewok.GlobalContext;
//
//
//
//  @ Project : Ewok
//  @ File Name : DB.java
//  @ Date : 2014-09-29
//  @ Author : Member
//
//
import Ewok.Processor.QueueEntry;




public class DB implements Callable {
	private PhysicalDB dbPoint;
	private MySQLDB MySQLPoint;
	private CacheTable cacheTable;
	
	public DB(String rootName){
		/**
		 * Setting currunt selected DB .
		 */
		switch (GlobalContext.getSelectedDb()){
		case MEM:
			dbPoint = new MemPhysicalDB(rootName);
			break;
		case FILE:
			break;
		case MYSQL:
			MySQLPoint = new MySQLDB(rootName);
			break;
		case CASSANDRA:
			break;
		case MONGODB:
			break;
		default : 
			System.out.println("Invalid DB.");
			System.exit(1);
			break;
		}
	}
	
	public Object call(){
		while(true){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* For MemDB. It must be refactory. */
	public boolean add(DBEntry	content) {
		return dbPoint.add(content);
	}
	
	public boolean delete(DBEntry	content) {
		return	dbPoint.delete(content);
	}
	
//	public DBEntry query(String	key) {
//		return dbPoint.query(key);
//	}
	/* For MemDB. It must be refactory. */
	
	
	/* For SQLDB. It must be refactory. */
	public boolean add(QueueEntry data){
		return	MySQLPoint.add(data);
	}
	public boolean delete(QueueEntry data){
		return	MySQLPoint.delete(data);
	}
	public ArrayList<QueueEntry> query(String queryURL){
		ArrayList<QueueEntry> ret = MySQLPoint.query(queryURL);
		if (ret.size() == 0){
			return null;
		}
		
		return	ret;
	}
	/* For SQLDB. It must be refactory. */
}
