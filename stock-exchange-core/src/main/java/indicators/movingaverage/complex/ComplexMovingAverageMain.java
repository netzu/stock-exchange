package indicators.movingaverage.complex;

import indicators.movingaverage.simple.SimpleMovingAverageSignals;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import dao.DBConnection;
import dao.StockDataSelect;
import data.collector.StockTickerHistory;

public class ComplexMovingAverageMain {

	private static final int PERIOD_FOR_THIRD_AVERAGE = 25;
	private static final int PERIOD_FOR_SECOND_AVERAGE = 20;
	private static final int PERIOD_FOR_FIRST_AVERAGE = 15;

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
		
		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
		final Connection connection = new DBConnection().getConnection(propertiesInstance);

		String tickerName = "LENA";
		
		StockDataSelect ticker = new StockDataSelect(connection);
		StockTickerHistory stockCollectionForTicker = ticker.getAllDataForStockTicker(tickerName);
		
		CompexMovingAverageIndicator complexMovingAverageIndicator = new CompexMovingAverageIndicator();
		List<AverageData> averageResults = complexMovingAverageIndicator.calculateComplexMovingAverage(PERIOD_FOR_FIRST_AVERAGE, PERIOD_FOR_SECOND_AVERAGE, PERIOD_FOR_THIRD_AVERAGE, stockCollectionForTicker);	
		
		//something is totally wrong here
		SimpleMovingAverageSignals averageSignals = new SimpleMovingAverageSignals();
		
		ComplexMovingAverageSignals signals = new ComplexMovingAverageSignals();
		signals.buysignal(averageResults, averageSignals , stockCollectionForTicker);
		
		System.out.print("Koniec");

	}

}
