package Ewok.RegionFilter;

import java.util.ArrayList;

import Ewok.RegionFilter.URLInfo.URLType;

import com.gargoylesoftware.htmlunit.html.DomElement;

/*
  @ Project : Ewok
  @ File Name : NaverRegionFilter.java
  @ Date : 2014-10-03
  @ Author : Kiheung Park
  @ Modifier : JS
*/

public class NaverRegionFilter implements RegionFilter {
	
	public ArrayList<URLInfo> filter(String urlAddress) {
		HTMLContent html = new HTMLContent(urlAddress);
		
		return filter1(html);
	}

	// OPTION1: For extracting all the targeted URLs in given HTMLpage
	public ArrayList<URLInfo> filter1(HTMLContent html) {
		ArrayList<URLInfo> urlList = new ArrayList<URLInfo>();
		
		html.regionFilteredList = html.pageHTML.getElementsByTagName("a");
		String filteredURLByHref;
		String naver = "http://news.naver.com";
		
		for (DomElement e : html.regionFilteredList) {
			filteredURLByHref = e.getAttribute("href");
			if(filteredURLByHref.matches(".*/main/read.nhn\\?mode.*")){
				if(filteredURLByHref.contains("http")){
					urlList.add(new URLInfo(filteredURLByHref, URLType.NewsArticle));					
				}else{
		            urlList.add(new URLInfo(naver + filteredURLByHref, URLType.NewsArticle));					
				}
	        }else if (filteredURLByHref.matches(".*#&.*")) {
				//urlList.add(html.urlAddress.toString()+filteredURLByHref);
				String temp = html.urlAddress.toString();
				urlList.add(new URLInfo(temp.substring(0, temp.indexOf("1=1")+5)+filteredURLByHref, URLType.PageNavi));
			}
		}
		
		html.close();
		
		return urlList;
	}
	

	// OPTION2: For extracting the targeted URLs from the specific region
	public ArrayList<String> filter2(HTMLContent html) {
		ArrayList<String> urlList = new ArrayList<String>();
		
		getRenderURLs(html, "section_body", urlList); // News Article URLs in the content list section; <div id="section_body"> ... </div>
		getRegionURLs(html, "paging", urlList); // Page navigation by page number URLs below the section body; <div id=paging> ... </div>
		getRegionURLs(html, "pagenavi_day", urlList); // Page navigation by day URLs below the page numbers; <div id=pagenavi_day> ... </div>
		
		return urlList;
	}

	// Extract targeted URLs for render
	private void getRenderURLs(HTMLContent html, String elementsById, ArrayList<String> urlList){
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
	private void getRegionURLs(HTMLContent html, String elementsById, ArrayList<String> urlList) {
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
