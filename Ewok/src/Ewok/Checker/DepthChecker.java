package Ewok.Checker;
import Ewok.Processor.QueueEntry;
//
//
//
//  @ Project : Ewok
//  @ File Name : DepthChecker.java
//  @ Date : 2014-09-29
//  @ Author : Member
//
//



public class DepthChecker extends Checker {

//	private boolean isDepthLimit(HTMLContent URL) {
//		if(URL.Depth == 0){
//			return 
//		}
//		
//		Depth.Depth = Depth.Depth - 1;  //Input depth is lowered by one.
//	}

	@Override
	public boolean check(QueueEntry entry) {
		// First ......Decrease count.
		entry.decreaseDepthCount();
		// and,,,,check depth count. if -1, go to infinity.
		if (entry.getDepth() == 0){
			return false;	// drop.
		}
		
		return true;
	}
}
