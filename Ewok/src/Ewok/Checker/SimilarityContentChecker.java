package Ewok.Checker;

import java.util.ArrayList;

import Ewok.GlobalContext;
import Ewok.Processor.QueueEntry;
import Ewok.DB.*;
import Ewok.Render.*;
//
//
//
//  @ Project : Ewok
//  @ File Name : SimilarityContentChecker.java
//  @ Date : 2014-09-29
//  @ Author : Member
//
//

public class SimilarityContentChecker extends Checker {
	// Using Meaningful DB

	public void doJob() {

	}

	private void isDuplication() {

	}

	@Override
	public boolean check(QueueEntry entry) {
		// sim -> true
		// non-sim -> false

		ArrayList<QueueEntry> entriesFromDB = GlobalContext.getMeaningfulDB().queryArticleFromUrlString(entry.getSiteURL());
		
		for (int index = 0; index < entriesFromDB.size(); index++){
			if (entry.getArticle().content.equals(entriesFromDB.get(index).getArticle().content)){
				return	true;
			}
		}

		return	false;
	}
}
