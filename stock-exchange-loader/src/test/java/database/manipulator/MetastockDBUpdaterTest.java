package database.manipulator;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.h2.command.dml.Select;
import org.junit.Test;

import utils.MockForCommonsTest;

import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import dao.StockDataSelect;
import data.DataFileReader;
import data.collector.StockTickerHistory;
import database.creator.CreateMetastockDBSchema;

public class MetastockDBUpdaterTest {
	
	private static final String PATH = "D:\\Workspace\\stock-exchange\\stock-exchange-loader\\src\\test\\resources\\MetastockDBUpdaterTest\\MetastockDBUpdaterTest\\";
	MockForCommonsTest mock = new MockForCommonsTest();
	
	@Test
	public void refresh() throws ClassNotFoundException, SQLException, ParseException{
		
		StockTickerHistory expectedTickerCollection = mock.readStockTickerHistory("MetastockDBUpdaterTest/MetastockDBUpdaterTest/LENA.mst");
		
		final String propertiesPath = getResourcePath("MetastockDBUpdaterTest/MetastockDBUpdaterTest/StockExchange.properties");
		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance(propertiesPath);
		MetastockDBUpdater updater = new MetastockDBUpdater(propertiesInstance);
		
		Class.forName("org.h2.Driver");  
		Connection connection = DriverManager.getConnection("jdbc:h2:" + PATH + "MetastockDBUpdaterTest_refresh", "sa", "");
		
		CreateMetastockDBSchema creatSchema = new CreateMetastockDBSchema(connection);
		creatSchema.createMetastockDBIfNotExist();
		
		try {
			updater.refresh();
			
			StockDataSelect select  =new StockDataSelect(connection);
			List<String> tickerNameList= select.getAllStockTickerNames();
			
			assertEquals("Unexpected data found",1, tickerNameList.size());
			assertEquals("Wrong ticker found", "LENA", tickerNameList.get(0));
			
			StockTickerHistory currentResults = select.getAllDataForStockTicker("LENA");
			
			assertTrue(expectedTickerCollection.equals(currentResults));
			
		} catch (ParseException e) {
			fail("Exception when not expected");
		}finally{
			connection.close();
			
			removeFiles_CleanUp(PATH, "MetastockDBUpdaterTest_refresh.h2.db");
		}
	}
	
	private String getResourcePath(final String resourceName) {
		URL resource = this.getClass().getClassLoader().getResource(resourceName);
		if (resource == null) {
			throw new IllegalArgumentException("Given resource name was not found");
		}
		
		return resource.getPath();
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
