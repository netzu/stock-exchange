package buy.signal.measurements;

import indicators.Signal;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import data.collector.StockTickerHistory;

public class PriceDeltaAnalyzer {

	public static ArrayList<Integer> analyze( StockTickerHistory stockCollectionForTicker, List<Signal> buySignalsForTicker, int testRange) {
		
		ProfitsAnalyser profitsAnalyser = new ProfitsAnalyser();
		ArrayList<Integer> collectionOfPricesDifrences = new ArrayList<Integer>();
		
		for(int buySignalsIterator = 0; buySignalsIterator < buySignalsForTicker.size(); buySignalsIterator++){
			
			//calculate delta
			PriceDeltaCalculator priceDelta = new PriceDeltaCalculator();
			DateTime signal = buySignalsForTicker.get(buySignalsIterator).getDate();
			
			List<Double> deltaForWillimas  = priceDelta.getDelta(signal, stockCollectionForTicker, testRange);
			
			//and if there was profit add number of a day with first profit to the list
			if(deltaForWillimas.size() > 0){
				if(profitsAnalyser.getDayNumberWithFirstProfit(deltaForWillimas).isPresent()){		
					collectionOfPricesDifrences.add(profitsAnalyser.getDayNumberWithFirstProfit(deltaForWillimas).get());
				}
			}			
		}
		
		return collectionOfPricesDifrences;
	}
}
