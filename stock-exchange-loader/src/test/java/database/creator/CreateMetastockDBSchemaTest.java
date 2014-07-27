package database.creator;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class CreateMetastockDBSchemaTest {
	
	private static final String PATH = "D:\\Workspace\\stock-exchange\\stock-exchange-loader\\src\\test\\resources\\";
	private static final String LIST_OF_TABLES = "show tables;";

	@Test
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
		
		removeFiles_CleanUp(PATH + "CreateMetastockDBSchemaTest\\", "notExistingTable.h2.db");
		removeFiles_CleanUp(PATH + "CreateMetastockDBSchemaTest\\", "notExistingTable.trace.db");
	}
	
	@Test 
	public void CannotCreateTableException() throws ClassNotFoundException, SQLException{
		
		String expectedError = "Could not create MetastockDB";

		Class.forName("org.h2.Driver");  
		Connection connection = DriverManager.getConnection("jdbc:h2:" + PATH + "CreateMetastockDBSchemaTest\\exceptionsTesting", "sa", "");
		
		connection.close();

		try {
			CreateMetastockDBSchema metastockDBSchema = new CreateMetastockDBSchema(connection);
			metastockDBSchema.createMetastockDBIfNotExist();
			fail("No excetion when expected");
		} catch (createMetastockDBException e) {
			assertEquals(expectedError, e.getMessage());
		}finally{
			removeFiles_CleanUp(PATH + "CreateMetastockDBSchemaTest\\", "exceptionsTesting.h2.db");
			removeFiles_CleanUp(PATH + "CreateMetastockDBSchemaTest\\", "exceptionsTesting.trace.db");
		}				
	}
		
	private void removeFiles_CleanUp(String directory, String files){
		Path path = FileSystems.getDefault().getPath(directory, files);
		
		try {
			Files.delete(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
