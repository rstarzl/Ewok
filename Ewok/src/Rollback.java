import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import Ewok.GlobalContext;
import Ewok.GlobalContext.TYPE_OF_SITE;
import Ewok.GlobalContext.URLType;
import Ewok.Processor.QueueEntry;
import Ewok.RegionFilter.URLInfo;


public class Rollback {
	private HashMap<String, URLType> tpList = new HashMap<String, URLType>();
	private HashMap<String, URLType> cpList = new HashMap<String, URLType>();
	private HashMap<String, URLType> rpList = new HashMap<String, URLType>();
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
				HashMap<String, URLType> selectedQ = null;
				if (item[1].equals("TP")){
					selectedQ = tpList;
				} else if (item[1].equals("CP")){
					selectedQ = cpList;
				} else if (item[1].equals("RP")){
					selectedQ = rpList;
				}
				
				// + or -
				if (item[0].equals("+")){
					if (item.length > 3){// for consistency to Old version. 
						selectedQ.put(item[2].trim(), URLType.valueOf(item[3]));
					} else {
						selectedQ.put(item[2].trim(), URLType.UnKnown);
					}
					
				} else if (item[0].equals("-")){
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
			entry.setURLInfo(new URLInfo(url, tpList.get(url)));
			if (GlobalContext.getURLDB().query(url) != null){
				GlobalContext.getURLDB().delete(entry);
			}
			GlobalContext.getAvailableTargetQL().push(entry);
		}
		
		for (String url: cpList.keySet()){
			QueueEntry entry = new QueueEntry(url);
			entry.setURLInfo(new URLInfo(url, cpList.get(url)));
			if (GlobalContext.getURLDB().query(url) != null){
				GlobalContext.getURLDB().delete(entry);
			}
			GlobalContext.getAvailableClassifierQL().push(entry);
		}
	
		for (String url: rpList.keySet()){
			QueueEntry entry = new QueueEntry(url);
			entry.setURLInfo(new URLInfo(url, rpList.get(url)));
			if (GlobalContext.getURLDB().query(url) != null){
				GlobalContext.getURLDB().delete(entry);
			}
			GlobalContext.getAvailableRenderingQL().push(entry);
		}
	}

}
