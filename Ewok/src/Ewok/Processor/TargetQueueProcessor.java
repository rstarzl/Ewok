package Ewok.Processor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import Ewok.CrawlerDriver;
import Ewok.RegionFilter.HTMLContent;
//
//
//
//  @ Project : Ewok
//  @ File Name : TargetQueueProcessor.java
//  @ Date : 2014-09-29
//  @ Author : HJ Shin
//	// Modified by JS
//

public class TargetQueueProcessor extends QueueProcessor {
	public TargetQueueProcessor(int id){
		super(id);
	}
	
	public void run() {
		while(true){
			HTMLContent content = pop(queueList);
			if(content!=null){
				System.out.println("target : "+getURL(content));
				ArrayList<String> URLList = new ArrayList<String>();
				//call region extractor
				//URLList = regionExtractor(content);	
				URLList.add("url1");
				URLList.add("url2");
				URLList.add("url3");
				pushAllURL(URLList);
				
				/* @added by JS */
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/* @added by JS */
			}
		}
	}

	private void pushAllURL(ArrayList<String> URLList) {
		for(int listIdx=0; listIdx<URLList.size(); ++listIdx){
			HTMLContent content = new HTMLContent();
			setURL(content, URLList.get(listIdx));
			CrawlerDriver.cp.push(CrawlerDriver.cp.queueList, content);
			System.out.println("send to classifier : "+content.url);
		}
	}
}
