package data.collector;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import org.joda.time.DateTime;
import org.apache.log4j.Logger;

import sources.storage.FilesCollectionFromCentralStorage;
import DAO.DBConnection;
import DAO.UpdateDBWithFreshnestData;
import configuration.ApplicationContext;
import configuration.StockExchangeProperties;

public class Main {
	
	private static org.apache.log4j.Logger log = Logger.getLogger(Main.class);
	
	public static void main(final String[] args) throws ClassNotFoundException, SQLException, ParseException{
		DateTime start = new DateTime(); 
		log.info(start.now());
		
		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
		final Connection connection = new DBConnection().getConnection(propertiesInstance);
		try {
			StockTickerCollection stockList = new StockTickerCollection();
			DataFileReader dataReader = new DataFileReader();

			FilesCollectionFromCentralStorage allFilesInFolder = new FilesCollectionFromCentralStorage(
					propertiesInstance);

			for (final File tickerFile : allFilesInFolder.getListOfFiles()) {
				stockList = dataReader.getStockTickerCollection(tickerFile);
				log.info(tickerFile.getName());

				UpdateDBWithFreshnestData upToDateStockDataDb = new UpdateDBWithFreshnestData(
						connection);

				upToDateStockDataDb
						.insertStockTickerDataCollectionWithoutDuplicates(stockList);

			}
			log.info("DONE");
		} finally {
			connection.close();	
		}
			
		log.info(start.now());
	}
	
}
