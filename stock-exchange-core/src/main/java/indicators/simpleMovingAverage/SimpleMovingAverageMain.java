package indicators.simpleMovingAverage;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import DAO.DBConnection;
import DAO.StockDataSelect;
import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import data.collector.StockTickerHistory;

public class SimpleMovingAverageMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {

		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
		final Connection connection = new DBConnection().getConnection(propertiesInstance);
		
		StockTickerHistory stockCollectionForTicker = new StockTickerHistory();
		
		StockDataSelect ticker = new StockDataSelect(connection);
		stockCollectionForTicker = ticker.getAllDataForStockTicker("LENA");
		
		SimpleMovingAverageIndicator simpleMovingAverageIndicator = new SimpleMovingAverageIndicator();
		simpleMovingAverageIndicator.calculateSimpleMovingAverage(13, stockCollectionForTicker);
		//simpleMovingAverageIndicator.printToLog();
		
		SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
		signals.generateSimpleSignals(simpleMovingAverageIndicator.getAverage(), stockCollectionForTicker);
		
		System.out.print("Koniec");
	}
}
