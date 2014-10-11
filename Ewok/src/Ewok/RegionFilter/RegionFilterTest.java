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

	// Daum News
//	HTMLContent test = new HTMLContent("http://media.daum.net/society/all/#page=1&type=tit_cont"); //��ȸ
//	HTMLContent test = new HTMLContent("http://media.daum.net/politics/all/#page=1&type=tit_cont"); //��ġ
//	HTMLContent test = new HTMLContent("http://media.daum.net/economic/all/#page=1&type=tit_cont"); //����
//	HTMLContent test = new HTMLContent("http://media.daum.net/foreign/all/#page=1&type=tit_cont"); //����
//	HTMLContent test = new HTMLContent("http://media.daum.net/culture/all/#page=1&type=tit_cont"); //��ȭ/��Ȱ
//	HTMLContent test = new HTMLContent("http://media.daum.net/digital/all/#page=1&type=tit_cont"); //Tech
//	HTMLContent test = new HTMLContent("http://media.daum.net/editorial/all/#page=1&type=tit_cont"); //Į��
//	HTMLContent test = new HTMLContent("http://media.daum.net/society/all/#page=1&type=tit_cont#page=2&type=tit_cont"); // page number click
//	HTMLContent test = new HTMLContent("http://media.daum.net/society/all/#page=1&type=tit_cont#page=2&type=tit_cont?regdate=20141004#page=1&type="); // page by date
	
	// Nate News
//	HTMLContent test = new HTMLContent("http://news.nate.com/recent?mid=n0201"); // ��ġ
	HTMLContent test = new HTMLContent("http://news.nate.com/recent?cate=pol&mid=n0201&type=c&date=20141002"); // �ٸ� ��¥

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
	

	@org.junit.Test
	public void test3() {
		DaumRegionFilter daumRF = new DaumRegionFilter();
		ArrayList<URLInfo> testArray = daumRF.filter("http://media.daum.net/society/all/#page=11&type=tit_cont");
//		ArrayList<URLInfo> testArray = daumRF.filter("http://media.daum.net/society/all/#page=1&type=tit_cont");
		for (int i = 0; i < testArray.size(); i++) {
			if (testArray != null) {
				System.out.println(testArray.get(i).getUrl() + "\t" +testArray.get(i).getUrlType());
			} else {
				fail("filter1 method fail!!!");
			}
		}
	}


/*	
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
*/



}
