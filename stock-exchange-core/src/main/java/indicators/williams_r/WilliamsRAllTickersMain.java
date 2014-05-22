package indicators.williams_r;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import buy.signal.test.BuySignalTester;
import buy.signal.test.BuySingalStatistics;
import buy.signal.test.ProfitsFromSignal;
import DAO.DBConnection;
import DAO.StockDataSelect;
import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import data.collector.StockTickerHistory;

public class WilliamsRAllTickersMain {
	
	public static void main( String[] args ) throws ClassNotFoundException, SQLException, ParseException, FileNotFoundException, UnsupportedEncodingException{

		
    	StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
		Connection connection = new DBConnection().getConnection(propertiesInstance);
		
		StockDataSelect querryDB = new StockDataSelect(connection);
		List<String> listOfTickerNames = querryDB.getAllStockTickerNames();
		
		PrintWriter writer = new PrintWriter("StatsALL.txt", "UTF-8");
		writer.println("SumOfProfits;SumOfPercentage;Average;StandardDeviation;Median;Variance;Min;Max;"
				+ "SumNegativeProfits;SumPositiveProfits;CountNegativeProfits;CountPositiveProfits");
		
		for(int i=0; i<listOfTickerNames.size(); i++){
			
			StockTickerHistory stockCollectionForTicker = new StockTickerHistory();
			String tickerName = listOfTickerNames.get(i);
			
			StockDataSelect ticker = new StockDataSelect(connection);
			stockCollectionForTicker = ticker.getAllDataForStockTicker(tickerName);
			WilliamsRIndicator wiRIndicator = new WilliamsRIndicator();
			wiRIndicator.calculateWilliamsR(14, stockCollectionForTicker);		
					
			WilliamsRSignals signal = new WilliamsRSignals();			
			
			BuySignalTester test = new BuySignalTester();
			List <List<ProfitsFromSignal>> results = test.calculateProfitsForTicker(signal.buySignals(wiRIndicator.calculateWilliamsR(14, stockCollectionForTicker)), stockCollectionForTicker, 21);
			List <BuySingalStatistics> stats = test.statisticsForTicker(results, 21);
			
			for(int j=0; j<stats.size(); j++){
				writer.print(tickerName + ";" + j + ";" + stats.get(j).printStatsToString());
			}			
		}
		
		writer.close();
		
		System.out.print("KONIEC!");
    }
}
