package Ewok.Render;

import Ewok.Processor.QueueEntry;
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
//	String targetedURL = "http://news.nate.com/view/20141002n24969?mid=n0201"; // PASS
//	String targetedURL = "http://news.nate.com/view/20141005n01131?mid=n0401"; // PASS
//	String targetedURL = "http://news.nate.com/view/20141011n07657?mid=n0506"; // PASS
//	String targetedURL = "http://news.nate.com/view/20141011n01753?mid=n0506"; // PASS
//	String targetedURL = "http://news.nate.com/view/20141012n00713?mid=n0401"; // PASS
//	String targetedURL = "http://news.nate.com/view/20141012n00081?mid=n0301"; // PASS
//	String targetedURL = "http://news.nate.com/view/20141012n00196?mid=n0201"; // PASS
//	String targetedURL = "http://news.nate.com/view/20141011n08655?mid=n0601"; // PASS
//    String targetedURL = "http://news.nate.com/view/20141011n00242?mid=n0701"; // PASS
    String targetedURL = "http://news.nate.com/view/20141012n15039?mid=n0501"; //
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
		RenderDriver driver = new RenderDriver();
		
		QueueEntry entry = new QueueEntry("http://media.daum.net/editorial/column/newsview?newsid=20140207204409273");
		
		try {
			driver.render(entry);
		} catch (RenderTerminatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(1);
		
//		QueueEntry entry1 = new QueueEntry("http://news.nate.com/view/20141002n24969?mid=n0201");
//		
//		try {
//			driver.render(entry1);
//		} catch (RenderTerminatedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(2);
	}

}
