package connection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class ExecuteSQLStatmentTest{
	
	final String PATH = "D:\\workspace\\stock-exchange\\stock-exchange-dao\\src\\test\\resources\\settings\\";

	@Test
	public void executedProperly(){
		
		Connection connection = null;
		final String STATEMENT_TO_EXECUTE = "show tables;";
		
		try{
			Class.forName("org.h2.Driver");  
			connection = DriverManager.getConnection("jdbc:h2:" + PATH + "TestDatabase", "sa", "");			
			ExecuteSQLStatment.execute(connection, STATEMENT_TO_EXECUTE);
				
			assertTrue(connection.isValid(0));
			assertTrue(connection.getMetaData().getURL().equals("jdbc:h2:" + PATH + "TestDatabase"));
		}catch(Exception ex){
			fail("Got exception when not expected");
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	@Test
	public void executedWithException(){
		
		Connection connection = null;
		final String STATEMENT_TO_EXECUTE = "show tables;";
		
		try{
			Class.forName("org.h2.Driver");  
			connection = DriverManager.getConnection("jdbc:h2:" + PATH + "TestDatabase", "sa", "");
			
			connection.close();
			ExecuteSQLStatment.execute(connection, STATEMENT_TO_EXECUTE);
			
			assertFalse(connection.isValid(0));
		}catch(Exception ex){
			assertTrue(ex.getMessage().equals("could not execute statment"));
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
}
