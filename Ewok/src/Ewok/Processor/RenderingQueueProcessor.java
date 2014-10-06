package Ewok.Processor;

import java.util.ArrayList;
import java.util.List;

import Ewok.GlobalContext;
import Ewok.RegionFilter.HTMLContent;
//
//
//
//  @ Project : Ewok
//  @ File Name : ParsingQueueProcessor.java
//  @ Date : 2014-09-29
//  @ Author : HJ Shin
//
//

public class RenderingQueueProcessor extends QueueProcessor {
	public RenderingQueueProcessor(int id){
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
		}	
	}
	/* @ modified by JS */
	
//	public void run() {
//		
//		while(true){
//			HTMLContent content = pop(queueList);
//			if(content!=null){
//				System.out.println("render : "+getURL(content));
//				boolean result;
//				//call render
//				//result = render(content);
//			}
//		}	
//	}
}
