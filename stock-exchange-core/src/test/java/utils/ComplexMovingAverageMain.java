package utils;

import indicators.movingaverage.complex.ComplexMovingAverageSettings;
import indicators.movingaverage.complex.ComplexMovingAverageSignalsGenerator;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import metastockDB.StockDataSelect;

import org.junit.Ignore;
import org.junit.Test;

import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import creator.MetastockDBConnection;
import data.collector.StockTickerHistory;

public class ComplexMovingAverageMain {

	private static final int PERIOD_FOR_THIRD_AVERAGE = 25;
	private static final int PERIOD_FOR_SECOND_AVERAGE = 20;
	private static final int PERIOD_FOR_FIRST_AVERAGE = 15;

	@Test
    @Ignore("This is not unit test more integration test")
	public void test() throws ClassNotFoundException, SQLException, ParseException {
		
		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
		final Connection connection = new MetastockDBConnection().getConnection(propertiesInstance);

		String tickerName = "LENA";
		
		StockDataSelect ticker = new StockDataSelect(connection);
		StockTickerHistory stockCollectionForTicker = ticker.getAllDataForStockTicker(tickerName);

        final ComplexMovingAverageSettings settings = new ComplexMovingAverageSettings(PERIOD_FOR_FIRST_AVERAGE, PERIOD_FOR_SECOND_AVERAGE, PERIOD_FOR_THIRD_AVERAGE);

        ComplexMovingAverageSignalsGenerator generator = new ComplexMovingAverageSignalsGenerator(settings);
        generator.buySignals(stockCollectionForTicker);


	}

}
