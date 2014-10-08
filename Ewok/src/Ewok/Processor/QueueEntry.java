package Ewok.Processor;

import java.io.Serializable;

import Ewok.GlobalContext.TYPE_OF_SITE;
import Ewok.DB.URL;
import Ewok.RegionFilter.HTMLContent;
import Ewok.Render.Article;
/**
 * 
 * @author JS
 *
 */
public class QueueEntry implements Serializable{
	private URL	targetAddrInfo;
	private int depth;
	private Article	article;
	
	/* Getter, Setter */
	public URL getUrl() {
		return targetAddrInfo;
	}
	public void setUrl(URL url) {
		this.targetAddrInfo = url;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
		public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	
	
	public TYPE_OF_SITE	getSiteName(){
		return	targetAddrInfo.getSiteName();
	}
	public String	getSiteURL(){
		return	targetAddrInfo.getUrl();
	}
	public void	setSiteURL(String	url){
		this.targetAddrInfo.setUrl(url);
	}
	
	
	/* Getter, Setter */
	
	public int decreaseDepthCount() {
		return depth--;
	}
}
