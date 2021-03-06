package metastockDB;

import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import data.collector.StockTickerHistory;
import org.junit.Ignore;
import org.junit.Test;
import utils.TestUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static paths.ResourcesUtils.getResourcePath;

public class MetastockDBUpdaterTest {
	
	private static final String PATH = "D:\\workspace\\stock-exchange\\stock-exchange-dao\\src\\test\\resources\\metastockDB\\MetastockDBUpdaterTest\\MetastockDBUpdaterTest\\";
//	MockForCommonsTest mock = new MockForCommonsTest();
	
	@Test
    @Ignore("This is integration test")
	public void refresh() throws ClassNotFoundException, SQLException, ParseException{
		
//		StockTickerHistory expectedTickerCollection = mock.readStockTickerHistory("MetastockDBUpdaterTest/MetastockDBUpdaterTest/LENA.mst");
		
		final String propertiesPath = getResourcePath("MetastockDBUpdaterTest/MetastockDBUpdaterTest/StockExchange.properties");
		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance(propertiesPath);
		MetastockDBUpdater updater = new MetastockDBUpdater(propertiesInstance);
		String dbRootPath = getResourcePath("MetastockDBUpdaterTest/MetastockDBUpdaterTest");
		Class.forName("org.h2.Driver");  
		Connection connection = DriverManager.getConnection("jdbc:h2:" + dbRootPath + "MetastockDBUpdaterTest_refresh", "sa", "");
		
		CreateMetastockDBSchema creatSchema = new CreateMetastockDBSchema(connection);
		creatSchema.createMetastockDBIfNotExist();
		
		try {
			updater.refresh(connection);
			
			StockDataSelect select = new StockDataSelect(connection);
			List<String> tickerNameList= select.getAllStockTickerNames();
			
			assertEquals("Unexpected data found",1, tickerNameList.size());
			assertEquals("Wrong ticker found", "LENA", tickerNameList.get(0));
			
			StockTickerHistory currentResults = select.getAllDataForStockTicker("LENA");
			
//			assertTrue(expectedTickerCollection.equals(currentResults));
			
		} catch (ParseException e) {
			fail("Exception when not expected");
		}finally{
			connection.close();
            final TestUtils utils = new TestUtils();
			utils.removeFilesCleanUp(PATH, "MetastockDBUpdaterTest_refresh.h2.db");
		}
	}
}
