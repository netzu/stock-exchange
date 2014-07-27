package utils;

import indicators.williamsr.WilliamsRIndicator;
import indicators.williamsr.WilliamsRSignals;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import org.junit.Test;

import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import dao.DBConnection;
import dao.StockDataSelect;
import data.collector.StockTickerHistory;

public class MainTest {
	
	private static final int WILLIAMS_PERIOD = 14;
	
	@Test
	public void mainTester() throws ClassNotFoundException, SQLException, ParseException{
		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
		final Connection connection = new DBConnection().getConnection(propertiesInstance);

		String tickerName = "LENA";
		
		StockDataSelect ticker = new StockDataSelect(connection);
		StockTickerHistory stockCollectionForTicker = ticker.getAllDataForStockTicker(tickerName);
		
		WilliamsRIndicator wiRIndicator = new WilliamsRIndicator();
		wiRIndicator.calculateWilliamsR(WILLIAMS_PERIOD, stockCollectionForTicker);
						
		WilliamsRSignals signal = new WilliamsRSignals();
	}

}
