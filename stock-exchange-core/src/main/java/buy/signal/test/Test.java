package buy.signal.test;

import indicators.williamsr.WilliamsRData;
import indicators.williamsr.WilliamsRIndicator;
import indicators.williamsr.WilliamsRSignals;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import dao.DBConnection;
import dao.StockDataSelect;
import data.collector.StockTickerHistory;

public class Test {

	private static final int WILLIAMS_PERIOD = 14;
	private static final int NUMBER_OF_DAYS_TO_TEST = 30;

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, FileNotFoundException, UnsupportedEncodingException {
		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
		final Connection connection = new DBConnection().getConnection(propertiesInstance);
		
		StockDataSelect ticker = new StockDataSelect(connection);
		String tickerName = "LENA";
		
		StockTickerHistory stockCollectionForTicker =  ticker.getAllDataForStockTicker(tickerName);
		
		WilliamsRIndicator wiRIndicator = new WilliamsRIndicator();
		ArrayList<WilliamsRData> collection = wiRIndicator.calculateWilliamsR(WILLIAMS_PERIOD, stockCollectionForTicker);
						
		WilliamsRSignals signal = new WilliamsRSignals();
		List<DateTime> buySignlaList = signal.buySignals(collection);
		
		SingleBuySignalEffectiveness testOfBuySignalEffectiveness = new SingleBuySignalEffectiveness();
		
		PrintWriter positiveResults = new PrintWriter("D:\\wyniki\\one_signal_results.txt", "UTF-8");
		String stingForPositiveResults = "ticker;nr of days;day;T/F";
		positiveResults.println(stingForPositiveResults);
		
		PrintWriter percentageGained = new PrintWriter("D:\\wyniki\\percentage_gain.txt", "UTF-8");
		String stringForPercentage = "ticker;nr of days;day;percentage;value";

		
		//ticker| nr of test days | day | T/F | percentage | value
		for(int i=0; i<buySignlaList.size();i++){
			List<Boolean> daysWithPositiveResults = testOfBuySignalEffectiveness.daysWithPositiveResults(NUMBER_OF_DAYS_TO_TEST, buySignlaList.get(i), stockCollectionForTicker);
			List<Double> percentageGainPerDay = testOfBuySignalEffectiveness.percentageGainPerDay(NUMBER_OF_DAYS_TO_TEST, buySignlaList.get(i), stockCollectionForTicker);
			List<Double> valueGainPerDay = testOfBuySignalEffectiveness.valueGainPerDay(NUMBER_OF_DAYS_TO_TEST, buySignlaList.get(i), stockCollectionForTicker);
			
			for(int j=0; j<daysWithPositiveResults.size(); j++){
				stingForPositiveResults = tickerName + ";" + daysWithPositiveResults.size() + ";" + j + ";" + daysWithPositiveResults.get(j);
				positiveResults.println(stingForPositiveResults);
				
				stringForPercentage = tickerName + ";" + daysWithPositiveResults.size() + ";" + j + ";" + percentageGainPerDay.get(j) + ";" + valueGainPerDay.get(j);
				positiveResults.println(stringForPercentage);
			}
			
			positiveResults.println();
			percentageGained.println();
		}
		
		positiveResults.close();
		percentageGained.close();
	}

}
