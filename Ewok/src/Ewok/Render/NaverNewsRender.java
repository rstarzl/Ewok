package Ewok.Render;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import Ewok.RegionFilter.HTMLContent;

//  @ Project : Ewok
//  @ File Name : NaverNewsParser.java
//  @ Date : 2014-10-03
//  @ Author : Kiheung Park
public class NaverNewsRender implements Render {
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
		renderArticle.title = ((DomElement) targetedPage.getFirstByXPath("html/body/div/table/tbody/tr/td[2]/div/div[2]/div[2]/h3")).asText();
//		renderArticle.date = ((DomElement) targetedPage.getFirstByXPath("html/body/div/table/tbody/tr/td[2]/div/div[2]/div[2]/div/span[3]")).asText();
//		renderArticle.date = ((DomElement) targetedPage.getFirstByXPath("html/body/div/table/tbody/tr/td[2]/div/div[2]/div[2]/div/span")).asText();
		
		for(DomElement e : targetedPage.getElementsByTagName("span")){
			if(e.getAttribute("class").contains("t11")){
				renderArticle.date = e.asText();
			}
		}
//		for(DomElement e : ((DomElement) targetedPage.getFirstByXPath("html/body/div/table/tbody/tr/td[2]/div/div[2]/div[2]/div/")).getElementsByTagName("span")){
//			if(e.getAttribute("class") == "t11"){
//				renderArticle.date = e.asText();
//			}
//		}
		renderArticle.press = ((DomElement) targetedPage.getFirstByXPath("html/body/div/table/tbody/tr/td[2]/div/div[2]/div/a/img")).getAttribute("title");
		renderArticle.content = ((HtmlDivision) targetedPage.getElementById("articleBody")).asText();
		
		return renderArticle;
	}
}
