package Ewok;

import java.util.LinkedList;

import Ewok.Utils.ExceptionInvalidForm;
import Ewok.Utils.XMLReader;

public class GlobalContext {
	static private String filePath = "";
	static private int	depthLimit;
	static private int	renderQPCount;
	static private int	targetQPCount;
	static private int	urlClassifierQPCount;
	static private LinkedList<String> seedList = new LinkedList<String>();
	
	public static String getFilePath() {
		return filePath;
	}
	public static void setFilePath(String filePath) {
		GlobalContext.filePath = filePath;
	}
	public static int getDepthLimit() {
		return depthLimit;
	}
	public static int getRenderQPCount() {
		return renderQPCount;
	}
	public static int getTargetQPCount() {
		return targetQPCount;
	}
	public static int getUrlClassifierQPCount() {
		return urlClassifierQPCount;
	}
	public static LinkedList<String> getSeedList(){
		return	seedList;
	}
	
	public static void	readConfigFile(){
		// path validation check.
		if (filePath.equals("")){
			defaultSetting();
		}
		
		
		// get value.
		XMLReader	xmlReader = new XMLReader(filePath);
		String tempSeed = "";
		
		try {
			depthLimit = Integer.valueOf(xmlReader.getFirstTagValue("DepthLimit").trim());
			renderQPCount = Integer.valueOf(xmlReader.getFirstTagValue("RenderQPCount").trim());
			targetQPCount = Integer.valueOf(xmlReader.getFirstTagValue("TargetQPCount").trim());
			urlClassifierQPCount = Integer.valueOf(xmlReader.getFirstTagValue("URLClassifierQPCount").trim());
			tempSeed = xmlReader.getFirstTagValue("seedList");
		} catch (ExceptionInvalidForm e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//assign seed list.
		String[] seeds = tempSeed.split("\\n");
		for (int index = 0; index < seeds.length; index++){
			if (!seeds[index].trim().equals("")){
				seedList.push(seeds[index].trim());
			}
		}		
	}
	private static void defaultSetting() {
		// TODO Auto-generated method stub
		// 기본경로에 xml File 생성해서 다시 로드 시킴..
		// file 생성하는 기능 만들어야함.
		filePath = "./Ewok.Config";
	}
	
	
	
	
	
	
}
