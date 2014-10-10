package Ewok.RegionFilter;

public class URLInfo {
	private String url;
	public enum URLType { PageNavi, NewsArticle }
	private URLType urlType;
	
	public URLInfo(String url, URLType	urlType){
		this.url = url;
		this.urlType = urlType;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public URLType getUrlType() {
		return urlType;
	}
	public void setUrlType(URLType urlType) {
		this.urlType = urlType;
	}
	
}