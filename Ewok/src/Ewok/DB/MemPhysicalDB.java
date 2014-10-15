package Ewok.DB;

import java.util.ArrayList;

import Ewok.Processor.QueueEntry;

/**
 * 
 * @author JS
 *
 */
public class MemPhysicalDB implements PhysicalDB{
	private DBEntry	dbPoint;
	
	public MemPhysicalDB(String rootName){
		super();
		dbPoint = new DBEntry(rootName);
	}
	
	public DBEntry	getDBPoint(){
		return	this.dbPoint;
	}
	

	/**
	 * 키이름이 있어야 함. 같은 레벨에서의 키이름은 유일해야함.
	 * 만일 키 이름이 없을 경우 add 할 수 없음.
	 */
	public boolean add(DBEntry content) {
		DBEntry	parentEntry = this.dbPoint.findByParentKey(content.getParentKey());
		if (parentEntry != null){
			parentEntry.addData(content);
			return true;
		}

		return false;
	}

	/**
	 * 키이름이 있어야 함. 같은 레벨에서의 키이름은 유일해야함.
	 * 만일 키 이름이 없을 경우 add 할 수 없음.
	 */
	public boolean delete(DBEntry content) {
		DBEntry	parentEntry = this.dbPoint.findByParentKey(content.getParentKey());
		
		if (parentEntry != null){
			parentEntry.deleteData(content);
			return true;
		}
		
		return false;
	}

	
//	public DBEntry query(String key) {
//		return	this.dbPoint.findEnrtyByKey(key);
//	}

	@Override
	public boolean add(QueueEntry content) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean delete(QueueEntry content) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ArrayList<QueueEntry> query(String key) {
		// TODO Auto-generated method stub
		return new ArrayList<QueueEntry>();
	}
}
