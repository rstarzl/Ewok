package Ewok.Processor;

import java.util.ArrayList;
import java.util.List;

import Ewok.GlobalContext;
import Ewok.Checker.SimilarityContentChecker;
import Ewok.RegionFilter.HTMLContent;
import Ewok.Render.NonTargetException;
import Ewok.Render.RenderDriver;
import Ewok.Render.RenderTerminatedException;
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
	long						collectedArticleCount = 0;
	
	public RenderingQueueProcessor(String qp, int id){
		super(qp, id);
	}
	
	@Override
	public void push(QueueEntry entry){
		super.push(entry);
		GlobalContext.snapShotWriter("+, RP, " + entry.getSiteURL() + ", " + entry.getUrlType());
	}
	
	/* @ modified by JS */
	public void run() {
	
		while(true){
			internalRun();
		}	
	}
	/* @ modified by JS */

	public void internalRun() {
		while(this.getQSize() != 0){
			System.gc();
			
			sleep(100);
			
			QueueEntry	workingItem = this.pop();
			if (workingItem != null){
				// 1. render contents
				try {
					render.render(workingItem);
				} catch (RenderTerminatedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					GlobalContext.logCommon(workingItem.getSiteURL() + "<-- Sport section article");
					GlobalContext.snapShotWriter("-, RP, " + workingItem.getSiteURL() + ", " + workingItem.getUrlType());
					continue;
				}
				
				// 2. checking similarity
				if (similarityContentChecker.check(workingItem)){
					return;
				}
				
				// 3. DB Access
				GlobalContext.getMeaningfulDB().add(workingItem);
				GlobalContext.snapShotWriter("-, RP, " + workingItem.getSiteURL() + ", " + workingItem.getUrlType());
				GlobalContext.logCommon("RP : " + workingItem.getArticle().title + "\t" + this.getQSize());
				System.out.print("CollectedArticleCount : " + (collectedArticleCount++) + "\r");
			}
		}
	}
	
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
