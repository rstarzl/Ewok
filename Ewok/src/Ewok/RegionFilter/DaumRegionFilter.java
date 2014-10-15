package Ewok.RegionFilter;

import java.util.ArrayList;

import Ewok.GlobalContext;
import Ewok.RegionFilter.URLInfo.URLType;

import com.gargoylesoftware.htmlunit.html.DomElement;
//@ Project : Ewok
//@ File Name : DaumRegionFilter.java
//@ Date : 2014-10-03
//@ Author : Kiheung Park
public class DaumRegionFilter extends RegionFilter {
	public ArrayList<URLInfo> filter(String urlAddress) {
		HTMLContent html = new HTMLContent(urlAddress);
		ArrayList<URLInfo> urlList = new ArrayList<URLInfo>();
		html.regionFilteredList = html.pageHTML.getElementsByTagName("a");
		String daum = "http://media.daum.net";
		String filteredURLByHref;
		for (DomElement e : html.regionFilteredList) {
			filteredURLByHref = e.getAttribute("href");
			// For extracting render URLs but excluding sorted list URLs on right side of the page
			if(filteredURLByHref.matches(".*/v/[0-9]{17}$")){
				if(filteredURLByHref.contains("http")){
					urlList.add(new URLInfo(filteredURLByHref, URLType.NewsArticle));
				}else{
					urlList.add(new URLInfo(daum + filteredURLByHref, URLType.NewsArticle));	
				}
			}else if(filteredURLByHref.matches(".*#page=.*")){
				String temp = html.urlAddress.toString();
				urlList.add(new URLInfo(temp.substring(0, temp.indexOf("/all/")+5)+filteredURLByHref, URLType.PageNavi));	
			}

		}
		
		html.close();
		
		return urlList;
	}

}
