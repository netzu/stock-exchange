package indicators.moving_average.complex;

import indicators.moving_average.simple.SimpleMovingAverageSignals;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import DAO.DBConnection;
import DAO.StockDataSelect;
import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import data.collector.StockTickerHistory;

public class ComplexMovingAverageMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
		
		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
		final Connection connection = new DBConnection().getConnection(propertiesInstance);
		
		StockTickerHistory stockCollectionForTicker = new StockTickerHistory();
		String tickerName = "LENA";
		
		StockDataSelect ticker = new StockDataSelect(connection);
		stockCollectionForTicker = ticker.getAllDataForStockTicker(tickerName);
		
		CompexMovingAverageIndicator complexMovingAverageIndicator = new CompexMovingAverageIndicator();
		List<AverageData> averageResults = complexMovingAverageIndicator.calculateComplexMovingAverage(15, 20, 25, stockCollectionForTicker);	
		
		//something is totally wrong here
		SimpleMovingAverageSignals averageSignals = new SimpleMovingAverageSignals();
		
		ComplexMovingAverageSignals signals = new ComplexMovingAverageSignals();
		signals.buysignal(averageResults, averageSignals , stockCollectionForTicker);
		
		System.out.print("Koniec");

	}

}
