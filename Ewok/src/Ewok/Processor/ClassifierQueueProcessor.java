package Ewok.Processor;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import Ewok.CrawlerDriver;
import Ewok.GlobalContext;
import Ewok.RegionFilter.HTMLContent;

public class ClassifierQueueProcessor extends QueueProcessor{
	public ClassifierQueueProcessor(int id){
		super(id);
	}
	
	/* @ modified by JS */
	public void run() {
		while(true){
			QueueEntry	workingItem = this.pop();
			if (workingItem != null){
//				if(content.isContent){
				// workingItem에 작업 내용 체워서 아래 함수 이용 큐 어싸인.
					GlobalContext.getAvailableRenderingQL().push(workingItem);
//				}else{
					GlobalContext.getAvailableTargetQL().push(workingItem);
//				}
			}

			sleep(10);
			
//			HTMLContent content = pop(queueList);
//			if(content!=null){
//				System.out.println("class : "+getURL(content));
//				if(content.isContent){
//					CrawlerDriver.rp.push(CrawlerDriver.rp.queueList, content);
//				}else{
//					CrawlerDriver.tp.push(CrawlerDriver.tp.queueList, content);
//				}
//			}
		}
	}
	/* @ modified by JS */


}
