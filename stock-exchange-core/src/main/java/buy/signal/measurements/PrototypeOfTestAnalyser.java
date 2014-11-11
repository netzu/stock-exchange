package buy.signal.measurements;

import indicators.williamsr.BuySignalsGenerator;
import indicators.williamsr.WilliamsRData;
import indicators.williamsr.WilliamsRIndicator;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
		
		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
		final Connection connection = new DBConnection().getConnection(propertiesInstance);
		
		StockDataSelect metastockDB = new StockDataSelect(connection);
		
		List<String> listOfTcikerNames = new ArrayList<String>();
		listOfTcikerNames = metastockDB.getAllStockTickerNames();
		
		ArrayList<Integer> listOfDays = new ArrayList<Integer>();
		
		int buySignalsCounter = 0;
		
		
		//for each ticker
		for(int nameIndex = 0; nameIndex < listOfTcikerNames.size(); nameIndex++){
			StockTickerHistory stockCollectionForTicker = metastockDB.getAllDataForStockTicker(listOfTcikerNames.get(nameIndex));
			
			if(stockCollectionForTicker.getStockTickerDataList().size() < WILLIAMS_PERIOD){
				continue;
			}
			
			//create WilliamsR Indicator
			WilliamsRIndicator williamsRIndicator = new WilliamsRIndicator();
			ArrayList<WilliamsRData> williamsRCollection = williamsRIndicator.calculateWilliamsR(WILLIAMS_PERIOD, stockCollectionForTicker);
			
			if(williamsRCollection.size() <= 2){
				continue;
			}
			
			//generate signals for buy
			BuySignalsGenerator buySignals = new BuySignalsGenerator();
			List <DateTime> buySignalsForWilliamsR = buySignals.generate(williamsRCollection);
			
			buySignalsCounter = buySignalsCounter + buySignalsForWilliamsR.size();
			
			ProfitsAnalyser analyser = new ProfitsAnalyser();
			
			//for each signal
			for(int buySignalsIterator = 0; buySignalsIterator < buySignalsForWilliamsR.size(); buySignalsIterator++){
				
				//calculate delta
				PriceDelta delta = new PriceDelta();
				DateTime signal = buySignalsForWilliamsR.get(buySignalsIterator);
				
				List<Double> deltaForWillimas  = delta.calculateInValue(signal, stockCollectionForTicker, TEST_RANGE);
				
				//and if there was profit add number of a day with first profit to the list
				if(deltaForWillimas.size() > 0){
					if(analyser.getDayNumberWithFirstProfit(deltaForWillimas).isPresent()){		
						listOfDays.add(analyser.getDayNumberWithFirstProfit(deltaForWillimas).get());
					}
				}
				
			}
		}
		
		//create histogram
		FirstDayWithProfitsHistogram histogram = FirstDayWithProfitsHistogram.createBuckets(TEST_RANGE);
		Collection<HistogramItem<Integer>> histogramResults = histogram.calculateHistogram(listOfDays);
		
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
