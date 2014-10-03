package Ewok.RegionFilter;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.JavaScriptPage;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.xml.XmlPage;

/*
  @ Project : Ewok
  @ File Name : NaverRegionFilter.java
  @ Date : 2014-10-03
  @ Author : Kiheung Park
*/

public class NaverRegionFilter implements RegionFilter {
	ArrayList<String> urlList = new ArrayList<String>();

	// OPTION1: For extracting all the targeted URLs in given HTMLpage
	public ArrayList<String> filter1(HTMLContent html) {
		html.regionFilteredList = html.pageHTML.getElementsByTagName("a");
		String filteredURLByHref;
		for (DomElement e : html.regionFilteredList) {
			filteredURLByHref = e.getAttribute("href");
			if(filteredURLByHref.contains("http://news.naver.com/main/read.nhn?mode")){
				urlList.add(filteredURLByHref);
			}else if (filteredURLByHref.contains("#&")) {
				urlList.add(html.urlAddress.toString()+filteredURLByHref);
			}
		}
		return urlList;
	}

	// OPTION2: For extracting the targeted URLs from the specific region
	public ArrayList<String> filter2(HTMLContent html) {
		getRenderURLs(html, "section_body"); // News Article URLs in the content list section; <div id="section_body"> ... </div>
		getRegionURLs(html, "paging"); // Page navigation by page number URLs below the section body; <div id=paging> ... </div>
		getRegionURLs(html, "pagenavi_day"); // Page navigation by day URLs below the page numbers; <div id=pagenavi_day> ... </div>
		
		return urlList;
	}

	// Extract targeted URLs for render
	private void getRenderURLs(HTMLContent html, String elementsById){
		html.regionFilteredList = html.pageHTML.getElementsByIdAndOrName(elementsById);
		String filteredURLByHref;
		for(DomElement div : html.regionFilteredList) {
			for(DomElement ul : div.getChildElements()){
				for(DomElement li : ul.getChildElements()){
					filteredURLByHref = li.getFirstElementChild().getAttribute("href");
					if(filteredURLByHref.contains("http://news.naver.com/main/read.nhn?mode")){
						urlList.add(filteredURLByHref);
					}else if(filteredURLByHref.contains("/main/read.nhn?mode")){
						urlList.add("http://news.naver.com" + filteredURLByHref);			
					}
				}
			}
		}
	}
	
	// Extract region filtered URLs in which recursively used for extracting render URLs
	private void getRegionURLs(HTMLContent html, String elementsById) {
		html.regionFilteredList = html.pageHTML.getElementsByIdAndOrName(elementsById);
		String filteredURLByHref;
		for(DomElement div : html.regionFilteredList){
			for(DomElement a : div.getChildElements()){
				if(a.getLocalName().matches("a")){
					filteredURLByHref = a.getAttribute("href");
					if(filteredURLByHref.contains("#&")){
						urlList.add(html.urlAddress.toString()+filteredURLByHref);
					}
				}
			}
		}
	}
}
