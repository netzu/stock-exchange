package buy.signal.measurements;

import indicators.williamsr.BuySignalsGenerator;
import indicators.williamsr.WilliamsRData;
import indicators.williamsr.WilliamsRIndicator;

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

public class BuySignalaAnalyserForWilliamsR {
	
	public void updateHistogramForOneTicker(String ticker, DateTime signal, int WilliamsRFactor) throws ClassNotFoundException, SQLException, ParseException{
		
		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
		final Connection connection = new DBConnection().getConnection(propertiesInstance);
		
		StockDataSelect metastockDB = new StockDataSelect(connection);
		
		StockTickerHistory stockCollectionForTicker = metastockDB.getAllDataForStockTicker(ticker);
		
		if(stockCollectionForTicker.getStockTickerDataList().size() < WilliamsRFactor){
			//continue;
			//TBD
		}
		
		WilliamsRIndicator williamsRIndicator = new WilliamsRIndicator();
		ArrayList<WilliamsRData> williamsRCollection = williamsRIndicator.calculateWilliamsR(WilliamsRFactor, stockCollectionForTicker);
		
		if(williamsRCollection.size() <= 2){
			//continue;
			//TBD
		}
		
		BuySignalsGenerator buySignals = new BuySignalsGenerator();
		List <DateTime> buySignalsForWilliamsR = buySignals.generate(williamsRCollection);
		
		connection.close();
	}
	
	private void updateHistogramInputList(List<Double> deltaForWillimas, ArrayList<Integer> listOfDays){
		
		ProfitsAnalyser analyser = new ProfitsAnalyser();
		
		if(deltaForWillimas.size() > 0){
			if(analyser.getDayNumberWithFirstProfit(deltaForWillimas).isPresent()){		
				listOfDays.add(analyser.getDayNumberWithFirstProfit(deltaForWillimas).get());
			}
		}
	}

}
