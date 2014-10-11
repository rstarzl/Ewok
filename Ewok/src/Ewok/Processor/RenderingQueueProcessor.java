package Ewok.Processor;

import java.util.ArrayList;
import java.util.List;

import Ewok.GlobalContext;
import Ewok.Checker.SimilarityContentChecker;
import Ewok.RegionFilter.HTMLContent;
import Ewok.Render.RenderDriver;
//
//
//
//  @ Project : Ewok
//  @ File Name : ParsingQueueProcessor.java
//  @ Date : 2014-09-29
//  @ Author : HJ Shin
//	@ Modifier : JS
//

public class RenderingQueueProcessor extends QueueProcessor {
	SimilarityContentChecker	similarityContentChecker = new SimilarityContentChecker();
	RenderDriver				render = new RenderDriver();
	
	public RenderingQueueProcessor(String qp, int id){
		super(qp, id);
	}
	
	/* @ modified by JS */
	public void run() {
	
		while(true){
			System.gc();
			
			sleep(100);
			QueueEntry	workingItem = this.pop();
			if (workingItem != null){
				// 1. render contents
				render.render(workingItem);
				
				// 2. checking similarity
				if (similarityContentChecker.check(workingItem)){
					continue;
				}
				
				// 3. DB Access
				GlobalContext.getMeaningfulDB().add(workingItem);
				
				GlobalContext.logCommon("RP : " + workingItem.getArticle().title);
			}
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
