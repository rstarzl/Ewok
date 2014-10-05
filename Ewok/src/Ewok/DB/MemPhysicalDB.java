package Ewok.DB;

import java.util.HashMap;

public class MemPhysicalDB implements PhysicalDB{
	private DBEntry	dbPoint;
	
	public MemPhysicalDB(String rootName){
		super();
		dbPoint = new DBEntry(rootName);
	}
	
	public DBEntry	getDBPoint(){
		return	this.dbPoint;
	}
	

	@Override
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
	@Override
	public boolean delete(DBEntry content) {
		DBEntry	parentEntry = this.dbPoint.findByParentKey(content.getParentKey());
		
		if (parentEntry != null){
			parentEntry.deleteData(content);
			return true;
		}
		
		return false;
	}

	
	@Override
	public DBEntry query(String key) {
		return	this.dbPoint.findEnrtyByKey(key);
	}
}
