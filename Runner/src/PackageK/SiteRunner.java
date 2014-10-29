package PackageK;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class SiteRunner {
	private String siteName;
	private String path;
	private int		runningCount = 0;
//	private Process process = null;
	private HeartBeatThread	heartBeatThread = null;
//	private HeartBeatMaster heartBeatMaster;
		
	public SiteRunner(String siteName){
		this.siteName = siteName;
		if (!checkFolder()){
			System.out.println("Not enough Requirement, " + siteName);
			System.exit(1);
		}
		this.path = "./" + siteName + "/";
//		this.heartBeatMaster = heartBeatMaster;

		run();
	}

	public boolean isAlive(){
		if (heartBeatThread != null){
			return	heartBeatThread.checkHeartBeat();
		} else {
			return	false;
		}
	}

	// 실행 
	public void run(){
		// 씨드, 스냅샷 뭘로 실행?
		
		// 먼저 열고 기다릴 방안이 필요한가?
		try {
			if (heartBeatThread != null){
				System.out.println("Before forceDown");
				heartBeatThread.forceDown();
				System.out.println("Done forceDown");
				heartBeatThread = null;
			}
			
			System.out.println("Wait Client.." + siteName);
//			Socket conn = heartBeatMaster.accept();
			
			if (isSnapShot()){
				heartBeatThread = new HeartBeatThread(new ProcessBuilder(path + "exeForSnapshot.bat").start());
			} else {
				heartBeatThread = new HeartBeatThread(new ProcessBuilder(path + "exeForSeed.bat").start());
			}
			heartBeatThread.start();
			
			System.out.println("Connected Client.." + siteName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.runningCount++;
	}
	
	private boolean isSnapShot(){
		File file = new File(path + "SnapShot");
		
		if (file.isFile()){
			return	true;
		} else {
			return	false;
		}
	}

	// 폴더 체크.
	private boolean checkFolder(){
		File file = new File(this.siteName);
		if (file.isDirectory()){
			return	true;
		} else {
			return	false;
		}
	}
	
	@Override
	public String toString(){
		return this.siteName + " running Count : " + String.valueOf(this.runningCount);
	}
}
