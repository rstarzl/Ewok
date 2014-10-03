package Ewok.DB;

import java.util.HashMap;

public class MemPhysicalDB implements PhysicalDB{
	private DBEntry	dbPoint;
	
	public MemPhysicalDB(){
		super();
		dbPoint = new DBEntry();
	}
	
	public DBEntry	getDBPoint(){
		return	this.dbPoint;
	}
	

	@Override
	/**
	 * 저장 여부 리턴.
	 */
	public boolean add(DBEntry content) {
		DBEntry	parentEntry = this.dbPoint.findByParentKey(content.getParentKey());
		if (parentEntry != null){
			parentEntry.addData(content);
			return true;
		}

		return false;
	}

	@Override
	public boolean delete(DBEntry content) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DBEntry query(DBEntry content) {
		// TODO Auto-generated method stub
		return null;
	}

}
