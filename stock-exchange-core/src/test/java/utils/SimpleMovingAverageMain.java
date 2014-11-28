package utils;

import com.google.common.collect.Lists;
import indicators.DateTimeFromSignal;
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


		SimpleMovingAverageSignalsGenerator signals = new SimpleMovingAverageSignalsGenerator(PERIOD_FOR_MOVING_AVERAGE);

        List<DateTime> sellSignal = Lists.transform(signals.sellSignals(stockCollectionForTicker), new DateTimeFromSignal());
        List<DateTime> buySignal = Lists.transform(signals.buySignals(stockCollectionForTicker), new DateTimeFromSignal());

        //buySignals.isEmpty();
		//sellSignals.isEmpty();

		connection.close();
	}
}
