import java.io.IOException;

import Ewok.Processor.ClassifierQueueProcessor;
import Ewok.Processor.QueueProcessor;
import Ewok.Processor.RenderingQueueProcessor;
import Ewok.Processor.TargetQueueProcessor;
import Ewok.RegionFilter.HTMLContent;
import Ewok.*;


public class Ewok {
	public static void main(String[] args){
		GlobalContext.readConfigFile();
		
		CrawlerDriver cd = new CrawlerDriver();
		
		// Added for Q snap-shot
		if (args.length > 0){
			// TODO : DB rollback Code.
			// TODO : 각 큐마다 스냅샷 찍을것,.
		}
			
		
		cd.run();
	}
}
