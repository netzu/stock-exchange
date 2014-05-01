package indicators.williamsR;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import buySignalTest.BuySignalTester;
import buySignalTest.BuySingalStatistics;
import buySignalTest.ProfitsFromSignal;
import DAO.DBConnection;
import DAO.StockDataSelect;
import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import data.collector.StockTickerHistory;

public class WilliamsRMain {
	
	public static void main( String[] args ) throws ClassNotFoundException, SQLException, ParseException{
    	
    	StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
		final Connection connection = new DBConnection().getConnection(propertiesInstance);
		
		StockTickerHistory stockCollectionForTicker = new StockTickerHistory();
		String tickerName = "LENA";
		
		StockDataSelect ticker = new StockDataSelect(connection);
		stockCollectionForTicker = ticker.getAllDataForStockTicker(tickerName);
		
		WilliamsRIndicator wiRIndicator = new WilliamsRIndicator();
		wiRIndicator.calculateWilliamsR(14, stockCollectionForTicker);		
				
		WilliamsRSignals signal = new WilliamsRSignals();
		signal.generateWilliamsRSignals(wiRIndicator.getWilliamsRCollectionForTicker());
		
		BuySignalTester test = new BuySignalTester();
		List <List<ProfitsFromSignal>> results = test.calculateProfitsForTicker(signal.getBuySignal(), stockCollectionForTicker, 21);
		List <BuySingalStatistics> stats = test.statisticsForTicker(results, 21);
		
		System.out.print("KONIEC!");
    }
}
