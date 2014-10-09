package Ewok.RegionFilter;

import java.util.ArrayList;

import Ewok.Processor.QueueEntry;

//  @ Project : Ewok
//  @ File Name : RegionFilter.java
//  @ Date : 2014-10-03
//  @ Author : Kiheung Park

public interface RegionFilter {
	public ArrayList<String> filter(String urlAddress);

//	public ArrayList<String> filter(QueueEntry entry); 
}
