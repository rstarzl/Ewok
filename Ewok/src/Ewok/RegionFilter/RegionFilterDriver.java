package Ewok.RegionFilter;

import java.util.ArrayList;
import java.util.HashMap;

import Ewok.GlobalContext;
import Ewok.GlobalContext.TYPE_OF_SITE;
import Ewok.Processor.QueueEntry;
/**
 * 
 * @author JS
 *
 */
public class RegionFilterDriver {
	HashMap <TYPE_OF_SITE, RegionFilter>	regionFilters = new HashMap <TYPE_OF_SITE, RegionFilter>();
	
	public RegionFilterDriver(){
		for (TYPE_OF_SITE	site : GlobalContext.SELECTED_SITE){
			switch (site){ 
			case DAUM:
				regionFilters.put(site, new DaumRegionFilter());
				break;
			case NATE:
				regionFilters.put(site, new NateRegionFilter());
				break;
			case NAVER:
				regionFilters.put(site, new NaverRegionFilter());
				break;
			default:
				break;
			}
		}
	}

	public ArrayList<String> filter(QueueEntry entry) {
		// TODO : default action.
//		if (!regionFilters.containsKey(entry.getSiteName())){
//			;
//		}
		RegionFilter regionFilter = regionFilters.get(entry.getSiteName());
		return regionFilters.get(entry.getSiteName()).filter(entry.getSiteURL());
	}
	
	
	
}
