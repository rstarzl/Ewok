package PackageK;

import java.io.File;
import java.io.IOException;

public class SiteRunner {
	private String siteName;
	private String path;
	private int		runningCount = 0;
	private Process process;
	
	public SiteRunner(String siteName){
		this.siteName = siteName;
		if (!checkFolder()){
			System.out.println("Not enough Requirement");
			System.exit(1);
		}
		this.path = "./" + siteName + "/";
	}

	public boolean isAlive(){
		return	process.isAlive();
	}

	// ½ÇÇà 
	public void run(){
		// ¾¾µå, ½º³À¼¦ ¹»·Î ½ÇÇà?
		try {
			if (isSnapShot()){
				process = new ProcessBuilder(path + "exeForSnapshot.bat").start();
			} else {
				process = new ProcessBuilder(path + "exeForSeed.bat").start();
			}
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

	// Æú´õ Ã¼Å©.
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
