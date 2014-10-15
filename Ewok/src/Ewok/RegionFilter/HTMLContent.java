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
 * HTMLUNIT�� URL�� �־��� �� ������ �ڷᱸ���� ��� �ڷᱸ�� 
 * HTMLUNIT�� ���װ� �߻��߰ų� ������Ʈ �� ���� ������ ���� �����ϴ� class
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
