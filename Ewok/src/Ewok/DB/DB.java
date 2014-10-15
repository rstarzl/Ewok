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




public class DB implements Callable, PhysicalDB{
	private PhysicalDB dbPoint;
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
			dbPoint = new MySQLDB(rootName);
			break;
		case CASSANDRA:
			break;
		case MONGODB:
			break;
		case NON:
			
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
	
	
	@Override
	public boolean add(QueueEntry data){
		return	dbPoint.add(data);
	}
	@Override
	public boolean delete(QueueEntry data){
		return	dbPoint.delete(data);
	}
	@Override
	public ArrayList<QueueEntry> query(String queryURL){
		ArrayList<QueueEntry> ret = dbPoint.query(queryURL);
		if (ret.size() == 0){
			return null;
		}
		
		return	ret;
	}
}
