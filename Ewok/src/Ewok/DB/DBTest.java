package Ewok.DB;

import static org.junit.Assert.*;

import org.junit.Test;

import Ewok.DB.DBEntry.SubData;

public class DBTest {

//	@Test
//	// �޸� ���, 1���� �⺻ �׽�Ʈ.
//	public void test() {
//		MemPhysicalDB memPhysicalDB = new MemPhysicalDB();
//		
//		//����
//		for (int testNum = 0; testNum < 100; testNum++){
//			memPhysicalDB.add(new DBEntry("root", String.valueOf(testNum), String.valueOf(testNum)));
//		}
//		
//		SubData sunData = memPhysicalDB.getDBPoint().getSubData();
//		
//		// �б�
//		for (int testNum = 0; testNum < 100; testNum++){
//			System.out.println(sunData.get(testNum));
//		}
//	}
	
	
	@Test
	// �޸� ���, ��Ƽ ���� �⺻ �׽�Ʈ.
	public void test2() {
		MemPhysicalDB memPhysicalDB = new MemPhysicalDB();
		
		//����
		assertFalse("fail", !memPhysicalDB.add(new DBEntry("root", "1", "1")));
		assertFalse("fail", !memPhysicalDB.add(new DBEntry("1", "1_1", "1_1")));
		assertFalse("fail", !memPhysicalDB.add(new DBEntry("1", "1_2", "1_2")));
		assertFalse("fail", !memPhysicalDB.add(new DBEntry("root", "2", "2")));
		assertFalse("fail", !memPhysicalDB.add(new DBEntry("2", "2_1", "2_1")));
		assertFalse("fail", !memPhysicalDB.add(new DBEntry("1_1", "1_1_1", "1_1_1")));
		assertFalse("fail", !memPhysicalDB.add(new DBEntry("1_2", "1_2_1", "1_2_1")));
		
		SubData sunData = memPhysicalDB.getDBPoint().getSubData();
		
		// �б�
		for (String testData : sunData){
			System.out.println(testData);
		}
	}
}
