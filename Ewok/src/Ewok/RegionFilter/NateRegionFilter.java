package Ewok.RegionFilter;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLAnchorElement;

//  @ Project : Ewok
//  @ File Name : NateRegionFilter.java
//  @ Date : 2014-10-04
//  @ Author : Kiheung Park
public class NateRegionFilter implements RegionFilter {
	public ArrayList<String> filter(HTMLContent html) {
		ArrayList<String> urlList = new ArrayList<String>();
		String nate = "http://news.nate.com";
		String filteredURLByHref;
		html.regionFilteredList = html.pageHTML.getElementsByTagName("a");
		for (DomElement e : html.regionFilteredList) {
			filteredURLByHref = e.getAttribute("href");
			if(filteredURLByHref.contains("http://news.nate.com/view/") && filteredURLByHref.contains("?mid=")){
				urlList.add(filteredURLByHref);
			}else if (filteredURLByHref.contains("&date=") || filteredURLByHref.contains("&page=")) {
				urlList.add(nate+filteredURLByHref);
			}
		}
		return urlList;
	}
	
}
