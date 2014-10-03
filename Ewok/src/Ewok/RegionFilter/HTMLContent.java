package Ewok.RegionFilter;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HTMLParser;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptEngine;
import com.gargoylesoftware.htmlunit.xml.XmlPage;

/*
 * HTMLUNIT에 URL을 넣었을 때 나오는 자료구조를 담는 자료구조 
 * HTMLUNIT에 버그가 발생했거나 업데이트 시 유지 보수를 위해 존재하는 class
 * 
 */
public class HTMLContent {
    public List<DomElement> regionFilteredList;
    public HtmlPage pageHTML = null;
    public URL urlAddress = null;
    String sourceURL;
    String targetContents;

	public HTMLContent(String url){
		
	    WebClient webClient = new WebClient();
	    JavaScriptEngine engine = new JavaScriptEngine(webClient);
	    String pageXML = "";
	    
		try {
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getCookieManager().setCookiesEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			webClient.setJavaScriptEngine(engine);

			pageHTML = webClient.getPage(url);
			urlAddress = new URL(url);
			
			pageXML = pageHTML.asXml();
			StringWebResponse response = new StringWebResponse(pageXML, urlAddress);
			pageHTML = HTMLParser.parseXHtml(response, webClient.getCurrentWindow());
			
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
