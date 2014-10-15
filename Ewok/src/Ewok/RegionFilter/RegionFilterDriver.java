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
		RegionFilter	regionFilter = regionFilters.get(entry.getSiteName());
		
		//셋팅.
		regionFilter.setParam(entry.getSiteURL());
		
		//실행
		regionFilter.start();
		
		try {
			regionFilter.join(GlobalContext.WAIT_TIME_SECEND * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			throw new FilterTerminatedException();
		}
		
		// 겟..... 결과가 짝이 안맞으면 익셉션.
		ArrayList<URLInfo> result = regionFilter.getResult();
		if (result == null){
			throw new FilterTerminatedException();
		}
		
		return	result;
		
//		RegionFilter regionFilter = regionFilters.get(entry.getSiteName());
//		return regionFilters.get(entry.getSiteName()).filter(entry.getSiteURL());
	}
	
	
	
}
