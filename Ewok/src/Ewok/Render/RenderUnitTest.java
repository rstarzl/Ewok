package Ewok.Render;

import Ewok.Processor.QueueEntry;

public class RenderUnitTest {
	public static void main(String[] args){
		RenderDriver driver = new RenderDriver();
		
		QueueEntry entry = new QueueEntry("http://news.nate.com/view/20141012n15039?mid=n0501");
		
		try {
			driver.render(entry);
		} catch (RenderTerminatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(1);
		
		QueueEntry entry1 = new QueueEntry("http://news.nate.com/view/20141002n24969?mid=n0201");
		System.gc();
		try {
			driver.render(entry1);
		} catch (RenderTerminatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(2);
	}
}
