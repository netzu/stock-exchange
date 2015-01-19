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
	
	private static final int WILLIAMS_PERIOD = 14;
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
		
		ArrayList<Integer> collectionOfPricesDifrences = new ArrayList<Integer>();
		int buySignalsCounter = 0;		
		
		//for each ticker
		for(int nameIndex = 0; nameIndex < listOfCompaniesNames.size(); nameIndex++){
			StockTickerHistory stockCollectionForTicker = metastockDB.getAllDataForStockTicker(listOfCompaniesNames.get(nameIndex));
			
			if(stockCollectionForTicker.getEODTickDataList().size() < WILLIAMS_PERIOD){
				continue;
			}
			
			try{
				final WilliamsRSignalsGenerator signalGenerator = new WilliamsRSignalsGenerator(WILLIAMS_PERIOD);
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
		
		//create histogram
		FirstDayWithProfitsHistogram histogram = FirstDayWithProfitsHistogram.createBuckets(TEST_RANGE);
		Collection<HistogramItem<Integer>> histogramResults = histogram.calculateHistogram(collectionOfPricesDifrences);
		
		int counter = 0;
		
		histogram.printToExcel(histogramResults, buySignalsCounter, "D:\\Workspace\\BuySignalAnalyzes\\", "BuySignlaAnalyzer.txt");
		System.out.println("KONIEC");
		
		//print histogram
		for(HistogramItem<Integer> item : histogramResults){
			System.out.println(item.getPredicate() + " has elements: " + item.getItems().size());
			counter = counter + item.getItems().size();
		}
		
		System.out.println("Sum: " + counter);
		System.out.println("Total number of signals: " + buySignalsCounter);
	}
}
