import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import Ewok.GlobalContext;
import Ewok.Processor.QueueEntry;


public class Rollback {
	private HashMap<String, Integer> tpList = new HashMap<String, Integer>();
	private HashMap<String, Integer> cpList = new HashMap<String, Integer>();
	private HashMap<String, Integer> rpList = new HashMap<String, Integer>();
	private BufferedReader reader;

	
	
	public Rollback(String fileName){
		reader = GlobalContext.loadSnapShot(fileName);
	}

	public void rollbackEachQ(){
		readQueueSnapshot();
		
		GlobalContext.createSnapShot();
		
		assignQueue();
	}

	private void readQueueSnapshot() {
		// rules
		// added -> +
		// removed -> -
		// Q name -> TP, CP, RP
		// spliter -> ,(Space) 
		// ex)   +, CP, url~~~
		
		String line = null;
		try {
			while((line=reader.readLine())!=null){
				String[] item = line.split(", ");
				// Choose Q
				HashMap<String, Integer> selectedQ = null;
				if (item[1].equals("TP")){
					selectedQ = tpList;
				} else if (item[1].equals("CP")){
					selectedQ = cpList;
				} else if (item[1].equals("RP")){
					selectedQ = rpList;
				}
				
				// + or -
				if (item[1].equals("+")){
					selectedQ.put(item[2].trim(), 0);
				} else if (item[1].equals("-")){
					selectedQ.remove(item[2].trim());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void assignQueue() {
		for (String url: tpList.keySet()){
			QueueEntry entry = new QueueEntry(url);
			if (GlobalContext.getURLDB().query(url) != null){
				GlobalContext.getURLDB().delete(entry);
			}
			GlobalContext.getAvailableTargetQL().push(entry);
		}
		
		for (String url: cpList.keySet()){
			QueueEntry entry = new QueueEntry(url);
			if (GlobalContext.getURLDB().query(url) != null){
				GlobalContext.getURLDB().delete(entry);
			}
			GlobalContext.getAvailableClassifierQL().push(entry);
		}
	
		for (String url: rpList.keySet()){
			QueueEntry entry = new QueueEntry(url);
			if (GlobalContext.getURLDB().query(url) != null){
				GlobalContext.getURLDB().delete(entry);
			}
			GlobalContext.getAvailableRenderingQL().push(entry);
		}
	}

}
