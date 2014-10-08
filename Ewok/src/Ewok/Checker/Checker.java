package Ewok.Checker;
import Ewok.Processor.QueueEntry;
//
//
//
//  @ Project : Ewok
//  @ File Name : Checker.java
//  @ Date : 2014-09-29
//  @ Author : Member
//
//
import Ewok.RegionFilter.*;

/**
 * 
 * @author JS
 *
 */

public abstract class Checker {
	private Object condition;
	protected void checkAndDrop() {
		;
	}
	protected void setCondition() {
		;
	}
	
	public	abstract boolean check(QueueEntry entry);
}
