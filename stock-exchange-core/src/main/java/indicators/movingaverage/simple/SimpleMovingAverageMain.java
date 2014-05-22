package indicators.movingaverage.simple;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import dao.DBConnection;
import dao.StockDataSelect;
import data.collector.StockTickerHistory;

public class SimpleMovingAverageMain {

	private static final int PERIOD_FOR_MOVING_AVERAGE = 13;

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {

		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
		final Connection connection = new DBConnection().getConnection(propertiesInstance);
		
		StockDataSelect ticker = new StockDataSelect(connection);
		StockTickerHistory stockCollectionForTicker = ticker.getAllDataForStockTicker("LENA");
		
		List<SimpleMovingAverageData> simpleMovingAverageData = SimpleMovingAverageIndicator.calculateSimpleMovingAverage(PERIOD_FOR_MOVING_AVERAGE, stockCollectionForTicker);
		
		SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
		
		List<DateTime> buySignals = signals.buySignal(simpleMovingAverageData, stockCollectionForTicker);
		List<DateTime> sellSignals = signals.simpleSell(simpleMovingAverageData, stockCollectionForTicker);
		
		buySignals.isEmpty();
		sellSignals.isEmpty();
		
		System.out.print("Koniec");
	}
}
