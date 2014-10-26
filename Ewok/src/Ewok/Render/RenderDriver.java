package Ewok.Render;

import java.lang.Thread.State;
import java.util.HashMap;

import Ewok.GlobalContext;
import Ewok.GlobalContext.TYPE_OF_SITE;
import Ewok.Processor.QueueEntry;

/**
 * 
 * @author JS
 *
 */
public class RenderDriver {

	HashMap <TYPE_OF_SITE, Render>	renders = new HashMap <TYPE_OF_SITE, Render>();
	
	public RenderDriver(){
		for (TYPE_OF_SITE	site : GlobalContext.SELECTED_SITE){
			switch (site){ 
			case DAUM:
				renders.put(site, new DaumNewsRender());
				break;
			case NATE:
				renders.put(site, new NateNewsRender());
				break;
			case NAVER:
				renders.put(site, new NaverNewsRender());
				break;
			default:
				break;
			}
		}
	}

	public void render(QueueEntry entry) throws RenderTerminatedException{
		try {
			entry.setArticle(renders.get(entry.getSiteName()).render(entry.getSiteURL())); 
		} catch (Exception e) {
			throw new RenderTerminatedException(); 
		}
	}
}
