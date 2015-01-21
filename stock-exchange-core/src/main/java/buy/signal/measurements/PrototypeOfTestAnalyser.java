package buy.signal.measurements;


import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import indicators.Signal;
import indicators.williamsr.WilliamsRCalculationException;
import indicators.williamsr.WilliamsRSignalsGenerator;
import org.joda.time.DateTime;

import utils.histogram.FirstDayWithProfitsHistogram;
import utils.histogram.HistogramItem;
import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import dao.DBConnection;
import dao.StockDataSelect;
import data.collector.StockTickerHistory;

public class PrototypeOfTestAnalyser {
	
	private static final int WILLIAMS_PERIOD = 3;
	private static final int TEST_RANGE = 10;

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
		
		//preparation
		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
		final Connection connection = new DBConnection().getConnection(propertiesInstance);
		
		StockDataSelect metastockDB = new StockDataSelect(connection);
		
		//get list of all companies from stock exchange
		List<String> listOfCompaniesNames = new ArrayList<String>();
		listOfCompaniesNames = metastockDB.getAllStockTickerNames();
						
		
		for(int i = 0; i<20; i++ ){
			
			ArrayList<Integer> collectionOfPricesDifrences = new ArrayList<Integer>();
			int buySignalsCounter = 0;
			
			//for each ticker
			for(int nameIndex = 0; nameIndex < listOfCompaniesNames.size(); nameIndex++){
				StockTickerHistory stockCollectionForTicker = metastockDB.getAllDataForStockTicker(listOfCompaniesNames.get(nameIndex));
				
				if(stockCollectionForTicker.getEODTickDataList().size() < (WILLIAMS_PERIOD + i)){
					continue;
				}
				
				try{
					final WilliamsRSignalsGenerator signalGenerator = new WilliamsRSignalsGenerator((WILLIAMS_PERIOD+i));
		            List<Signal> buySignalsForTicker = signalGenerator.buySignals(stockCollectionForTicker);
		            buySignalsCounter = buySignalsCounter + buySignalsForTicker.size();
					
					//ProfitsAnalyser profitsAnalyser = new ProfitsAnalyser();
		            PriceDeltaAnalyzer priceDeltaAnalyzer = new PriceDeltaAnalyzer();
					collectionOfPricesDifrences.addAll(priceDeltaAnalyzer.analyze(stockCollectionForTicker, buySignalsForTicker, TEST_RANGE));
					
				}catch(WilliamsRCalculationException error){
					
					String expectedErrorMessage = "Cannot generate buy signal for williams R collection which has less than 2 elements";				
					if(!error.getMessage().equals(expectedErrorMessage)){
						throw error;
					}
				}        		
			}

		System.out.println("WiliamR period: " + (WILLIAMS_PERIOD + i));
			
		PriceDeltaData priceDeltaData = new PriceDeltaData("WiliamsR", Integer.toString(WILLIAMS_PERIOD+i), TEST_RANGE, buySignalsCounter, collectionOfPricesDifrences);
		priceDeltaData.displayData(collectionOfPricesDifrences/*, "D:\\Workspace\\BuySignalAnalyzes\\", "BuySignlaAnalyzer.txt"*/);
		}
	}
}
