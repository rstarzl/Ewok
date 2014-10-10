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
	private ExecutorService eservice;
			
	public CrawlerDriver(){
		int nrOfProcessors = Runtime.getRuntime().availableProcessors();
		eservice = Executors.newFixedThreadPool(nrOfProcessors);
		// Q 3개만 쓰레드로 돌게, DB는 Q에 붙여서 우선..
		
		/* Running Target Queue */
		for (int queueIndex = 0; queueIndex < GlobalContext.getTargetQPCount(); queueIndex++){
			TargetQueueProcessor	qp = new TargetQueueProcessor(queueIndex);
			GlobalContext.getTargetQP().add(qp);
			eservice.submit(qp);
		}
		/* Running Target Queue */
		
		
		/* Running Classifier Queue */
		for (int queueIndex = 0; queueIndex < GlobalContext.getUrlClassifierQPCount(); queueIndex++){
			ClassifierQueueProcessor	qp = new ClassifierQueueProcessor(queueIndex);
			GlobalContext.getClassifierQP().add(qp);
			eservice.submit(qp);
		}
		/* Running Classifier Queue */
		
		
		/* Running Rendering Queue */
		for (int queueIndex = 0; queueIndex < GlobalContext.getRenderQPCount(); queueIndex++){
			RenderingQueueProcessor	qp = new RenderingQueueProcessor(queueIndex);
			GlobalContext.getRenderingQP().add(qp);
			eservice.submit(qp);
		}
		/* Running Rendering Queue */
		
		
		/* Connect DB */
		GlobalContext.connectDB();
		/* Connect DB */
	}
	
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
