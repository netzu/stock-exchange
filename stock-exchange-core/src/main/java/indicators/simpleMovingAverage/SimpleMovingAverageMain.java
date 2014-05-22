package indicators.simpleMovingAverage;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import org.joda.time.DateTime;

import DAO.DBConnection;
import DAO.StockDataSelect;
import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import data.collector.StockTickerHistory;

public class SimpleMovingAverageMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {

		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
		final Connection connection = new DBConnection().getConnection(propertiesInstance);
		
		StockDataSelect ticker = new StockDataSelect(connection);
		StockTickerHistory stockCollectionForTicker = ticker.getAllDataForStockTicker("LENA");
		
		ArrayList<SimpleMovingAverageData> simpleMovingAverageData = SimpleMovingAverageIndicator.calculateSimpleMovingAverage(13, stockCollectionForTicker);
		
		SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
		
		ArrayList<DateTime> buySignals = signals.buySignal(simpleMovingAverageData, stockCollectionForTicker);
		ArrayList<DateTime> sellSignals = signals.simpleSell(simpleMovingAverageData, stockCollectionForTicker);
		
		
		
		System.out.print("Koniec");
	}
}
