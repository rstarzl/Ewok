package Ewok.RegionFilter;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.xerces.parsers.XMLParser;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ImmediateRefreshHandler;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HTMLParser;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptEngine;
import com.gargoylesoftware.htmlunit.xml.XmlPage;

/*
@ Project : Ewok
@ File Name : HTMLContent.java
@ Date : 2014-10-03
@ Author : Kiheung Park
*/
public class HTMLContent {
    public List<DomElement> regionFilteredList;
    public HtmlPage pageHTML = null;
    public XmlPage pageXml = null;
    public Page page = null;
    public URL urlAddress = null;
    String sourceURL;
    public String targetContents;
    public int depth;

	public HTMLContent(String url){
		try {
			java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
		    java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
			
			urlAddress = new URL(url);
			WebRequest webRequest = new WebRequest(urlAddress);
//			webRequest.setCharset("utf-8");
			WebClient webClient;
		    if(url.contains("nate")){
				webClient = new WebClient(BrowserVersion.CHROME);		    	
		    }else{
			    webClient = new WebClient();		    	
		    }
		    JavaScriptEngine engine = new JavaScriptEngine(webClient);
//		    webClient.waitForBackgroundJavaScript(50000);
		    String xmlString = "";
		    
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getCookieManager().setCookiesEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			webClient.setJavaScriptEngine(engine);

			pageHTML = webClient.getPage(webRequest);
			page = webClient.getPage(webRequest);
			targetContents = urlAddress.toString();// for render method?
			
			xmlString = pageHTML.asXml();

			StringWebResponse response = new StringWebResponse(xmlString, urlAddress);
			pageHTML = HTMLParser.parseXHtml(response, webClient.getCurrentWindow());
			
//			webClient.closeAllWindows();

			
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
