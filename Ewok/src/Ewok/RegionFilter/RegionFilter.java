package Ewok.RegionFilter;

import java.util.ArrayList;

import Ewok.Render.Article;
import Ewok.Render.NonTargetException;

//  @ Project : Ewok
//  @ File Name : RegionFilter.java
//  @ Date : 2014-10-03
//  @ Author : Kiheung Park

public abstract class RegionFilter extends Thread{
	abstract public ArrayList<URLInfo> filter(String urlAddress) throws Exception;
	
	private String targetedURL;
	private ArrayList<URLInfo> curResult;
	
	public void setParam(String targetURL){
		this.targetedURL = targetURL;
		curResult = null;
	}
	
	public ArrayList<URLInfo> getResult(){
		return curResult;
	}
	
//	//for thread.
//	public void run(){
//		curResult = filter(this.targetedURL);
//	}

//	public ArrayList<String> filter(QueueEntry entry); 
}
