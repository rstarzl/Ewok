package Ewok;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;





public class CrawlerDriver extends Thread{
	private ExecutorService eservice;
	
	public CrawlerDriver(){
		int nrOfProcessors = Runtime.getRuntime().availableProcessors();
		eservice = Executors.newFixedThreadPool(nrOfProcessors);
		// Q 3개 쓰레드로 돌릴꺼고,,,,,DB는 우선 Q안에 포함시키는 걸로.
	}
	
	
	@Override
	public void run() {
		
	}
}
