package Ewok.DB;

public class URL {
	private String[] childNames = {"URL", "siteName"};
	String url;
	String siteName;
	private String nodeName;
	
		
	public URL (String url){
		this.url = url;
		this.nodeName = url;
		this.siteName = extractSiteName();
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
		return	this.siteName;
	}
	
		
	/**
	 * URL로 사이트 소속을 밝힌다. 
	 * TODO:정규식으로 구현
	 * @return
	 */
	private String extractSiteName(){
		return	"Non";
	}
}
