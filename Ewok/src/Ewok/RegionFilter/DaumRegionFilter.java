package Ewok.RegionFilter;

import java.util.ArrayList;

import com.gargoylesoftware.htmlunit.html.DomElement;
//@ Project : Ewok
//@ File Name : DaumRegionFilter.java
//@ Date : 2014-10-03
//@ Author : Kiheung Park
public class DaumRegionFilter implements RegionFilter {
	public ArrayList<String> filter(HTMLContent html) {
		ArrayList<String> urlList = new ArrayList<String>();
		
		html.regionFilteredList = html.pageHTML.getElementsByTagName("a");
		String daum = "http://media.daum.net/";
		String filteredURLByHref;
		for (DomElement e : html.regionFilteredList) {
			filteredURLByHref = e.getAttribute("href");
			if(filteredURLByHref.contains("/v/") && !filteredURLByHref.contains("RIGHT_")){ // For extracting render URLs but excluding sorted list URLs on right side of the page
				urlList.add(daum + filteredURLByHref);
			}else if (filteredURLByHref.contains("#page=")) { // For extracting page navigation URLs by both page numbers and dates
				urlList.add(html.urlAddress.toString()+filteredURLByHref);
			}
		}
		return urlList;
	}

}
