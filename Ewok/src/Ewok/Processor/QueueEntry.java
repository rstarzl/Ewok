package Ewok.Processor;

import java.io.Serializable;

import Ewok.GlobalContext.TYPE_OF_SITE;
import Ewok.DB.URL;
import Ewok.RegionFilter.HTMLContent;
import Ewok.RegionFilter.URLInfo;
import Ewok.RegionFilter.URLInfo.URLType;
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
	private URLInfo	info;
	
	@Override
	public String	toString(){
		return targetAddrInfo.toString() + "\r\n" + article.toString();
	}
	
	public QueueEntry(String url){
		this.targetAddrInfo = new URL(url);
		this.article = new Article();
	}

	public QueueEntry(){
		this.targetAddrInfo = new URL("temp");
		this.article = new Article();
	}
	

	/* Getter, Setter */
	public URLType getUrlType(){
		return info.getUrlType();
	}
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
	public void setURLInfo(URLInfo workingURL) {
		this.info = workingURL;
	}	
	
	/* Getter, Setter */
	
	public int decreaseDepthCount() {
		return depth--;
	}


}
