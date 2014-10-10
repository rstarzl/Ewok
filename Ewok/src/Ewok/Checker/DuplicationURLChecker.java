package Ewok.Checker;

import Ewok.GlobalContext;
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
		// URL DB Access.

		// true, duplicated URL
		// false, non-duplicated URL
		if (GlobalContext.getURLDB().queryURLFromUrlString(entry.getSiteURL()) == null){
			return	false;
		} else {
			return	true;
		}
	}

}
