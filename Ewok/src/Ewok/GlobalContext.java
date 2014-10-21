package Ewok;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import Ewok.DB.MeaningfulDB;
import Ewok.DB.MySQLDB;
import Ewok.DB.URLDB;
import Ewok.Processor.ClassifierQueueProcessor;
import Ewok.Processor.QueueEntry;
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
	public GlobalContext() {

	}

	/* Temp Snapshot */
	private static BufferedReader snapShotReader = null;
	private static BufferedWriter snapShotWriter = null;
	
	public static BufferedReader loadSnapShot(String fileName) {
		try {
			snapShotReader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return snapShotReader;
	}
	public static void createSnapShot() {
		try {
			snapShotWriter = new BufferedWriter(new FileWriter("SnapShot", false));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void reSnapShotEachQueue(){
		createSnapShot();
		// Temp reset commonLog for reduce log size.
		try {
			logCommon = new BufferedWriter(new FileWriter("Common.log", false));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TargetQueueProcessor	tp = getAvailableTargetQL();
		ArrayList<QueueEntry>	tpEntries = new ArrayList<QueueEntry>();
		while(tp.getQSize() != 0){
			tpEntries.add(tp.pop());
		}
		for (QueueEntry entry : tpEntries){
			tp.push(entry);
		}
		
		ClassifierQueueProcessor	cp = getAvailableClassifierQL();
		ArrayList<QueueEntry>	cpEntries = new ArrayList<QueueEntry>();
		while(cp.getQSize() != 0){
			cpEntries.add(cp.pop());
		}
		for (QueueEntry entry : cpEntries){
			cp.push(entry);
		}

		
		RenderingQueueProcessor	rp = getAvailableRenderingQL();
		ArrayList<QueueEntry>	rpEntries = new ArrayList<QueueEntry>();
		while(rp.getQSize() != 0){
			rpEntries.add(rp.pop());
		}
		for (QueueEntry entry : rpEntries){
			rp.push(entry);
		}
	}
	
	public static void snapShotWriter(String msg) {
		try {
			snapShotWriter.write(msg+"\r\n");
			snapShotWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/* Temp Snapshot */
	
	
	/* Setting variable for Workflow. */
	public static enum TYPE_OF_SITE {
		NAVER, NATE, DAUM, NON
	};

	public static final TYPE_OF_SITE SELECTED_SITE[] = { TYPE_OF_SITE.DAUM,
			TYPE_OF_SITE.NATE, TYPE_OF_SITE.NAVER };
	
	// HTML UNIT Wait Time//
	static public final long WAIT_TIME_SECEND = 80;
	
	/* Setting variable for Workflow. */

	static private String filePath = "";
	static private int depthLimit;
	static private int renderQPCount;
	static private int targetQPCount;
	static private int urlClassifierQPCount;
	static private LinkedList<String> seedList = new LinkedList<String>();

	/* QP Lists */
	static private ProcessorList<RenderingQueueProcessor> qpRender = new ProcessorList<RenderingQueueProcessor>();
	static private ProcessorList<TargetQueueProcessor> qpTarget = new ProcessorList<TargetQueueProcessor>();
	static private ProcessorList<ClassifierQueueProcessor> qpClassifier = new ProcessorList<ClassifierQueueProcessor>();

	/* QP Lists */

	/* DB */
	public static enum TYPE_OF_DB {
		MEM, FILE, MYSQL, CASSANDRA, MONGODB, NON
	};

	// private static final TYPE_OF_DB SELECTED_DB = TYPE_OF_DB.MEM;
	private static final TYPE_OF_DB SELECTED_DB = TYPE_OF_DB.MEM;

	public static TYPE_OF_DB getSelectedDb() {
		return SELECTED_DB;
	}
	
	public static final String JDBC_URL = "jdbc:mysql://localhost:3333/webcrawler_v01?characterEncoding=utf8";
	public static final String DB_NAME = "webcrawler_v01"; 
	public static final String USER_ID = "root";
	public static final String USER_PASS = "1q2w3e";

	/* DB */

	/* Getter and Setter */
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

	public static LinkedList<String> getSeedList() {
		return seedList;
	}

	/* Getter and Setter */

	/* Each Available Queue getter */
	public static RenderingQueueProcessor getAvailableRenderingQL() {
		return qpRender.getAllocatableQP();
	}

	public static TargetQueueProcessor getAvailableTargetQL() {
		return qpTarget.getAllocatableQP();
	}

	public static ClassifierQueueProcessor getAvailableClassifierQL() {
		return qpClassifier.getAllocatableQP();
	}

	/* Each QP QueueList getter */

	/* Each QP getter */
	public static ProcessorList<RenderingQueueProcessor> getRenderingQP() {
		return qpRender;
	}

	public static ProcessorList<TargetQueueProcessor> getTargetQP() {
		return qpTarget;
	}

	public static ProcessorList<ClassifierQueueProcessor> getClassifierQP() {
		return qpClassifier;
	}

	/* Each QP getter */

	/* DB Settting */
	private static MeaningfulDB meaningfulDB = new MeaningfulDB("content");
	private static URLDB urlDB = new URLDB("url");

	public static void connectDB() {
		MySQLDB.connectMySQLDB();
	}

	public static MeaningfulDB getMeaningfulDB() {
		return meaningfulDB;
	}

	public static URLDB getURLDB() {
		return urlDB;
	}

	/* DB Settting */

	/* LogFile */
	private static BufferedWriter logQueueLock = null;
	private static BufferedWriter logTransQueue = null;
	private static BufferedWriter logCommon = null;

	public static void connectLogFiles() {
		try {
			logQueueLock = new BufferedWriter(new FileWriter("QueueLock.log", true));
			logTransQueue = new BufferedWriter(new FileWriter("TransQueue.log", true));
			logCommon = new BufferedWriter(new FileWriter("Common.log", true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void logWriter(String msg, BufferedWriter writer) {
		try {
			writer.write(msg+"\r\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getTime(){
		return "["+(new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(new Date())+"] ";
	}
	public static synchronized void logQueueLock(String msg) {
//		logWriter(getTime()+msg, logQueueLock);
	}

	public static synchronized void logTransQueue(String msg) {
//		logWriter(getTime()+msg, logTransQueue);
	}

	public static synchronized void logCommon(String msg) {
		logWriter(getTime()+msg, logCommon);
	}

	/* LogFile */

	public static void readConfigFile() {
		// path validation check.
		if (filePath.equals("")) {
			defaultSetting();
		}

		// get value.
		XMLReader xmlReader = new XMLReader(filePath);
		String tempSeed = "";

		try {
			depthLimit = Integer.valueOf(xmlReader.getFirstTagValue(
					"DepthLimit").trim());
			renderQPCount = Integer.valueOf(xmlReader.getFirstTagValue(
					"RenderQPCount").trim());
			targetQPCount = Integer.valueOf(xmlReader.getFirstTagValue(
					"TargetQPCount").trim());
			urlClassifierQPCount = Integer.valueOf(xmlReader.getFirstTagValue(
					"URLClassifierQPCount").trim());
			tempSeed = xmlReader.getFirstTagValue("seedList");
		} catch (ExceptionInvalidForm e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// assign seed list.
		String[] seeds = tempSeed.split("\\n");
		for (int index = 0; index < seeds.length; index++) {
			if (!seeds[index].trim().equals("")) {
				seedList.push(seeds[index].trim());
			}
		}

		// // Throw seedlist to targetQP
		// while(!seedList.isEmpty()){
		// QueueEntry entry = new QueueEntry();
		// entry.setSiteURL(seedList.pop());
		// entry.setDepth(depthLimit);
		// getAvailableTargetQL().push(entry);
		// }

		// http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=100
		// http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=101
		// http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=102
		// http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=103
		// http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=104
		// http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=105
		// http://media.daum.net/society/all/#page=1&type=tit_cont
		// http://media.daum.net/politics/all/#page=1&type=tit_cont
		// http://media.daum.net/economic/all/#page=1&type=tit_cont
		// http://media.daum.net/foreign/all/#page=1&type=tit_cont
		// http://media.daum.net/culture/all/#page=1&type=tit_cont
		// http://media.daum.net/digital/all/#page=1&type=tit_cont
		// http://media.daum.net/editorial/all/#page=1&type=tit_cont
		// http://news.nate.com/recent?mid=n0201
		// http://news.nate.com/recent?mid=n0301
		// http://news.nate.com/recent?mid=n0401
		// http://news.nate.com/recent?mid=n0501
		// http://news.nate.com/recent?mid=n0601
		// http://news.nate.com/recent?mid=n0701
	}

	private static void defaultSetting() {
		// TODO Auto-generated method stub
		// �⺻��ο� xml File �����ؼ� �ٽ� �ε� ��Ŵ..
		// file �����ϴ� ��� ��������.
		filePath = "./Ewok.Config";
	}

}
