package database.manipulator;

import DAO.DBConnection;
import DAO.StockDataInsert;
import configuration.StockExchangeProperties;
import data.DataFileReader;
import data.collector.StockTickerHistory;
import org.apache.log4j.Logger;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class MetastockDBUpdater {

    private final StockExchangeProperties properties;

    public MetastockDBUpdater(final StockExchangeProperties stockExchangeProperties) {
        properties = stockExchangeProperties;
    }

	private static final Logger LOGGER = Logger.getLogger(MetastockDBUpdater.class);

	public void refresh() throws ParseException, ClassNotFoundException, SQLException{
		
		LOGGER.info("Updating DB with Metastock freshnesst info");
		
		final Connection connection = new DBConnection().getConnection(this.properties);

		try {
			StockTickerHistory stockList = new StockTickerHistory();
			DataFileReader dataReader = new DataFileReader();

			MetastockFilesCollection allFilesInFolder = new MetastockFilesCollection(
					this.properties);

			for (final File tickerFile : allFilesInFolder.getListOfFiles()) {
				stockList = dataReader.getStockTickerCollection(tickerFile);
				LOGGER.info("Updated DB with " + tickerFile.getName());

				StockDataInsert upToDateStockDataDb = new StockDataInsert(
						connection);

				upToDateStockDataDb
						.insertStockTickerDataCollectionWithoutDuplicates(stockList);

			}
			LOGGER.info("Metastock Database has been refreshed. ENJOY and WIN !");
		} finally {
			connection.close();	
		}
	}
}
