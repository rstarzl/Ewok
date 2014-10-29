package PackageK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HeartBeatThread extends Thread{
//	private Socket socket;
	private Process process;
	private long	lastAccessTime;
	private BufferedReader	reader;
	private boolean runningFlag = true;
	
	private Thread thisThread;
	
	public HeartBeatThread(Process process){
//		this.socket = socket;
//		this.process = process;
		lastAccessTime = System.currentTimeMillis();
		reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		this.process = process;
	}
	
	public boolean checkHeartBeat(){
		long	currentTime = System.currentTimeMillis();
		if (currentTime - lastAccessTime < Configure.HEART_BEAT_LIMIT){
			return	true;
		} else {
			return	false;
		}
	}
	
	public void forceDown(){
		runningFlag = false;
		process.destroyForcibly();
		
		try {
			System.out.println("Wait for thread..");
			thisThread.join(1500);
			System.out.println("Wait for thread..Done.");
//			sleep(1500);
//			this.socket.close();
//			this.reader.close();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		thisThread = Thread.currentThread();
		while (runningFlag){
			try {
				if (reader.readLine() != null){
					lastAccessTime = System.currentTimeMillis();
				}
				sleep(100);
			} catch (IOException | InterruptedException e) {
				break;
			}
		}
	}
}
