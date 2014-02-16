package indicators.williamsR;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

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
		
		StockDataSelect ticker = new StockDataSelect(connection);
		stockCollectionForTicker = ticker.getAllDataForStockTicker("LENA");
		
		WilliamsRIndicator wiRIndicator = new WilliamsRIndicator();
		wiRIndicator.calculateWilliamsR(14, stockCollectionForTicker);	
		
		WilliamsRSignals signal = new WilliamsRSignals();
		signal.generateWilliamsRSignals(wiRIndicator.getWilliamsRCollectionForTicker());
		
		System.out.print("KONIEC!");
    }
}
