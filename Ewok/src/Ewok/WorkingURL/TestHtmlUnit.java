package	Ewok.WorkingURL;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


public class TestHtmlUnit {
	public static void main(String[] args) throws Exception {
        turnOffSystemErr();
        String seed = "http://news.naver.com/";
        ArrayList<String> URLList = new ArrayList<String>();
        URLList = source(seed);
       
    }

    private static void turnOffSystemErr() {
        System.setErr(new PrintStream(new OutputStream() {
            public void write(int b) {
                // do nothing
            }
        }));
    }
    
    private static ArrayList<String> source(String URLSeed) throws Exception{
    	ArrayList<String> URLList = new ArrayList<String>();
    	
    	WebClient webClient = new WebClient();
        HtmlPage page = webClient.getPage(URLSeed);
        List<DomElement> list = page.getElementsByTagName("a");
        for(DomElement e : list){
        	String candidateURL = e.getAttribute("href");    	
        	if(!candidateURL.contains("http")){
        		candidateURL=URLSeed+candidateURL;
        	}
        	
        	URLList.add(candidateURL);
        }
        
        webClient.closeAllWindows();
     
    	return URLList;
    }
    
    private static String render(String URL) throws Exception{
    	WebClient webClient = new WebClient();
        HtmlPage page = webClient.getPage(URL);
        HtmlDivision div = (HtmlDivision) page.getElementById("articleBody");
        String result = div.asText();
 
    	return result;
    }

}
