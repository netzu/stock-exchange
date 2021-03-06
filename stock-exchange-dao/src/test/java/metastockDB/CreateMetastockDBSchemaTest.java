package metastockDB;


import connection.ExecuteSQLStatmentException;
import org.junit.Ignore;
import org.junit.Test;
import utils.TestUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
//TODO wyjebac stad tego sztywnego patha
public class CreateMetastockDBSchemaTest {
	
	private static final String PATH = "D:\\Workspace\\stock-exchange\\stock-exchange-dao\\src\\test\\resources\\metastockDB\\";
	private static final String LIST_OF_TABLES = "show tables;";

	@Test
	@Ignore
	public void createMetastockDBIfNotExist_TableNotExist() throws ClassNotFoundException, SQLException {
		
		String expectedTable = "DAILY_STOCK_INFO";
		
		Class.forName("org.h2.Driver");  
		Connection connection = DriverManager.getConnection("jdbc:h2:" + PATH + "CreateMetastockDBSchemaTest\\notExistingTable", "sa", "");
		
		CreateMetastockDBSchema metastockDBSchema = new CreateMetastockDBSchema(connection);
		metastockDBSchema.createMetastockDBIfNotExist();		

		PreparedStatement statement = connection.prepareStatement(LIST_OF_TABLES);
		ResultSet currentResults = statement.executeQuery();
		
		currentResults.next();
		
		int firstRowIndex = currentResults.getRow();
			
		assertEquals("Table do not exist", expectedTable, currentResults.getString(1));
		
		currentResults.last();
		int lastRowIndex = currentResults.getRow();
		
		assertEquals("To many tables, what the hell?!", firstRowIndex, lastRowIndex);
		
		currentResults.close();
		connection.close();
		
	
		TestUtils utils = new TestUtils();
		//utils.removeFilesCleanUp(PATH + "CreateMetastockDBSchemaTest\\", "notExistingTable.h2.db");
		//utils.removeFilesCleanUp(PATH + "CreateMetastockDBSchemaTest\\", "notExistingTable.trace.db");
	}
	
	@Test 
	public void CannotCreateTableException() throws ClassNotFoundException, SQLException{
		
		String expectedError = "could not execute statment";

		Class.forName("org.h2.Driver");  
		Connection connection = DriverManager.getConnection("jdbc:h2:" + PATH + "CreateMetastockDBSchemaTest\\exceptionsTesting", "sa", "");
		
		connection.close();

		try {
			CreateMetastockDBSchema metastockDBSchema = new CreateMetastockDBSchema(connection);
			metastockDBSchema.createMetastockDBIfNotExist();
			fail("No excetion when expected");
		} catch (ExecuteSQLStatmentException e) {
			assertEquals(expectedError, e.getMessage());
		}finally{
			TestUtils utils = new TestUtils();
			utils.removeFilesCleanUp(PATH + "CreateMetastockDBSchemaTest\\", "exceptionsTesting.h2.db");
			utils.removeFilesCleanUp(PATH + "CreateMetastockDBSchemaTest\\", "exceptionsTesting.trace.db");
		}				
	}
}
