package PackageK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HeartBeatThread extends Thread{
	private Socket socket;
	private long	lastAccessTime;
	private BufferedReader	reader;
	private boolean runningFlag = true;
	
	public HeartBeatThread(Socket socket){
		this.socket = socket;
		lastAccessTime = System.currentTimeMillis();
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
		try {
			this.join();
			this.socket.close();
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		while (runningFlag){
			try {
				if (reader.readLine() != null){
					lastAccessTime = System.currentTimeMillis();
				}
				sleep(1000);
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
