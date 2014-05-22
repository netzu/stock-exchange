package indicators.williamsr;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import buy.signal.test.BuySignalTester;
import buy.signal.test.ProfitsFromSignal;
import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import dao.DBConnection;
import dao.StockDataSelect;
import data.collector.StockTickerHistory;

public class WilliamsRMain {
	
	private static final int DAY_TO_TEST = 21;
	private static final int WILLIAMS_PERIOD = 14;

	public static void main( String[] args ) throws ClassNotFoundException, SQLException, ParseException{
    	
    	StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
		final Connection connection = new DBConnection().getConnection(propertiesInstance);
		
		StockTickerHistory stockCollectionForTicker = new StockTickerHistory();
		String tickerName = "LENA";
		
		StockDataSelect ticker = new StockDataSelect(connection);
		stockCollectionForTicker = ticker.getAllDataForStockTicker(tickerName);
		
		WilliamsRIndicator wiRIndicator = new WilliamsRIndicator();
		wiRIndicator.calculateWilliamsR(WILLIAMS_PERIOD, stockCollectionForTicker);
						
		WilliamsRSignals signal = new WilliamsRSignals();
		
		
		BuySignalTester test = new BuySignalTester();
		List <List<ProfitsFromSignal>> results = test.calculateProfitsForTicker(signal.buySignals(wiRIndicator.calculateWilliamsR(WILLIAMS_PERIOD, stockCollectionForTicker)), stockCollectionForTicker, DAY_TO_TEST);
		results.isEmpty();		
				
		System.out.print("KONIEC!");
    }
}
