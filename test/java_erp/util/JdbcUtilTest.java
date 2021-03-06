package java_erp.util;

import java.sql.Connection;

import org.junit.After;
import org.junit.Test;

public class JdbcUtilTest {

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void testGetConnection() {
		System.out.println("testGetConnection()");
		Connection con = JdbcUtil.getConnection();
		org.junit.Assert.assertNotNull(con);
		System.out.println("con >> " + con);
	}

}
