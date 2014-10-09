package Ewok.Checker;

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

		boolean Contentchecking = false;
		Article article;

		MeaningfulDB DBcheckContents = new MeaningfulDB();
		article = DBcheckContents.queryContentsFromContentString(entry
				.getArticle().content);

		if (article != null) {
			Contentchecking = true;
		}

		return Contentchecking;
	}
}
