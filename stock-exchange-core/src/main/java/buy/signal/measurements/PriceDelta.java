package buy.signal.measurements;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import data.collector.StockTickerHistory;

public class PriceDelta {
	
	public static List<Double> calculateInValue(DateTime signal, StockTickerHistory stockCollection, int testRange){
		
		ArrayList<Double> results = new ArrayList<Double>();

		int startingIndex = stockCollection.findStockIndexByDate(signal) + 1;
		double buyPrice = stockCollection.findStockByDate(signal).getClose();
		
		int finishIndex = startingIndex + testRange;
		
		for(int i = startingIndex; i < finishIndex; i++){
			if( i == stockCollection.getEODTickDataList().size())
				break;
			double currentPrice = stockCollection.getEODTickDataList().get(i).getClose();
			results.add(currentPrice - buyPrice);
		}
		
		return results;		
	}
	
	//public statisc List<Double> calculateInPercentage(DateTime signal, StockTickerHistory stockCollection, int testRange)

}
