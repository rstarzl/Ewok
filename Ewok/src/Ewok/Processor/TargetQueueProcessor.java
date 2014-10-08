package Ewok.Processor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import Ewok.CrawlerDriver;
import Ewok.GlobalContext;
import Ewok.Checker.DepthChecker;
import Ewok.RegionFilter.HTMLContent;
import Ewok.RegionFilter.RegionFilterDriver;
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
	private	DepthChecker		depthChecker = new DepthChecker();
	private RegionFilterDriver	regionFilter = new RegionFilterDriver();
	
	public TargetQueueProcessor(int id){
		super(id);
	}
	
	@Override
	public void push(QueueEntry entry){
		// Depth checking. and drop.
		if (!depthChecker.check(entry)){
			return;	// drop.
		}
		super.push(entry);
	}
	
	/* @ modified by JS */
	public void run() {
		while(true){
			sleep(10);
			
			// workingItem에 작업 내용 체워서 아래 함수 이용 큐 어싸인.
			QueueEntry	workingItem = this.pop();
			if (workingItem != null){
				// 1. Getting URL.
				ArrayList <String> linkList = regionFilter.filter(workingItem);

				// 2. Assigning Work.
				for (String workingURL : linkList){
					QueueEntry	entry = new QueueEntry();
					entry.setSiteURL(workingURL);
					GlobalContext.getAvailableClassifierQL().push(entry);
				}
			}
		}
	}
	/* @ modified by JS */
	

//	public void run() {
//		while(true){
//			HTMLContent content = pop(queueList);
//			if(content!=null){
//				System.out.println("target : "+getURL(content));
//				ArrayList<String> URLList = new ArrayList<String>();
//				//call region extractor
//				//URLList = regionExtractor(content);	
//				URLList.add("url1");
//				URLList.add("url2");
//				URLList.add("url3");
//				pushAllURL(URLList);
//	
//			}
//		}
//	}
//
//	private void pushAllURL(ArrayList<String> URLList) {
//		for(int listIdx=0; listIdx<URLList.size(); ++listIdx){
//			HTMLContent content = new HTMLContent();
//			setURL(content, URLList.get(listIdx));
//			CrawlerDriver.cp.push(CrawlerDriver.cp.queueList, content);
//			System.out.println("send to classifier : "+content.url);
//		}
//	}
}
