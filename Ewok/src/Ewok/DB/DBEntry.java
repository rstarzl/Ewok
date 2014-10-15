package Ewok.DB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

// TODO : DB ������ ���� �߻�Ŭ������ ��ȭ���Ѿ���.��,,,,,,,���� �������̽�......... 
public class DBEntry extends HashMap<String, DBEntry>{
	private String parentKey;
	private String key;
	private String data;

	/**
	 * ���� �����ڸ�, ��Ʈ ��������
	 */
	public DBEntry(){
		super();
		parentKey = null;
		key = "root";
		data = null;
	}
	
	/**
	 * �Ϲ� ���� ������ 1
	 * @param key
	 * @param entry
	 */
	public DBEntry(String key, String data){
		super();
		parentKey = null;
		this.key = key;
		this.data = data;
	}
	
	/**
	 * �Ϲ� ���� ������ 2
	 * @param key
	 * @param entry
	 */
	public DBEntry(String parentKey, String key, String data){
		super();
		this.parentKey = parentKey;
		this.key = key;
		this.data = data;
	}
	
	public String getKey(){
		return	this.key;
	}
	
	public String getParentKey(){
		return	this.parentKey;
	}
	
	/**
	 * ���� ��忡 ���� Data�� ������. 
	 * @return
	 */
	public String	getData(){
		return	this.data;
	}
	
	class SubData extends Vector<String>{
		public void addData(Vector<String> subData){
			for (String data : subData){
				 super.add(data);
			}
		}
	}
	
	/**
	 * ���� ��� ������ �߿��� ���� Value �� String�� ��� ����.
	 * TODO : Test �ʿ�, ��Ŀ�ú�� ����.
	 * @return
	 */
	public SubData getSubData(){
		SubData ret = new SubData();
		SubData temp = new SubData();

		for (String key : this.keySet()){
			if (this.get(key).size() != 0){
//				ret.addData(this.get(key).getSubData());
				temp.addData(this.get(key).getSubData());
			} else {
				ret.add(this.get(key).data);
			}
		}
		
		ret.addData(temp);
		
		return	ret;
	}
	
	/**
	 * �̹� �����ϴ� Ű�� ������ ���� �� ���� �� �߰�.
	 * @param key
	 * @param entry
	 */
	public void addData(DBEntry	entry){
		entry.parentKey = this.key;
//		this.get(this.key).put(entry.key, entry);
		this.put(entry.key, entry);
	}
	
	/**
	 * �θ� Ű�� �Է��ϸ� ���� ��� ��带 ������ DBEntry ������. ������ null ����.
	 * TODO : �׽�Ʈ �ʿ�, ��Ŀ�ú���.
	 * @param key
	 * @return
	 */
	public DBEntry	findByParentKey(String key){
		//�ڱⲨ ���� ����.
		if (this.key.equals(key)){
			return	this;
		}
		
		for (String targetKey : this.keySet()){
			DBEntry findIt = this.get(targetKey).findByParentKey(key);
			if (findIt == null){
				continue;
			} else {
				return	findIt;
			}
		}
		
//		for (String targetKey : this.keySet()){
//			if (!this.get(targetKey).equals(key)){
//				return	this.get(targetKey).findByParentKey(key);
//			} else {
//				return	this.get(targetKey);
//			}
//		}

		return null;
	}
	
	

}
