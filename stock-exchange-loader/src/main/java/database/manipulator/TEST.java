package database.manipulator;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import DAO.StockDataInsert;

import org.apache.log4j.Logger;

import DAO.DBConnection;
import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import data.collector.StockTickerHistory;

public class TEST {
	
	private static org.apache.log4j.Logger log = Logger.getLogger(TEST.class);
	
	public static void main(final String[] args) throws ClassNotFoundException, SQLException, ParseException{
		
		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
		final Connection connection = new DBConnection().getConnection(propertiesInstance);
		try {
			StockTickerHistory stockList = new StockTickerHistory();
			DataFileReader dataReader = new DataFileReader();

			MetastockFilesCollection allFilesInFolder = new MetastockFilesCollection(propertiesInstance);

			for (final File tickerFile : allFilesInFolder.getListOfFiles()) {
				stockList = dataReader.getStockTickerCollection(tickerFile);
				log.info(tickerFile.getName());

				StockDataInsert upToDateStockDataDb = new StockDataInsert(connection);

				upToDateStockDataDb.insertStockTickerDataCollectionWithoutDuplicates(stockList);

			}
			log.info("DONE");
		} finally {
			connection.close();	
		}
			
		//log.info(start.now());
	}
	
}
