package utils;

import com.google.common.collect.Lists;

import indicators.DateTimeFromSignal;
import indicators.movingaverage.simple.MovingAverageSignalsGenerator;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import indicators.movingaverage.simple.SimpleMovingAverageIndicator;
import metastockDB.StockDataSelect;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;

import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import creator.MetastockDBConnection;
import data.collector.StockTickerHistory;

public class SimpleMovingAverageMain {

	private static final int PERIOD_FOR_MOVING_AVERAGE = 13;

	@Test
    @Ignore("This is not unit test more integration test")
	public void test() throws ClassNotFoundException, SQLException, ParseException {

		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
		final Connection connection = new MetastockDBConnection().getConnection(propertiesInstance);
		
		StockDataSelect ticker = new StockDataSelect(connection);
		StockTickerHistory stockCollectionForTicker = ticker.getAllDataForStockTicker("LENA");


		MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(PERIOD_FOR_MOVING_AVERAGE, new SimpleMovingAverageIndicator());

        List<DateTime> sellSignal = Lists.transform(signals.sellSignals(stockCollectionForTicker), new DateTimeFromSignal());
        List<DateTime> buySignal = Lists.transform(signals.buySignals(stockCollectionForTicker), new DateTimeFromSignal());

        //buySignals.isEmpty();
		//sellSignals.isEmpty();

		connection.close();
	}
}
