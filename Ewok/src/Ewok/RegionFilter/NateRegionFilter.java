package Ewok.RegionFilter;

import java.util.ArrayList;

import Ewok.GlobalContext.URLType;

import com.gargoylesoftware.htmlunit.html.DomElement;

//  @ Project : Ewok
//  @ File Name : NateRegionFilter.java
//  @ Date : 2014-10-04
//  @ Author : Kiheung Park
public class NateRegionFilter extends RegionFilter {
	public ArrayList<URLInfo> filter(String urlAddress) throws Exception{
		HTMLContent html = new HTMLContent(urlAddress);
		ArrayList<URLInfo> urlList = new ArrayList<URLInfo>();
		String nate = "http://news.nate.com";
		String filteredURLByHref;
		html.regionFilteredList = html.pageHTML.getElementsByTagName("a");
		
		for (DomElement e : html.regionFilteredList) {
			filteredURLByHref = e.getAttribute("href");
			if(filteredURLByHref.matches(".*/view/.*\\?mid=.*")){
				if(filteredURLByHref.contains("http")){
					urlList.add(new URLInfo(filteredURLByHref, URLType.NewsArticle));
				}else{
					urlList.add(new URLInfo(nate + filteredURLByHref, URLType.NewsArticle));					
				}
			}else if (filteredURLByHref.matches(".*\\&date=.*") || filteredURLByHref.matches(".*\\&page=.*")) {
				urlList.add(new URLInfo(nate+filteredURLByHref, URLType.PageNavi));
			}
		}
		
		html.close();
		
		return urlList;
	}
	
}
