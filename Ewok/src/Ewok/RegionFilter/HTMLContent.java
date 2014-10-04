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
@ Project : Ewok
@ File Name : HTMLContent.java
@ Date : 2014-10-03
@ Author : Kiheung Park
*/
public class HTMLContent {
    public List<DomElement> regionFilteredList;
    public HtmlPage pageHTML = null;
    public URL urlAddress = null;
    String sourceURL;
    public String targetContents;
    public int depth;

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
			targetContents = urlAddress.toString();// for render method?
			
			pageXML = pageHTML.asXml();
			
			// DEBUG
			FileWriter fw = new FileWriter("test.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(pageXML);
			bw.close();
			fw.close();
//			System.out.println(pageXML);
			
			StringWebResponse response = new StringWebResponse(pageXML, urlAddress);
			pageHTML = HTMLParser.parseXHtml(response, webClient.getCurrentWindow());
			
			FileWriter fw2 = new FileWriter("test2.txt");
			BufferedWriter bw2 = new BufferedWriter(fw);
			bw.write(pageHTML.toString());
			bw.close();
			fw.close();
			
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
