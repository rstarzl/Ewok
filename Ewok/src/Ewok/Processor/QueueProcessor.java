package Ewok.Processor;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;

import Ewok.GlobalContext;
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
	private String qpName;
	private int	id;
		
	public QueueProcessor(String qp, int id){
		this.id = id;
		this.qpName = qp;
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
	public QueueEntry pop(){
		GlobalContext.logQueueLock(qpName + String.valueOf(id) + "is locked.");
		
		QueueEntry	entry;
		
		try {
			entry = queueList.removeLast();
		} catch (NoSuchElementException e){
			entry = null;
		}
		
		GlobalContext.logQueueLock(qpName + String.valueOf(id) + "is unlocked.");
		return	entry;
	}
	
	public void push(QueueEntry entry){
		GlobalContext.logQueueLock(qpName + String.valueOf(id) + "is locked.");
		this.queueList.push(entry);
		GlobalContext.logQueueLock(qpName + String.valueOf(id) + "is unlocked.");
	}
	
	public int getQSize(){
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
