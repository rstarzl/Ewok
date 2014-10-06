package Ewok;

import java.util.ArrayList;

import Ewok.Processor.QueueProcessor;

public class ProcessorList <TypeOfQP extends QueueProcessor> extends ArrayList<QueueProcessor> {
	public ProcessorList(){
		super();
	}
	
	public TypeOfQP	getAllocatableQP(){
		int minSize = Integer.MAX_VALUE;
		QueueProcessor	minSizeQP = null;
		
		// TODO : is it consider to sync ?
		for (QueueProcessor	qp : this){
			if (qp.getQSize() < minSize){
				minSize = qp.getQSize();
				minSizeQP = qp;
			}
		}
		
		// did it check a null-pointer?
		return	(TypeOfQP)minSizeQP;
	}
}
