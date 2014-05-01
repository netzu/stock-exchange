package indicators.ComplexMovingAverage;

import indicators.simpleMovingAverage.AverageData;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
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
		ComplexMovingAverageSignals signals = new ComplexMovingAverageSignals(null);
		//signals.generateBuySignals(averageResults, stockCollection);
		
		//System.out.println(averageResults.);
		
		System.out.print("Koniec");

	}

}
