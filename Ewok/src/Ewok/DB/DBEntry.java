package Ewok.DB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

// TODO : DB 종류에 따라 추상클래스로 분화시켜야함.아,,,,,,,슈벌 인터페이스......... 
public class DBEntry extends HashMap<String, DBEntry>{
	private String parentKey;
	private String key;
	private String data;

	/**
	 * 최초 생성자만, 루트 생성전용
	 */
	public DBEntry(){
		super();
		parentKey = null;
		key = "root";
		data = null;
	}
	
	/**
	 * 일반 목적 생성자 1
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
	 * 일반 목적 생성자 2
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
	 * 현재 노드에 속한 Data만 가져옴. 
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
	 * 하위 모든 데이터 중에서 최종 Value 인 String을 묶어서 전달.
	 * TODO : Test 필요, 리커시브로 구현.
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
	 * 이미 존재하는 키를 넣으면 기존 값 삭제 후 추가.
	 * @param key
	 * @param entry
	 */
	public void addData(DBEntry	entry){
		entry.parentKey = this.key;
//		this.get(this.key).put(entry.key, entry);
		this.put(entry.key, entry);
	}
	
	/**
	 * 부모 키를 입력하면 하위 모든 노드를 뒤져서 DBEntry 가져옴. 없으면 null 리턴.
	 * TODO : 테스트 필요, 리커시브임.
	 * @param key
	 * @return
	 */
	public DBEntry	findByParentKey(String key){
		//자기꺼 부터 뒤짐.
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
