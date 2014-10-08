package Ewok;

import java.util.ArrayList;
import java.util.LinkedList;

import Ewok.GlobalConfigure.TYPE_OF_DB;
import Ewok.Processor.ClassifierQueueProcessor;
import Ewok.Processor.QueueList;
import Ewok.Processor.RenderingQueueProcessor;
import Ewok.Processor.TargetQueueProcessor;
import Ewok.Utils.ExceptionInvalidForm;
import Ewok.Utils.XMLReader;
/**
 * 
 * @author JS
 *
 */
public class GlobalContext {
	/* Setting variable for Workflow. */
	public static enum TYPE_OF_SITE {NAVER, NATE, DAUM, NON};
	public static final TYPE_OF_SITE SELECTED_SITE[] = {TYPE_OF_SITE.DAUM, TYPE_OF_SITE.NATE, TYPE_OF_SITE.DAUM};
	/* Setting variable for Workflow. */
	
	static private String filePath = "";
	static private int	depthLimit;
	static private int	renderQPCount;
	static private int	targetQPCount;
	static private int	urlClassifierQPCount;
	static private LinkedList<String> seedList = new LinkedList<String>();
	
	/* QP Lists */
	static private	ProcessorList<RenderingQueueProcessor>	qpRender  = new ProcessorList<RenderingQueueProcessor>();
	static private ProcessorList<TargetQueueProcessor>		qpTarget = new ProcessorList<TargetQueueProcessor>();
	static private ProcessorList<ClassifierQueueProcessor>	qpClassifier = new ProcessorList<ClassifierQueueProcessor>();
	/* QP Lists */
	
	/* Getter and Setter*/
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
	/* Getter and Setter*/
	
	
	/* Each Available Queue getter */
	public static RenderingQueueProcessor getAvailableRenderingQL(){
		return	qpRender.getAllocatableQP();
	}
	public static TargetQueueProcessor getAvailableTargetQL(){
		return	qpTarget.getAllocatableQP();
	}
	public static ClassifierQueueProcessor getAvailableClassifierQL(){
		return	qpClassifier.getAllocatableQP();
	}
	/* Each QP QueueList getter */
	
	
	/* Each QP getter */
	public static ProcessorList<RenderingQueueProcessor> getRenderingQP(){
		return	qpRender;
	}
	public static ProcessorList<TargetQueueProcessor> getTargetQP(){
		return	qpTarget;
	}
	public static ProcessorList<ClassifierQueueProcessor> getClassifierQP(){
		return	qpClassifier;
	}
	/* Each QP getter */
	
	
	
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
		// �⺻��ο� xml File �����ؼ� �ٽ� �ε� ��Ŵ..
		// file �����ϴ� ��� ��������.
		filePath = "./Ewok.Config";
	}
	

	
	
	
	
	
	
}
