package Ewok.DB;

import Ewok.GlobalContext.TYPE_OF_SITE;
/**
 * 
 * @author JS
 *
 */
public class URL {
	private String[] childNames = {"URL", "siteName"};
	private String url;
	private TYPE_OF_SITE	siteName;
	
	
	private String nodeName;
	
		
	public URL (String url){
		this.url = url;
		this.nodeName = url;
		this.siteName = extractSiteName();
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public TYPE_OF_SITE getSiteName() {
		return siteName;
	}

	public String getNodeName(){
		return	this.nodeName;
	}
	public String get1stChildName(){
		return	this.childNames[0];
	}
	public String get2ndChildName(){
		return	this.childNames[1];
	}
	public String get1stChildValue(){
		return	this.url;
	}
	public String get2ndChildValue(){
		return	this.siteName.toString();	//TODO : must check.
	}
	
		
	/**
	 * URL로 사이트 소속을 밝힌다. 
	 * TODO:정규식으로 구현
	 * @return
	 */
	private TYPE_OF_SITE extractSiteName(){
		if (this.url.contains("naver")){
			return	TYPE_OF_SITE.NAVER;
		}
		if (this.url.contains("nate")){
			return	TYPE_OF_SITE.NATE;
		}
		if (this.url.contains("daum")){
			return	TYPE_OF_SITE.DAUM;
		}
		
		return TYPE_OF_SITE.NON;
	}
}
