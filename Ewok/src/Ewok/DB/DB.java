package Ewok.DB;

import net.sourceforge.htmlunit.corejs.javascript.tools.shell.Global;
import Ewok.GlobalConfigure;
import Ewok.RegionFilter.HTMLContent;
//
//
//
//  @ Project : Ewok
//  @ File Name : DB.java
//  @ Date : 2014-09-29
//  @ Author : Member
//
//




public class DB {
	private PhysicalDB dbPoint;
	private CacheTable cacheTable;
	
	public DB(){
		/**
		 * Setting currunt selected DB .
		 */
		switch (GlobalConfigure.getSelectedDb()){
		case MEM:
			dbPoint = new MemPhysicalDB();
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
	
	public boolean add(DBEntry	content) {
		return dbPoint.add(content);
	}
	
	public boolean delete(DBEntry	content) {
		return	dbPoint.delete(content);
	}
	
	public DBEntry query(DBEntry	content) {
		return dbPoint.query(content);
	}
}
