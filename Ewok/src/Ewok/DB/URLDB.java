package Ewok.DB;

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
	
	public boolean addURL(URL url){
		DBEntry dbEntry = new DBEntry(url.getNodeName(), null);
		DBEntry dbEntry1 = new DBEntry(url.getNodeName(), url.get1stChildName(), url.get1stChildValue());
		DBEntry dbEntry2 = new DBEntry(url.getNodeName(), url.get2ndChildName(), url.get2ndChildValue());
		
		dbEntry.addData(dbEntry1);
		dbEntry.addData(dbEntry2);
		
		return add(dbEntry);
	}
	
	public boolean deleteURL(URL url){
		return this.delete(new DBEntry());
	}
	
	public URL queryURLFromUrlString(String url){
		return null;
	}
	
	/**
	 * TODO : 미구현
	 * @param siteName
	 * @return
	 */
//	public ArrayList<URL> queryURLFromSiteName(String siteName){
//		return null;
//	}
}
