package Ewok;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;





public class CrawlerDriver extends Thread{
	private ExecutorService eservice;
	
	public CrawlerDriver(){
		int nrOfProcessors = Runtime.getRuntime().availableProcessors();
		eservice = Executors.newFixedThreadPool(nrOfProcessors);
		// Q 3�� ������� ��������,,,,,DB�� �켱 Q�ȿ� ���Խ�Ű�� �ɷ�.
	}
	
	
	@Override
	public void run() {
		
	}
}
