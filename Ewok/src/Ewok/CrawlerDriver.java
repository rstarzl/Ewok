package Ewok;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;





public class CrawlerDriver extends Thread{
	private ExecutorService eservice;
	
	public CrawlerDriver(){
		int nrOfProcessors = Runtime.getRuntime().availableProcessors();
		eservice = Executors.newFixedThreadPool(nrOfProcessors);
		// Q 3개만 쓰레드로 돌게, DB는 Q에 붙여서 우선..
	}
	
	
	@Override
	public void run() {
		
	}
}
