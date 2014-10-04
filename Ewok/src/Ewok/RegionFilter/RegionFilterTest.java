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
import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HTMLParser;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptEngine;
import com.gargoylesoftware.htmlunit.xml.XmlPage;

/*
@ Project : Ewok
@ File Name : RegionFilterTest.java
@ Date : 2014-10-03
@ Author : Kiheung Park
*/
public class RegionFilterTest {

/*	Naver News */
//	HTMLContent test = new HTMLContent("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=100"); // 정치 
//	HTMLContent test = new HTMLContent("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=101"); // 경제 
//	HTMLContent test = new HTMLContent("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=102"); // 사회 
//	HTMLContent test = new HTMLContent("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=103"); // 생활/문화 
//	HTMLContent test = new HTMLContent("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=104"); // 세계 
//	HTMLContent test = new HTMLContent("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=100#&date=2014-10-02 00:00:00&page=1"); // 다른 날짜
//	HTMLContent test = new HTMLContent("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=100#&date=2014-10-02 00:00:00&page=2"); // 다른 페이지

	// Daum News
//	HTMLContent test = new HTMLContent("http://media.daum.net/society/all/#page=1&type=tit_cont"); //사회
//	HTMLContent test = new HTMLContent("http://media.daum.net/politics/all/#page=1&type=tit_cont"); //정치
//	HTMLContent test = new HTMLContent("http://media.daum.net/economic/all/#page=1&type=tit_cont"); //경제
//	HTMLContent test = new HTMLContent("http://media.daum.net/foreign/all/#page=1&type=tit_cont"); //국제
//	HTMLContent test = new HTMLContent("http://media.daum.net/culture/all/#page=1&type=tit_cont"); //문화/생활
//	HTMLContent test = new HTMLContent("http://media.daum.net/digital/all/#page=1&type=tit_cont"); //Tech
//	HTMLContent test = new HTMLContent("http://media.daum.net/editorial/all/#page=1&type=tit_cont"); //칼럼
//	HTMLContent test = new HTMLContent("http://media.daum.net/society/all/#page=1&type=tit_cont#page=2&type=tit_cont"); // page number click
//	HTMLContent test = new HTMLContent("http://media.daum.net/society/all/#page=1&type=tit_cont#page=2&type=tit_cont?regdate=20141004#page=1&type="); // page by date
	
	// Nate News
//	HTMLContent test = new HTMLContent("http://news.nate.com/recent?mid=n0201"); // 정치
	HTMLContent test = new HTMLContent("http://news.nate.com/recent?cate=pol&mid=n0201&type=c&date=20141002"); // 다른 날짜

/*
	@org.junit.Test
	public void test1() {
		NaverRegionFilter naverRF = new NaverRegionFilter();
		ArrayList<String> testArray = naverRF.filter1(test);
		for (int i = 0; i < testArray.size(); i++) {
			if (testArray != null) {
				System.out.println(testArray.get(i));
			} else {
				fail("filter1 method fail!!!");
			}
		}
	}
*/

/*
	@org.junit.Test
	public void test2() {
		NaverRegionFilter naverRF = new NaverRegionFilter();
		ArrayList<String> testArray = naverRF.filter2(test);
		for (int i = 0; i < testArray.size(); i++) {
			if (testArray != null) {
				System.out.println(testArray.get(i));
			} else {
				fail("filter2 method fail!!!");
			}
		}
		//System.out.println("URL count: " + testArray.size()+1);
	}
*/
	
/*
	@org.junit.Test
	public void test3() {
		DaumRegionFilter daumRF = new DaumRegionFilter();
		ArrayList<String> testArray = daumRF.filter1(test);
		for (int i = 0; i < testArray.size(); i++) {
			if (testArray != null) {
				System.out.println(testArray.get(i));
			} else {
				fail("filter1 method fail!!!");
			}
		}
	}
*/

	
	@org.junit.Test
	public void test4() {
		NateRegionFilter nateRF = new NateRegionFilter();
		ArrayList<String> testArray = nateRF.filter1(test);
		for (int i = 0; i < testArray.size(); i++) {
			if (testArray != null) {
				System.out.println(testArray.get(i));
			} else {
				fail("filter1 method fail!!!");
			}
		}
	}



}
