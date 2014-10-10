package Ewok.DB;
//
//
//
//  @ Project : Ewok
//  @ File Name : MeaningfulDB.java
//  @ Date : 2014-09-29
//  @ Author : Member
//
//

import java.util.ArrayList;

import Ewok.Processor.QueueEntry;
import Ewok.Render.*;



public class MeaningfulDB extends DB {
	private static final String rootName = "MeaningfulDB";
	
	public MeaningfulDB(){
		super(rootName);
	}
	public MeaningfulDB(String	tableName){
		super(tableName);
	}
	
	public void run() {
	
	}
	
//	public Article queryContentsFromContentString(String Contents){ // DB에서 Contents check 후 null 또는 Article 형태로 결과 뱉음. (SimilarityContentChecker에서 사용)
//		return null;
//	}
	
	/* For SQLDB. It must be refactory. */
	public boolean addArticle(Article article){
		QueueEntry	entry = new QueueEntry();
		entry.setArticle(article);
		return	super.add(entry);
	}
	
	public boolean deleteArticle(Article article){
		QueueEntry	entry = new QueueEntry();
		entry.setArticle(article);
		return super.delete(entry);
	}
	
	public ArrayList<QueueEntry> queryArticleFromUrlString(String url){
		return super.query(url);
	}
	/* For SQLDB. It must be refactory. */
}
