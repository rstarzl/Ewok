package Ewok.RegionFilter;

import java.util.ArrayList;
import java.util.HashMap;

import Ewok.GlobalContext;
import Ewok.GlobalContext.TYPE_OF_SITE;
import Ewok.Processor.QueueEntry;
import Ewok.Render.Article;
import Ewok.Render.Render;
import Ewok.Render.RenderTerminatedException;
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

	public ArrayList<URLInfo> filter(QueueEntry entry) throws FilterTerminatedException{		
		try {
			return regionFilters.get(entry.getSiteName()).filter(entry.getSiteURL());
		} catch (Exception e) {
			throw	new FilterTerminatedException();
		}
	}
	
	
	
}
