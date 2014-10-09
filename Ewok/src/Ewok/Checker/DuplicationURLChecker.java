package Ewok.Checker;

import Ewok.Processor.QueueEntry;
import Ewok.DB.*;

/**
 * 
 * @author JS
 *
 */
public class DuplicationURLChecker extends Checker {

	@Override
	public boolean check(QueueEntry entry) {
		// TODO Auto-generated method stub
		// URL DB Access.

		// true, duplicated URL
		// false, non-duplicated URL
		
		boolean URLchecking = false;
		URL url;

		URLDB DBcheckURL = new URLDB();
		url = DBcheckURL.queryURLFromUrlString(entry.getUrl().getUrl());

		if (url != null) {
			URLchecking = true;
		}

		return URLchecking;
	}

}
