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
//	HTMLContent test = new HTMLContent("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=100"); // ��ġ 
//	HTMLContent test = new HTMLContent("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=101"); // ���� 
//	HTMLContent test = new HTMLContent("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=102"); // ��ȸ 
//	HTMLContent test = new HTMLContent("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=103"); // ��Ȱ/��ȭ 
//	HTMLContent test = new HTMLContent("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=104"); // ���� 
//	HTMLContent test = new HTMLContent("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=100#&date=2014-10-02 00:00:00&page=1"); // �ٸ� ��¥
//	HTMLContent test = new HTMLContent("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=100#&date=2014-10-02 00:00:00&page=2"); // �ٸ� ������

	

/*
	@org.junit.Test
	public void test1() {
		NaverRegionFilter naverRF = new NaverRegionFilter();
		ArrayList<URLInfo> testArray = naverRF.filter("http://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=100");
		for (int i = 0; i < testArray.size(); i++) {
			if (testArray != null) {
				System.out.println(testArray.get(i).getUrl() + "\t" + testArray.get(i).getUrlType());
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
	
	// Daum News
//	String test = "http://media.daum.net/culture/all/#page=1&type=tit_cont"; // FIXED
//	String test = "http://media.daum.net/culture/all/?regdate=20141011#page=1&type="; // 
/*
	@org.junit.Test
	public void test3() {
		DaumRegionFilter daumRF = new DaumRegionFilter();
		ArrayList<URLInfo> testArray = daumRF.filter(test);
//		ArrayList<URLInfo> testArray = daumRF.filter("http://media.daum.net/society/all/#page=1&type=tit_cont");
		for (int i = 0; i < testArray.size(); i++) {
			if (testArray != null) {
				System.out.println(testArray.get(i).getUrl() + "\t" +testArray.get(i).getUrlType());
			} else {
				fail("filter1 method fail!!!");
			}
		}
	}
*/
	
	// Nate News
//	String test = "http://news.nate.com/recent?mid=n0201"; // PASS
//	String test = "http://news.nate.com/recent?cate=pol&mid=n0201&type=c&date=20141012&page=2"; // PASS
//	String test = "http://news.nate.com/recent?cate=pol&mid=n0201&type=c&date=20141011"; // PASS
//	String test = "http://news.nate.com/recent?cate=pol&mid=n0201&type=c&date=20141011&page=5"; // PASS
//	String test = "http://news.nate.com/recent?cate=pol&mid=n0201&type=c&date=20141006"; // PASS
	String test = "http://news.nate.com/recent?cate=pol&mid=n0201&type=c&date=20141008"; // 
	
	@org.junit.Test
	public void test4() {
		NateRegionFilter nateRF = new NateRegionFilter();
		ArrayList<URLInfo> testArray = nateRF.filter(test);
		for (int i = 0; i < testArray.size(); i++) {
			if (testArray != null) {
				System.out.println(testArray.get(i).getUrl() + "\t" + testArray.get(i).getUrlType());
			} else {
				fail("filter1 method fail!!!");
			}
		}
	}




}
