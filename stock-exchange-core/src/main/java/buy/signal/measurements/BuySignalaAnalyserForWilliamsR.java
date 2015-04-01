package buy.signal.measurements;

import indicators.Signal;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import indicators.williamsr.WilliamsRSignalsGenerator;
import metastockDB.StockDataSelect;

import org.joda.time.DateTime;

import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import creator.MetastockDBConnection;
import data.collector.StockTickerHistory;

public class BuySignalaAnalyserForWilliamsR {
	
	public void updateHistogramForOneTicker(String ticker, DateTime signal, int WilliamsRFactor) throws ClassNotFoundException, SQLException, ParseException{
		
		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
		final Connection connection = new MetastockDBConnection().getConnection(propertiesInstance);
		
		StockDataSelect metastockDB = new StockDataSelect(connection);
		
		StockTickerHistory stockCollectionForTicker = metastockDB.getAllDataForStockTicker(ticker);
		
		if(stockCollectionForTicker.getEODTickDataList().size() < WilliamsRFactor){
			//continue;
			//TBD
		}

        WilliamsRSignalsGenerator singalsGenerator = new WilliamsRSignalsGenerator(WilliamsRFactor);

        List<Signal> signals = singalsGenerator.buySignals(stockCollectionForTicker);

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
