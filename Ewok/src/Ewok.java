import java.io.IOException;

import Ewok.Processor.ClassifierQueueProcessor;
import Ewok.Processor.QueueProcessor;
import Ewok.Processor.RenderingQueueProcessor;
import Ewok.Processor.TargetQueueProcessor;
import Ewok.RegionFilter.HTMLContent;
import Ewok.*;


public class Ewok {
	public static void main(String[] args){
		CrawlerDriver cd = new CrawlerDriver();
		cd.run();
	}
}
