package fxdigital.db;

import static org.junit.Assert.*;

import org.junit.Test;

public class DbManagerTest {

	public static DbManager dbManager=new DbManager();
	@Test
	public void testGetLocalCenter() {
		dbManager.getLocalCenter();
	}

	@Test
	public void testGetSyncServer() {
		String id="55@001";
		dbManager.getSyncServer(id);
	}

	@Test
	public void testGetMQServerIP() {
		dbManager.getMQServerIP("55@001");
	}

	@Test
	public void testGetLocalMqInfo() {
		dbManager.getLocalMqInfo();
	}

	@Test
	public void testInsertLocalMqInfo() {
		dbManager.insertLocalMqInfo("111", "12.00", 2020);
		
	}

	@Test
	public void testIsExist() {
		dbManager.isExist("55@001");
		assertEquals(true, dbManager.isExist("55@001"));
	}

}
