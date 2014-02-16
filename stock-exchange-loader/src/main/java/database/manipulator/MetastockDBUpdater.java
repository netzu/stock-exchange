package database.manipulator;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import org.apache.log4j.Logger;

import DAO.DBConnection;
import DAO.StockDataInsert;
import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import data.collector.StockTickerHistory;

public class MetastockDBUpdater {
	
	private static org.apache.log4j.Logger log = Logger.getLogger(MetastockDBUpdater.class);

	public void refresh() throws ParseException, ClassNotFoundException, SQLException{
		
		log.info("Updating DB with Metastock freshnesst info");
		
		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();		
		final Connection connection = new DBConnection().getConnection(propertiesInstance);
		propertiesInstance.getDBUrl();
		
		try {
			StockTickerHistory stockList = new StockTickerHistory();
			DataFileReader dataReader = new DataFileReader();

			MetastockFilesCollection allFilesInFolder = new MetastockFilesCollection(
					propertiesInstance);

			for (final File tickerFile : allFilesInFolder.getListOfFiles()) {
				stockList = dataReader.getStockTickerCollection(tickerFile);
				log.info("Updated DB with " + tickerFile.getName());

				StockDataInsert upToDateStockDataDb = new StockDataInsert(
						connection);

				upToDateStockDataDb
						.insertStockTickerDataCollectionWithoutDuplicates(stockList);

			}
			log.info("Metastock Database has been reffreshed. ENJOY and WIN !");
		} finally {
			connection.close();	
		}
	}
}
