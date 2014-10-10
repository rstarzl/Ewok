package Ewok.Checker;
//
//
//
//  @ Project : Ewok
//  @ File Name : WorkingChecker.java
//  @ Date : 2014-09-29
//  @ Author : Member
//
//
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import Ewok.GlobalContext;
import Ewok.Processor.QueueEntry;
import Ewok.DB.*;



public class WorkingChecker extends Checker {


	@Override
	public boolean check(QueueEntry entry) {
		// Using Regular expression.
		// TODO 추후구현
		boolean URLchecking = false;
		ArrayList<QueueEntry> url;
		
		String urlCheck = entry.getSiteURL();
		
		if(urlCheck.contains(s)
			URLchecking = true;
		}
		
		return URLchecking;
	}

//	public boolean doJob(HTMLContent URL) { // Input : URL , true : it is not a duplicate , false : it should be dropped
//		boolean result = false;
//		boolean a = isDuplication(URL);
//		if(a==true){
//			result = true;
//		}
//		return result;
//	}
//
//	private boolean isDuplication(HTMLContent URL) { // Input : URL , true : No duplicate URL in DB , false : Duplicate URL exists in DB
//		boolean URLDupCheck = false;
//
//		String url = URL.url;
//		DBEntry search = new DBEntry();
//		String NullOrKey = search.findByParentKey(url);
//		
//		if(NullOrKey == null){
//			URLDupCheck = true;
//		}
//		return URLDupCheck;
//	}
	
//	private boolean isDomainRestriction(HTMLContent URL) { // Input : URL , true : input URL is good to go , false : input URL is advertisement or other unrelated URL
//		boolean URLDomRstrCheck = false;
//		ArrayList<String> n = new ArrayList<String>();
//		for (String str : n){
//			if(URL.toString().contains(str)){
//				URLDomRstrCheck = true;
//				break;
//			}	
//		}
//		return URLDomRstrCheck;
//	}
}
