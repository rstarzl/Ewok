package Ewok.Processor;

import java.util.ArrayList;
import java.util.List;

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
	
//	static List<HTMLContent> queueList = new ArrayList<HTMLContent>();
	
	public RenderingQueueProcessor(){
	}
	
	public void run() {
	
		while(true){
			HTMLContent content = pop(queueList);
			if(content!=null){
				System.out.println("render : "+getURL(content));
				boolean result;
				//call render
				//result = render(content);
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
