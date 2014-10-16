package Ewok.Render;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import Ewok.RegionFilter.HTMLContent;
//
//
//
//  @ Project : Ewok
//  @ File Name : NateNewsParser.java
//  @ Date : 2014-09-29
//  @ Author : Member
//
//




public class NateNewsRender extends Render {
	HtmlPage targetedPage = null;
	public Article render(String targetedURL) throws NonTargetException {
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
	    java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
	    
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		Article renderArticle = new Article();
		
		try {
			targetedPage = webClient.getPage(targetedURL);
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			// TITLE
			if(targetedPage.getUrl().toString().contains("sports.news") || targetedPage.getUrl().toString().contains("pann.nate") || targetedPage.getUrl().toString().contains("style.nate")){
				throw new NonTargetException();
			}
			renderArticle.title = ((DomElement) targetedPage.getFirstByXPath("html/body/div[2]/div[3]/div/div/div/h3")).asText();
			
			// DATE 
			for(DomElement e : targetedPage.getElementsByTagName("span")){
				if(e.getAttribute("class").contains("firstDate") || e.getAttribute("class").contains("lastDate")){
					renderArticle.date = e.getElementsByTagName("em").get(0).asText();
					break;
				}
			}
			
			// PRESS
			renderArticle.press = ((DomElement) targetedPage.getFirstByXPath("html/body/div[2]/div[3]/div/div/div/p/span/a")).asText();
	
			// Contents
			renderArticle.content = ((HtmlDivision) targetedPage.getElementById("realArtcContents")).asText();
		} catch (NullPointerException e){
			webClient.closeAllWindows();
			
			throw new NonTargetException();
		} 

		webClient.closeAllWindows();
		
		return renderArticle;
	}
}
