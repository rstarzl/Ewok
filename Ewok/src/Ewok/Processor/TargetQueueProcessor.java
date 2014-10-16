package Ewok.Processor;

import java.util.ArrayList;

import Ewok.GlobalContext;
import Ewok.Checker.DepthChecker;
import Ewok.RegionFilter.FilterTerminatedException;
import Ewok.RegionFilter.RegionFilterDriver;
import Ewok.RegionFilter.URLInfo;
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
	
	public TargetQueueProcessor(String qp, int id){
		super(qp, id);
	}
	
	@Override
	public void push(QueueEntry entry){
		// Depth checking. and drop.
		if (!depthChecker.check(entry)){
			return;	// drop.
		}
		super.push(entry);
		GlobalContext.snapShotWriter("+, TP, " + entry.getSiteURL());
	}
	
	/* @ modified by JS */
	public void run() {
		while(true){			
			internalRun();
		}
	}
	
	public void internalRun(){
		while(this.getQSize() != 0){
			sleep(100);
			
			//GC
			System.gc();
			
			// workingItem에 작업 내용 체워서 아래 함수 이용 큐 어싸인.
			QueueEntry	workingItem = this.pop();
			if (workingItem != null){
				// 1. Getting URL.
				ArrayList<URLInfo> linkList = new ArrayList<URLInfo>();
				try {
					linkList = regionFilter.filter(workingItem);
				} catch (FilterTerminatedException e) {
					e.printStackTrace();
					continue;
				}
				// 2. Assigning Work.
				for (URLInfo workingURL : linkList){
					QueueEntry	entry = new QueueEntry(workingURL.getUrl());
					entry.setURLInfo(workingURL);
					GlobalContext.logCommon("TP : " + entry.getSiteURL() + "\t" +entry.getUrlType() + "\t" + this.getQSize());
					GlobalContext.getAvailableClassifierQL().push(entry);
				}
				GlobalContext.snapShotWriter("-, TP, " + workingItem.getSiteURL());
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
