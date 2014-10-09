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

import Ewok.Render.*;



public class MeaningfulDB extends DB {
	private static final String rootName = "MeaningfulDB";
	
	public MeaningfulDB(){
		super(rootName);
	}
	
	public void run() {
	
	}
	public Article queryContentsFromContentString(String Contents){ // DB에서 Contents check 후 null 또는 Article 형태로 결과 뱉음. (SimilarityContentChecker에서 사용)
		return null;
	}
}
