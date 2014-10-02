package Ewok;

import Ewok.Processor.ClassifierQueueProcessor;
import Ewok.Processor.RenderingQueueProcessor;
import Ewok.Processor.TargetQueueProcessor;
import Ewok.RegionFilter.HTMLContent;

public class CrawlerDriver {
	public static TargetQueueProcessor tp = new TargetQueueProcessor();
	public static ClassifierQueueProcessor cp = new ClassifierQueueProcessor();
	public static RenderingQueueProcessor rp = new RenderingQueueProcessor();
	
	public void run(){
		Thread target = new Thread(tp);
		Thread classifier = new Thread(cp);
		Thread render = new Thread(rp);
		
		target.start();
		classifier.start();
		render.start();
		
		HTMLContent content = new HTMLContent();
		tp.setURL(content, "www.naver.com");	
		tp.push(tp.queueList, content);	
	}
}
