package connection;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hamcrest.Condition.Step;
import org.junit.Test;

public class ExecuteSQLStatmentTest{
	
	final String PATH = "D:\\workspace\\stock-exchange\\stock-exchange-dao\\src\\test\\resources\\settings\\";

	@Test
	public void executedProperly(){
		
		try{
			Class.forName("org.h2.Driver");  
			Connection connection = DriverManager.getConnection("jdbc:h2:" + PATH + "TestDatabase", "sa", "");
			
			connection.close();
				
			assertTrue(connection.isValid(0));
			assertTrue(connection.getMetaData().getURL().equals("jdbc:h2:" + PATH + "TestDatabase"));
		}catch(Exception ex){
			fail("Got exception when not expected");
		}
		
	}
	
	@Test
	public void executedWithException(){
		try{
			Class.forName("org.h2.Driver");  
			Connection connection = DriverManager.getConnection("jdbc:h2:" + PATH + "TestDatabase", "sa", "");
			
			connection.close();
			
			PreparedStatement statement = connection.prepareStatement("show tables;");
			statement.execute();
			
			assertFalse(connection.isValid(0));
		}catch(Exception ex){
			assertTrue(ex.getMessage().equals("The object is already closed [90007-174]"));
		}
	}
}
