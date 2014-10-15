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
		Render	rendering = renders.get(entry.getSiteName());
		
		//셋팅.
		rendering.setParam(entry.getSiteURL());
		
		//실행
		rendering.start();
		
		try {
			rendering.join(GlobalContext.WAIT_TIME_SECEND * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			throw new RenderTerminatedException();
		}
		
		// 겟..... 결과가 짝이 안맞으면 익셉션.
		Article result = rendering.getResult();
		if (result != null){
			entry.setArticle(result);
		} else {
			throw new RenderTerminatedException();
		}
//		entry.setArticle(renders.get(entry.getSiteName()).render(entry.getSiteURL()));
	}

}
