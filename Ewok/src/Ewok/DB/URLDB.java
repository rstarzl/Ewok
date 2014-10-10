package Ewok.DB;

import java.util.ArrayList;

import Ewok.Processor.QueueEntry;

//
//
//
//  @ Project : Ewok
//  @ File Name : URLDB.java
//  @ Date : 2014-09-29
//  @ Author : Member
//
//




public class URLDB extends DB {
	private static final String rootName = "URLDB";
		
	public URLDB(){
		super(rootName);
	}
	
	public URLDB(String	tableName){
		super(tableName);
	}
	
//	public boolean addURL(URL url){
//		DBEntry dbEntry = new DBEntry(url.getNodeName(), null);
//		DBEntry dbEntry1 = new DBEntry(url.getNodeName(), url.get1stChildName(), url.get1stChildValue());
//		DBEntry dbEntry2 = new DBEntry(url.getNodeName(), url.get2ndChildName(), url.get2ndChildValue());
//		
//		dbEntry.addData(dbEntry1);
//		dbEntry.addData(dbEntry2);
//		
//		return add(dbEntry);
//	}
//	
//	public boolean deleteURL(URL url){
//		return this.delete(new DBEntry());
//	}
//	
//	public URL queryURLFromUrlString(String url){
//		return null;
//	}
	
	/* For SQLDB. It must be refactory. */
	public boolean addURL(URL url){
		QueueEntry	entry = new QueueEntry(url.getUrl());
//		entry.setUrl(url);
		return	super.add(entry);
	}
	
	public boolean deleteURL(URL url){
		QueueEntry	entry = new QueueEntry(url.getUrl());
//		entry.setUrl(url);
		return super.delete(entry);
	}
	
	public ArrayList<QueueEntry> queryURLFromUrlString(String url){
		return super.query(url);
	}
	/* For SQLDB. It must be refactory. */
	
	
	/**
	 * TODO : 미구현
	 * @param siteName
	 * @return
	 */
//	public ArrayList<URL> queryURLFromSiteName(String siteName){
//		return null;
//	}
}
