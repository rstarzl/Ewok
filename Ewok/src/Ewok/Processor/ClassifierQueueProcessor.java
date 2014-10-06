package Ewok.Processor;

import java.util.ArrayList;

import Ewok.CrawlerDriver;
import Ewok.RegionFilter.HTMLContent;

public class ClassifierQueueProcessor extends QueueProcessor{
	public ClassifierQueueProcessor(int id){
		super(id);
	}
	
	public void run() {
		while(true){
			HTMLContent content = pop(queueList);
			if(content!=null){
				System.out.println("class : "+getURL(content));
				if(content.isContent){
					CrawlerDriver.rp.push(CrawlerDriver.rp.queueList, content);
				}else{
					CrawlerDriver.tp.push(CrawlerDriver.tp.queueList, content);
				}
			}
			
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
