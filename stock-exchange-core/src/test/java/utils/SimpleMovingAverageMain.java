package utils;

import indicators.movingaverage.simple.SimpleMovingAverageData;
import indicators.movingaverage.simple.SimpleMovingAverageIndicator;
import indicators.movingaverage.simple.SimpleMovingAverageSignalsGenerator;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import dao.DBConnection;
import dao.StockDataSelect;
import data.collector.StockTickerHistory;

public class SimpleMovingAverageMain {

	private static final int PERIOD_FOR_MOVING_AVERAGE = 13;

	@Test
	public void test() throws ClassNotFoundException, SQLException, ParseException {

		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
		final Connection connection = new DBConnection().getConnection(propertiesInstance);
		
		StockDataSelect ticker = new StockDataSelect(connection);
		StockTickerHistory stockCollectionForTicker = ticker.getAllDataForStockTicker("LENA");
		SimpleMovingAverageIndicator indicator = new SimpleMovingAverageIndicator();    	
		
		List<SimpleMovingAverageData> simpleMovingAverageData = indicator.calculateSimpleMovingAverage(PERIOD_FOR_MOVING_AVERAGE, stockCollectionForTicker);
		
		SimpleMovingAverageSignalsGenerator signals = new SimpleMovingAverageSignalsGenerator(period);
		
		List<DateTime> buySignals = signals.getBuySignal(simpleMovingAverageData, stockCollectionForTicker);
		List<DateTime> sellSignals = signals.getSellSignals(simpleMovingAverageData, stockCollectionForTicker);
		
		//buySignals.isEmpty();
		//sellSignals.isEmpty();

		connection.close();
	}
}
