package Ewok.Processor;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;

import Ewok.RegionFilter.HTMLContent;

//
//
//
//  @ Project : Ewok
//  @ File Name : QueueProcessor.java
//  @ Date : 2014-09-29
//  @ Author : HJ Shin
//	@ Modifier : JS
//

public abstract class QueueProcessor implements Callable{
	private	QueueList	queueList = new QueueList();
	private int	id;
	
	public QueueProcessor(int id){
		this.id = id;
	}
	public int getID(){
		return	this.id;
	}
	public QueueList	getQueueList(){
		return	queueList;
	}
	
	public void sleep(int time){
	    try {
	    	Thread.sleep(time);
	    } catch (InterruptedException e) {
	    	;
	    }
	}
	
	/* Function Overloading, but no modify original */
	public synchronized QueueEntry pop(){
		QueueEntry	entry;
		
		try {
			entry = queueList.pop();
		} catch (NoSuchElementException e){
			entry = null;
		}
		
		return	entry;
	}
	
	public synchronized void push(QueueEntry entry){
		this.queueList.push(entry);
	}
	
	public synchronized int getQSize(){
		return	this.queueList.size();
	}
	/* Function Overloading */
	
	public abstract void run();
	
	@Override
	/**
	 *
	 * Added by JS. For multi-core tasking.
	 *
	 */
	public Object call() throws Exception {
		run();
		
		return null;
	}
	
	
	
//	
//	public void push(List<HTMLContent> queueList, HTMLContent content) {
//		queueList.add(content);
//	}
//	
//	protected HTMLContent pop(List<HTMLContent> queueList) {
//		if(queueList.isEmpty()){
//			return null;
//		}
//		HTMLContent content = queueList.get(0);
//		queueList.remove(0);
//		return content;
//	}
//	
//	protected String getURL(HTMLContent content) {
//		return content.url;
//	}
//	
//	public void setURL(HTMLContent content, String url) {
//		content.url = url;
//	}
//	
//	protected int getDepth(HTMLContent content) {
//		return content.depth;
//	}
//	
//	protected void setDepth(HTMLContent content, int depth) {
//		content.depth = depth;
//	}
//	
//	protected boolean getIsContent(HTMLContent content) {
//		return content.isContent;
//	}
//	
//	protected void setIsContent(HTMLContent content, boolean isContent) {
//		content.isContent = isContent;
//	}
//	
//	

	

}
