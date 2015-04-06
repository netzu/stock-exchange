package buy.signal.measurements;


import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import indicators.Signal;
import indicators.movingaverage.simple.MovingAverageSignalsGenerator;
import indicators.movingaverage.simple.SimpleMovingAverageIndicator;
import indicators.williamsr.WilliamsRCalculationException;
import indicators.williamsr.WilliamsRSignalsGenerator;
import metastockDB.StockDataSelect;

import org.joda.time.DateTime;

import utils.histogram.FirstDayWithProfitsHistogram;
import utils.histogram.HistogramItem;
import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import connection.MetastockDBConnection;
import data.collector.StockTickerHistory;

public class PrototypeOfTestAnalyser {
	
	private static final int WILLIAMS_PERIOD = 3;
	private static final int TEST_RANGE = 10;
	private static final int SIMPLE_MOVING_AVERAGE = 3;

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
		
		//preparation
		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
		final Connection connection = new MetastockDBConnection().getConnection(propertiesInstance);
		
		StockDataSelect metastockDB = new StockDataSelect(connection);
		
		//get list of all companies from stock exchange
		List<String> listOfCompaniesNames = new ArrayList<String>();
		listOfCompaniesNames = metastockDB.getAllStockTickerNames();
						
		//WilliamRBuySignalAnalazer(metastockDB, listOfCompaniesNames);
		SimpleMovingAverageBuySignalAnalazer(metastockDB, listOfCompaniesNames);
		
		connection.close();
	}
	
private static void SimpleMovingAverageBuySignalAnalazer(StockDataSelect metastockDB, List<String> listOfCompaniesNames) throws SQLException, ParseException {
		
		for(int i = 0; i<50; i++ ){
			
			ArrayList<Integer> collectionOfPricesDifrences = new ArrayList<Integer>();
			int buySignalsCounter = 0;
			
			//for each ticker
			for(int nameIndex = 0; nameIndex < listOfCompaniesNames.size(); nameIndex++){
				StockTickerHistory stockCollectionForTicker = metastockDB.getAllDataForStockTicker(listOfCompaniesNames.get(nameIndex));
				
				if(stockCollectionForTicker.getEODTickDataList().size() < (SIMPLE_MOVING_AVERAGE + i)){
					continue;
				}
				
				try{
					SimpleMovingAverageIndicator indicator = new SimpleMovingAverageIndicator();
					//indicator.calculateSimpleMovingAverage(SIMPLE_MOVING_AVERAGE, stockCollectionForTicker);
					final MovingAverageSignalsGenerator signalGenerator = new MovingAverageSignalsGenerator((SIMPLE_MOVING_AVERAGE+i), indicator);
		           
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

		System.out.print(/*"WiliamR period;" + */(SIMPLE_MOVING_AVERAGE + i) + ";");
			
		PriceDeltaData priceDeltaData = new PriceDeltaData("WiliamsR", Integer.toString(WILLIAMS_PERIOD+i), TEST_RANGE, buySignalsCounter, collectionOfPricesDifrences);
		priceDeltaData.displayData(collectionOfPricesDifrences/*, "D:\\Workspace\\BuySignalAnalyzes\\", "BuySignlaAnalyzer.txt"*/);
		
		priceDeltaData.printToExcel(collectionOfPricesDifrences, "D:\\", "Wyniki.xls");
		}
	}

	private static void WilliamRBuySignalAnalazer(StockDataSelect metastockDB, List<String> listOfCompaniesNames) throws SQLException, ParseException {
		
		for(int i = 0; i<100; i++ ){
			
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

		System.out.print(/*"WiliamR period;" + */(WILLIAMS_PERIOD + i) + ";");
			
		PriceDeltaData priceDeltaData = new PriceDeltaData("WiliamsR", Integer.toString(WILLIAMS_PERIOD+i), TEST_RANGE, buySignalsCounter, collectionOfPricesDifrences);
		priceDeltaData.displayData(collectionOfPricesDifrences/*, "D:\\Workspace\\BuySignalAnalyzes\\", "BuySignlaAnalyzer.txt"*/);
		}
	}
}
