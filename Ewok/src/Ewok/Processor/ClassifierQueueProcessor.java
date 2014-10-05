package Ewok.Processor;

import java.util.ArrayList;

import Ewok.CrawlerDriver;
import Ewok.RegionFilter.HTMLContent;

public class ClassifierQueueProcessor extends QueueProcessor implements Runnable {
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
		}
	}


}
