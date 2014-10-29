package Ewok.Checker;

import Ewok.GlobalContext;
import Ewok.GlobalContext.URLType;
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
		if (entry.getUrlType() != URLType.PageNavi){
			if (GlobalContext.getURLDB().queryURLFromUrlString(entry.getSiteURL()) == null){
				return	false;
			} else {
				return	true;
			}
		} else {
			return false;
		}
		
//		return	false;
	}
}
