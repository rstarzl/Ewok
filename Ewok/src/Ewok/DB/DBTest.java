package Ewok.DB;

import static org.junit.Assert.*;

import org.junit.Test;

import Ewok.DB.DBEntry.SubData;

public class DBTest {

//	@Test
//	// 메모리 디비, 1뎁스 기본 테스트.
//	public void test() {
//		MemPhysicalDB memPhysicalDB = new MemPhysicalDB();
//		
//		//쓰기
//		for (int testNum = 0; testNum < 100; testNum++){
//			memPhysicalDB.add(new DBEntry("root", String.valueOf(testNum), String.valueOf(testNum)));
//		}
//		
//		SubData sunData = memPhysicalDB.getDBPoint().getSubData();
//		
//		// 읽기
//		for (int testNum = 0; testNum < 100; testNum++){
//			System.out.println(sunData.get(testNum));
//		}
//	}
	
	
	@Test
	// 메모리 디비, 멀티 뎁스 기본 테스트.
	public void test2() {
		MemPhysicalDB memPhysicalDB = new MemPhysicalDB();
		
		//쓰기
		assertFalse("fail", !memPhysicalDB.add(new DBEntry("root", "1", "1")));
		assertFalse("fail", !memPhysicalDB.add(new DBEntry("1", "1_1", "1_1")));
		assertFalse("fail", !memPhysicalDB.add(new DBEntry("1", "1_2", "1_2")));
		assertFalse("fail", !memPhysicalDB.add(new DBEntry("root", "2", "2")));
		assertFalse("fail", !memPhysicalDB.add(new DBEntry("2", "2_1", "2_1")));
		assertFalse("fail", !memPhysicalDB.add(new DBEntry("1_1", "1_1_1", "1_1_1")));
		assertFalse("fail", !memPhysicalDB.add(new DBEntry("1_2", "1_2_1", "1_2_1")));
		
		SubData sunData = memPhysicalDB.getDBPoint().getSubData();
		
		// 읽기
		for (String testData : sunData){
			System.out.println(testData);
		}
	}
}
