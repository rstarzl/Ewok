package Ewok.RegionFilter;

import java.util.ArrayList;

import Ewok.RegionFilter.URLInfo.URLType;

import com.gargoylesoftware.htmlunit.html.DomElement;
//@ Project : Ewok
//@ File Name : DaumRegionFilter.java
//@ Date : 2014-10-03
//@ Author : Kiheung Park
public class DaumRegionFilter implements RegionFilter {
	public ArrayList<URLInfo> filter(String urlAddress) {
		HTMLContent html = new HTMLContent(urlAddress);
		ArrayList<URLInfo> urlList = new ArrayList<URLInfo>();
		html.regionFilteredList = html.pageHTML.getElementsByTagName("a");
		String daum = "http://media.daum.net";
		String filteredURLByHref;
		for (DomElement e : html.regionFilteredList) {
			filteredURLByHref = e.getAttribute("href");
			if(filteredURLByHref.contains("/v/") && !filteredURLByHref.contains("RIGHT_")){ // For extracting render URLs but excluding sorted list URLs on right side of the page
				urlList.add(new URLInfo(daum + filteredURLByHref, URLType.NewsArticle));
			}else if (filteredURLByHref.contains("#page=")) { // For extracting page navigation URLs by both page numbers and dates
				String temp = html.urlAddress.toString();
				urlList.add(new URLInfo(temp.substring(0, temp.indexOf("/all/"))+filteredURLByHref, URLType.PageNavi));
			}
		}
		return urlList;
	}

}
