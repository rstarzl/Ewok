package Ewok.Processor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.http.impl.conn.Wire;

import Ewok.CrawlerDriver;
import Ewok.GlobalContext;
import Ewok.Checker.DepthChecker;
import Ewok.RegionFilter.HTMLContent;
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
		
		// TODO: temp code.
//		QueueEntry	entry = new QueueEntry("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=100");
//		entry.setSiteURL("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=100");
//		entry.setDepth(GlobalContext.getDepthLimit());
		
//		this.push(new QueueEntry("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=100"));
//		this.push(new QueueEntry("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=101"));
//		this.push(new QueueEntry("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=102"));
//		this.push(new QueueEntry("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=103"));
//		this.push(new QueueEntry("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=104"));
//		this.push(new QueueEntry("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=105"));
//		this.push(new QueueEntry("http://media.daum.net/society/all/#page=1&type=tit_cont"));
//		this.push(new QueueEntry("http://media.daum.net/politics/all/#page=1&type=tit_cont"));
//		this.push(new QueueEntry("http://media.daum.net/economic/all/#page=1&type=tit_cont"));
//		this.push(new QueueEntry("http://media.daum.net/foreign/all/#page=1&type=tit_cont"));
//		this.push(new QueueEntry("http://media.daum.net/culture/all/#page=1&type=tit_cont"));
//		this.push(new QueueEntry("http://media.daum.net/digital/all/#page=1&type=tit_cont"));
//		this.push(new QueueEntry("http://media.daum.net/editorial/all/#page=1&type=tit_cont"));
//		this.push(new QueueEntry("http://news.nate.com/recent?mid=n0201"));
//		this.push(new QueueEntry("http://news.nate.com/recent?mid=n0301"));
//		this.push(new QueueEntry("http://news.nate.com/recent?mid=n0401"));
//		this.push(new QueueEntry("http://news.nate.com/recent?mid=n0501"));
//		this.push(new QueueEntry("http://news.nate.com/recent?mid=n0601"));
//		this.push(new QueueEntry("http://news.nate.com/recent?mid=n0701"));

	
		this.push(new QueueEntry("http://news.nate.com/recent?cate=pol&mid=n0201&type=c&date=20141008"));
}
	
	@Override
	public void push(QueueEntry entry){
		// Depth checking. and drop.
		if (!depthChecker.check(entry)){
			return;	// drop.
		}
		super.push(entry);
//		GlobalContext.getURLDB().add(entry);
	}
	
	/* @ modified by JS */
	public void run() {
		while(true){
			sleep(100);
			
			//GC
			System.gc();
			
			// workingItem에 작업 내용 체워서 아래 함수 이용 큐 어싸인.
			QueueEntry	workingItem = this.pop();
			if (workingItem != null){
				// 1. Getting URL.
				ArrayList <URLInfo> urlResult = regionFilter.filter(workingItem);
				ArrayList <URLInfo> linkList = (ArrayList <URLInfo>)urlResult.clone();
				// 2. Assigning Work.
				for (URLInfo workingURL : linkList){
					QueueEntry	entry = new QueueEntry(workingURL.getUrl());
					entry.setURLInfo(workingURL);
					GlobalContext.logCommon("TP : " + entry.getSiteURL() + "\t" +entry.getUrlType() + "\t" + this.getQSize());
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
