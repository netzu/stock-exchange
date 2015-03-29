package metastockDB;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import org.apache.log4j.Logger;

import configuration.StockExchangeProperties;
import creator.DBConnection;
import data.DataFileReader;
import data.collector.StockTickerHistory;

public class MetastockDBUpdater {

    private final StockExchangeProperties properties;

    public MetastockDBUpdater(final StockExchangeProperties stockExchangeProperties) {
        properties = stockExchangeProperties;
    }

	private static final Logger LOGGER = Logger.getLogger(MetastockDBUpdater.class);

	public void refresh(Connection connection) throws ParseException, ClassNotFoundException, SQLException{
		
		LOGGER.info("Updating DB with Metastock freshnesst info");
		
		final Connection connection_local = connection;

		try {
			DataFileReader dataReader = new DataFileReader();

			MetastockFilesCollection allFilesInFolder = new MetastockFilesCollection(this.properties);

			for (final File tickerFile : allFilesInFolder.getListOfFiles()) {
				StockTickerHistory stockList = dataReader.getStockTickerCollection(tickerFile);
				
				StockDataInsert upToDateStockDataDb = new StockDataInsert(connection);
				upToDateStockDataDb.insertStockTickerDataCollectionWithoutDuplicates(stockList);

				LOGGER.info("Updated DB with " + tickerFile.getName());
			}
			LOGGER.info("Metastock Database has been refreshed. ENJOY and WIN !");
		} finally {
			//connection.close();	
		}
	}
}
