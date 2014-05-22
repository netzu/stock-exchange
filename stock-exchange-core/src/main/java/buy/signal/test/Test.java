package buy.signal.test;

import indicators.williams_r.WilliamsRData;
import indicators.williams_r.WilliamsRIndicator;
import indicators.williams_r.WilliamsRSignals;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import DAO.DBConnection;
import DAO.StockDataSelect;
import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import data.collector.StockTickerHistory;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, FileNotFoundException, UnsupportedEncodingException {
		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
		final Connection connection = new DBConnection().getConnection(propertiesInstance);
		
		StockTickerHistory stockCollectionForTicker = new StockTickerHistory();
		String tickerName = "LENA";
		
		StockDataSelect ticker = new StockDataSelect(connection);
		stockCollectionForTicker = ticker.getAllDataForStockTicker(tickerName);
		
		WilliamsRIndicator wiRIndicator = new WilliamsRIndicator();
		ArrayList<WilliamsRData> collection = wiRIndicator.calculateWilliamsR(14, stockCollectionForTicker);
						
		WilliamsRSignals signal = new WilliamsRSignals();
		List<DateTime> buySignlaList = signal.buySignals(collection);
		
		SingleBuySignalEffectiveness testOfBuySignalEffectiveness = new SingleBuySignalEffectiveness();
		
		PrintWriter positiveResults = new PrintWriter("D:\\wyniki\\one_signal_results.txt", "UTF-8");
		String stingForPositiveResults = new String();
		stingForPositiveResults = "ticker;nr of days;day;T/F";
		positiveResults.println(stingForPositiveResults);
		
		PrintWriter percentageGained = new PrintWriter("D:\\wyniki\\percentage_gain.txt", "UTF-8");
		String stringForPercentage = new String();
		stringForPercentage = "ticker;nr of days;day;percentage;value";

		
		//ticker| nr of test days | day | T/F | percentage | value
		for(int i=0; i<buySignlaList.size();i++){
			List<Boolean> daysWithPositiveResults = testOfBuySignalEffectiveness.daysWithPositiveResults(30, buySignlaList.get(i), stockCollectionForTicker);
			List<Double> percentageGainPerDay = testOfBuySignalEffectiveness.percentageGainPerDay(30, buySignlaList.get(i), stockCollectionForTicker);
			double[] valueGainPerDay = testOfBuySignalEffectiveness.valueGainPerDay(30, buySignlaList.get(i), stockCollectionForTicker);
			
			for(int j=0; j<daysWithPositiveResults.size(); j++){
				stingForPositiveResults = tickerName + ";" + daysWithPositiveResults.size() + ";" + j + ";" + daysWithPositiveResults.get(j);
				positiveResults.println(stingForPositiveResults);
				
				stringForPercentage = tickerName + ";" + daysWithPositiveResults.size() + ";" + j + ";" + percentageGainPerDay.get(j) + ";" + valueGainPerDay[j];
				positiveResults.println(stringForPercentage);
			}
			
			
			positiveResults.println();
			percentageGained.println();
		}
		
		positiveResults.close();
		percentageGained.close();
	}

}
