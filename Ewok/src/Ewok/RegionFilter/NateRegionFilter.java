package Ewok.RegionFilter;

import java.util.ArrayList;

import com.gargoylesoftware.htmlunit.html.DomElement;

//  @ Project : Ewok
//  @ File Name : NateRegionFilter.java
//  @ Date : 2014-10-03
//  @ Author : Kiheung Park
public class NateRegionFilter implements RegionFilter {
	ArrayList<String> urlList = new ArrayList<String>();

	public ArrayList<String> filter1(HTMLContent html) {
		getRenderURLs(html, "newsContents"); // News Article URLs in the content list section; <div id="section_body"> ... </div>
//		getRegionURLs(html, "paging"); // Page navigation by page number URLs below the section body; <div id=paging> ... </div>
//		getRegionURLs(html, "pagenavi_day"); // Page navigation by day URLs below the page numbers; <div id=pagenavi_day> ... </div>
		
		return urlList;
	}
	

	// Extract targeted URLs for render
	private void getRenderURLs(HTMLContent html, String elementsById){
//		System.out.println(html.pageHTML.asXml());
		html.regionFilteredList = html.pageHTML.getElementsByIdAndOrName(elementsById);
		String filteredURLByHref;
		for(DomElement div1 : html.regionFilteredList) {
			for(DomElement div2 : div1.getChildElements()){
				for(DomElement div3 : div2.getChildElements()){
					filteredURLByHref = div3.getFirstElementChild().getAttribute("href");
					if(filteredURLByHref.contains("news.nate.com/view/")){
						urlList.add(filteredURLByHref);
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
