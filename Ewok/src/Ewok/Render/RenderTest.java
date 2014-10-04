package Ewok.Render;

import Ewok.RegionFilter.HTMLContent;

public class RenderTest {
/*	Naver News */
//	String targetedURL = "http://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=102&oid=003&aid=0006112677";
//	String targetedURL = "http://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=101&oid=020&aid=0002660306";

/* Daum News */
//	String targetedURL = "http://media.daum.net/society/all/newsview?newsid=20141004112006560";
//	String targetedURL = "http://media.daum.net/economic/all/newsview?newsid=20141004120703352";
//	String targetedURL = "http://media.daum.net/editorial/all/newsview?newsid=20141004033119842";
	
/* Nate News */
//	String targetedURL = "http://news.nate.com/view/20141002n24969?mid=n0201";
	String targetedURL = "http://news.nate.com/view/20141005n01131?mid=n0401";
	
/*	
	@org.junit.Test
	public void test1() {
		NaverNewsRender naverNews = new NaverNewsRender();
		Article result = naverNews.render(targetedURL);
		System.out.println(result);
	}
*/	
	
/*	
	@org.junit.Test
	public void test2() {
		DaumNewsRender daumNews = new DaumNewsRender();
		Article result = daumNews.render(targetedURL);
		System.out.println(result);
	}
*/
	
	@org.junit.Test
	public void test3() {
		NateNewsRender nateNews = new NateNewsRender();
		Article result = nateNews.render(targetedURL);
		System.out.println(result);
	}

}
