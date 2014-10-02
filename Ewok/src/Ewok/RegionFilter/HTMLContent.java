package Ewok.RegionFilter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptEngine;
import com.gargoylesoftware.htmlunit.xml.XmlPage;


/*
 * HTMLUNIT에 URL을 넣었을 때 나오는 자료구조를 담는 자료구조 
 * HTMLUNIT에 버그가 발생했거나 업데이트 시 유지 보수를 위해 존재하는 class
 * 
 */
public class HTMLContent {
	WebClient webClient = new WebClient();
	JavaScriptEngine engine = new JavaScriptEngine(webClient);
	public HtmlPage startPage;
	public XmlPage startPageXML;
	List<DomElement> regionFilteredList;
	String sourceURL;
	String targetContents;		
	//HtmlPage contentPage = webClient.getPage(sourceURL);

	public HTMLContent(String URL){
		try {
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getCookieManager().setCookiesEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			webClient.setJavaScriptEngine(engine);
			startPage = webClient.getPage(URL);
			
//			startPageXML = startPage.asXml();
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
