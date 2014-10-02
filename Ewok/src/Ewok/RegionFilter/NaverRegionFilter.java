package Ewok.RegionFilter;

import java.util.ArrayList;

import com.gargoylesoftware.htmlunit.JavaScriptPage;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.xml.XmlPage;

//
//
//
//  @ Project : Ewok
//  @ File Name : NaverRegionFilter.java
//  @ Date : 2014-10-02
//  @ Author : Kiheung Park
//
//


public class NaverRegionFilter implements RegionFilter {
	ArrayList<String> URLList = new ArrayList<String>();
	
	// For extracting all URLs in given HTMLpage
	public ArrayList<String> filter(HTMLContent html) {
		html.regionFilteredList = html.startPage.getElementsByTagName("a");
//		html.regionFilteredList.add(html.startPageXML.);
		String filteredURLByHref;
		for(DomElement e : html.regionFilteredList){
			filteredURLByHref = e.getAttribute("href");
        	if(filteredURLByHref.contains("http://news.naver.com/main/read.nhn?mode")){
        		URLList.add(filteredURLByHref);
        	}else if(filteredURLByHref.contains("_paging")){
        		URLList.add(filteredURLByHref);
        	}
        }
		return URLList;
	}
	
	// For extracting URLs from the specific region
	public ArrayList<String> filter2(HTMLContent html) {
		html.regionFilteredList = html.startPage.getElementsByIdAndOrName("section_body");
		for(DomElement e : html.regionFilteredList){
        	String filteredURLByHref = e.getAttribute("href");
        	String filteredURLByClass = e.getAttribute("class");
        	if(filteredURLByHref.contains("http://news.naver.com/main/read.nhn?mode")){
        		URLList.add(filteredURLByHref);
        	}else if(filteredURLByClass.contains("_paging")){
        		URLList.add(filteredURLByClass);        		
        	}else if(filteredURLByClass.contains("_dateNavigation")){
        		URLList.add(filteredURLByClass);        		        		
        	}
        }
		return URLList;
	}
/*
	public String getPageAsText(){
		  if (getContentPage() instanceof HtmlPage)   return ((HtmlPage)getContentPage()).asXml();
		  if (getContentPage() instanceof TextPage)   return ((TextPage)getContentPage()).getContent();
		  if (getContentPage() instanceof XmlPage)   return ((XmlPage)getContentPage()).asXml();
		  if (getContentPage() instanceof JavaScriptPage)   return ((JavaScriptPage)getContentPage()).getContent();
		  throw new IllegalStateException("This page can not be converted to text.  Page type is " + getContentPage().getClass().getName());
	}
*/	
	
}
