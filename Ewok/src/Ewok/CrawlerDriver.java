package Ewok;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Ewok.Processor.ClassifierQueueProcessor;
import Ewok.Processor.RenderingQueueProcessor;
import Ewok.Processor.TargetQueueProcessor;
import Ewok.RegionFilter.HTMLContent;

/**
 * 
 * @author JS
 *
 */
public class CrawlerDriver extends Thread{
//public class CrawlerDriver {
	private ExecutorService eservice;
			
	public CrawlerDriver(){
		
		/* Connect DB */
		GlobalContext.connectDB();
		/* Connect DB */
		
		/* Connect LogFiles */
		GlobalContext.connectLogFiles();
		/* Connect LogFiles */
	}


	private void nonThreadWork() {
		/* Instance Target Queue */
		TargetQueueProcessor	tp = new TargetQueueProcessor("TP" ,0);
		GlobalContext.getTargetQP().add(tp);
		/* Instance Target Queue */
	
		
		/* Instance Classifier Queue */
		ClassifierQueueProcessor	cp = new ClassifierQueueProcessor("CP", 0);
		GlobalContext.getClassifierQP().add(cp);
		/* Instance Classifier Queue */
		
		
		/* Instance Rendering Queue */
		RenderingQueueProcessor	rp = new RenderingQueueProcessor("RQ", 0);
		GlobalContext.getRenderingQP().add(rp);
		/* Instance Rendering Queue */
				
		while(true){
			GlobalContext.getAvailableTargetQL().internalRun();
			GlobalContext.getAvailableClassifierQL().internalRun();
			GlobalContext.getAvailableRenderingQL().internalRun();
		}
	}


	private void threadWork() {
		int nrOfProcessors = Runtime.getRuntime().availableProcessors();
		eservice = Executors.newFixedThreadPool(nrOfProcessors);
		// Q 3개만 쓰레드로 돌게, DB는 Q에 붙여서 우선..
		
		/* Running Target Queue */
		for (int queueIndex = 0; queueIndex < GlobalContext.getTargetQPCount(); queueIndex++){
			TargetQueueProcessor	qp = new TargetQueueProcessor("TP" ,queueIndex);
			GlobalContext.getTargetQP().add(qp);
			eservice.submit(qp);
		}
		/* Running Target Queue */
		
		
		/* Running Classifier Queue */
		for (int queueIndex = 0; queueIndex < GlobalContext.getUrlClassifierQPCount(); queueIndex++){
			ClassifierQueueProcessor	qp = new ClassifierQueueProcessor("CP", queueIndex);
			GlobalContext.getClassifierQP().add(qp);
			eservice.submit(qp);
		}
		/* Running Classifier Queue */
		
		
		/* Running Rendering Queue */
		for (int queueIndex = 0; queueIndex < GlobalContext.getRenderQPCount(); queueIndex++){
			RenderingQueueProcessor	qp = new RenderingQueueProcessor("RQ", queueIndex);
			GlobalContext.getRenderingQP().add(qp);
			eservice.submit(qp);
		}
		/* Running Rendering Queue */
	}
	
	
	@Override
	public void run() {
		// TODO:external control method
//		//threadWork();
		nonThreadWork();
	}
}
