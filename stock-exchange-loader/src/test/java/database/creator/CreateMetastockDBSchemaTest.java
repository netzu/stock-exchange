package database.creator;

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
		Connection connection = DriverManager.getConnection("jdbc:h2:" + PATH + "CreateMetastockDBSchemaTest\\notExisting", "sa", "");
		
		CreateMetastockDBSchema metastockDBSchema = new CreateMetastockDBSchema(connection);
		metastockDBSchema.createMetastockDBIfNotExist();		

		PreparedStatement statement = connection.prepareStatement(LIST_OF_TABLES);
		ResultSet currentResults = statement.executeQuery();
		
		currentResults.next();
		
		assertEquals("Table do not exist", expectedTable, currentResults.getString(1));
		
		currentResults.close();
		connection.close();
		
		removeFiles_CleanUp(PATH + "CreateMetastockDBSchemaTest\\", "notExisting.h2.db");
		removeFiles_CleanUp(PATH + "CreateMetastockDBSchemaTest\\", "notExisting.trace.db");
	}
	
	@Test
	public void reateMetastockDBIfNotExist_TableExist(){
		
	}
	
	@Test
	public void reateMetastockDBIfNotExist_IndexNotExist(){
		
	}
	
	@Test
	public void reateMetastockDBIfNotExist_IndexExist(){
		
	}
	
	private void removeFiles_CleanUp(String directory, String files){
		Path path = FileSystems.getDefault().getPath(directory, files);
		
		try {
			Files.delete(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
