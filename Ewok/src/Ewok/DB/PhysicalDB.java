package Ewok.DB;

import java.util.ArrayList;

import Ewok.Processor.QueueEntry;

/**
 * 
 * @author JS
 *
 */
public interface PhysicalDB {
	public boolean add(QueueEntry content);
	public boolean delete(QueueEntry content);
	public ArrayList<QueueEntry> query(String key);
}
