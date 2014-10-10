package Ewok.RegionFilter;

import java.util.ArrayList;
import java.util.List;

import Ewok.RegionFilter.URLInfo.URLType;

import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLAnchorElement;

//  @ Project : Ewok
//  @ File Name : NateRegionFilter.java
//  @ Date : 2014-10-04
//  @ Author : Kiheung Park
public class NateRegionFilter implements RegionFilter {
	public ArrayList<URLInfo> filter(String urlAddress) {
		HTMLContent html = new HTMLContent(urlAddress);
		ArrayList<URLInfo> urlList = new ArrayList<URLInfo>();
		String nate = "http://news.nate.com";
		String filteredURLByHref;
		html.regionFilteredList = html.pageHTML.getElementsByTagName("a");
		for (DomElement e : html.regionFilteredList) {
			filteredURLByHref = e.getAttribute("href");
			if(filteredURLByHref.contains("http://news.nate.com/view/") && filteredURLByHref.contains("?mid=")){
				urlList.add(new URLInfo(filteredURLByHref, URLType.NewsArticle));
			}else if (filteredURLByHref.contains("&date=") || filteredURLByHref.contains("&page=")) {
				urlList.add(new URLInfo(nate+filteredURLByHref, URLType.PageNavi));
			}
		}
		return urlList;
	}
	
}
