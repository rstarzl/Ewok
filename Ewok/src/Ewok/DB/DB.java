package Ewok.DB;

import java.util.concurrent.Callable;

import Ewok.GlobalConfigure;
//
//
//
//  @ Project : Ewok
//  @ File Name : DB.java
//  @ Date : 2014-09-29
//  @ Author : Member
//
//




public class DB implements Callable {
	private PhysicalDB dbPoint;
	private CacheTable cacheTable;
	
	public DB(String rootName){
		/**
		 * Setting currunt selected DB .
		 */
		switch (GlobalConfigure.getSelectedDb()){
		case MEM:
			dbPoint = new MemPhysicalDB(rootName);
			break;
		case FILE:
		case MYSQL:
		case CASSANDRA:
		case MONGODB:
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
	
	public boolean add(DBEntry	content) {
		return dbPoint.add(content);
	}
	
	public boolean delete(DBEntry	content) {
		return	dbPoint.delete(content);
	}
	
	public DBEntry query(String	key) {
		return dbPoint.query(key);
	}
}
