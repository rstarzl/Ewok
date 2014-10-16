import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
			Rollback	rollback = new Rollback(args[0]);
			//DB rollback Code.
			rollback.rollbackEachQ();
		} else {
			Rollback	rollback = new Rollback("seed");
//			Rollback	rollback = new Rollback("SnapShot");
			rollback.rollbackEachQ();
		}
		
		cd.run();
	}
}
