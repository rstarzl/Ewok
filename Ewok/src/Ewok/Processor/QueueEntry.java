package Ewok.Processor;

import Ewok.DB.URL;
import Ewok.RegionFilter.HTMLContent;

public class QueueEntry {
	private URL	url;
	private int depth;
	private HTMLContent htmlContent;
	
	/* Getter, Setter */
	public URL getUrl() {
		return url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public HTMLContent getHtmlContent() {
		return htmlContent;
	}
	public void setHtmlContent(HTMLContent htmlContent) {
		this.htmlContent = htmlContent;
	}
	/* Getter, Setter */
	
	public int decreaseDepthCount() {
		return depth--;
	}
}
