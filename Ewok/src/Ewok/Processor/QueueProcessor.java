package Ewok.Processor;

import java.util.ArrayList;
import java.util.List;

import Ewok.RegionFilter.HTMLContent;

//
//
//
//  @ Project : Ewok
//  @ File Name : QueueProcessor.java
//  @ Date : 2014-09-29
//  @ Author : HJ Shin
//
//

public class QueueProcessor {
	public List<HTMLContent> queueList;
	
	public QueueProcessor(){
		queueList = new ArrayList<HTMLContent>();
	}
	
	
	public void push(List<HTMLContent> queueList, HTMLContent content) {
		queueList.add(content);
	}
	
	protected HTMLContent pop(List<HTMLContent> queueList) {
		if(queueList.isEmpty()){
			return null;
		}
		HTMLContent content = queueList.get(0);
		queueList.remove(0);
		return content;
	}
	
	protected String getURL(HTMLContent content) {
		return content.url;
	}
	
	public void setURL(HTMLContent content, String url) {
		content.url = url;
	}
	
	protected int getDepth(HTMLContent content) {
		return content.depth;
	}
	
	protected void setDepth(HTMLContent content, int depth) {
		content.depth = depth;
	}
	
	protected boolean getIsContent(HTMLContent content) {
		return content.isContent;
	}
	
	protected void setIsContent(HTMLContent content, boolean isContent) {
		content.isContent = isContent;
	}
	
	public void sleep(int time){
	    try {

	      Thread.sleep(time);

	    } catch (InterruptedException e) { }

	}

}
