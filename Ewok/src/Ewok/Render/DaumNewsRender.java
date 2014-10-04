package Ewok.Render;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import Ewok.RegionFilter.HTMLContent;

//  @ Project : Ewok
//  @ File Name : DaumNewsParser.java
//  @ Date : 2014-09-29
//  @ Author : Member
public class DaumNewsRender implements Render {
	HtmlPage targetedPage = null;
	public Article render(String targetedURL) {
		WebClient webClient = new WebClient();
		Article renderArticle = new Article();
		
		try {
			targetedPage = webClient.getPage(targetedURL);
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TITLE 
		renderArticle.title = ((DomElement) targetedPage.getFirstByXPath("html/body/div[2]/div[2]/h2")).asText();
		
		// DATE 
		for(DomElement e : targetedPage.getElementsByTagName("span")){
			if(e.getAttribute("class").contains("ff_tahoma")){
				renderArticle.date = e.asText();
				break;
			}
		}
		
		// PRESS
		renderArticle.press = ((DomElement) targetedPage.getFirstByXPath("html/body/div[2]/div[2]/div/div/div/div/div/div/div/span[2]/span")).asText();

		// Contents
		renderArticle.content = ((HtmlDivision) targetedPage.getElementById("newsBody")).asText();
		
		return renderArticle;
	}
}
