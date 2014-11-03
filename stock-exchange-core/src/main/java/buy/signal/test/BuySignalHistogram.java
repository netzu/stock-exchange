package buy.signal.test;

import java.util.List;

import org.joda.time.DateTime;

import data.collector.StockTickerHistory;

public class BuySignalHistogram {
	
	private final int size;
	
	public BuySignalHistogram(int size){
		this.size = size;
	}

	public int[] histogramForDayWithFirstProfitBasedOnEndOfDay(List<DateTime> buySignal, StockTickerHistory stockCollection){
		
		int histogram[] = new int[size];
		
		int startOfIteration = 0;
		
		for(int i=0; i<buySignal.size(); i++){
			startOfIteration = stockCollection.findStockIndexByDate(buySignal.get(i));
			
			for(int j=startOfIteration; j<startOfIteration + size; j++){
				
			}
			
		}
				
		return histogram;
	}
	
}
