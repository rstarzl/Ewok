package Ewok.Processor;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import Ewok.CrawlerDriver;
import Ewok.GlobalContext;
import Ewok.Checker.DepthChecker;
import Ewok.Checker.DuplicationURLChecker;
import Ewok.Checker.WorkingChecker;
import Ewok.DB.URL;
import Ewok.RegionFilter.HTMLContent;
import Ewok.RegionFilter.RegionFilterDriver;

public class ClassifierQueueProcessor extends QueueProcessor{
	private	DuplicationURLChecker		duplicationURLChecker = new DuplicationURLChecker();
	private WorkingChecker				workingChecker = new WorkingChecker();
	
	public ClassifierQueueProcessor(String qp, int id){
		super(qp, id);
	}
	
	@Override
	public void push(QueueEntry entry){
		// 1. Duplication URL checking. and drop.
		if (duplicationURLChecker.check(entry)){
			return;	// drop.
		}
		super.push(entry);
		
		//DB에 url을 저장하는 동작은 target/render queue에 push할 때 duplicate이 아닌 경우에만 db에 저장한다.(효정)
//		GlobalContext.getURLDB().add(entry);
	}
	
	/* @ modified by JS */
	public void run() {
		while(true){			
			internalRun();
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

	public void internalRun() {
		while(this.getQSize() != 0){
			sleep(100);
			
			QueueEntry	workingItem = this.pop();	
			
			if (workingItem != null){
				// 1. Working URL check.Is it a target news article?
				if (workingChecker.check(workingItem)){
	//					GlobalContext.getURLDB().add(workingItem);
					GlobalContext.getAvailableRenderingQL().push(workingItem);
				} else {
					GlobalContext.getAvailableTargetQL().push(workingItem);
				}
				
				GlobalContext.logCommon("CP : " + workingItem.getSiteURL() + "\t" + workingItem.getUrlType() + "\t" + this.getQSize());
			}
		}
	}


}
