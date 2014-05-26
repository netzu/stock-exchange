package indicators.movingaverage.simple;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import data.collector.StockTickerHistory;

public class BuySignalsGenerator {
	
	public List<DateTime> generate(List<SimpleMovingAverageData> averageCollection, StockTickerHistory stockCollection){
		
		double previousClose;
		double currentClose;
		double previousAverage;
		double currentAverage;
		
		List<DateTime> buySignal = new ArrayList<DateTime>();
		
		int startPoint = stockCollection.getStockTickerDataList().size() - averageCollection.size();
		
		for(int i=1; i<averageCollection.size(); i++){
			previousClose = stockCollection.getStockTickerDataList().get(i-1+startPoint).getClose();
			currentClose = stockCollection.getStockTickerDataList().get(i+startPoint).getClose();
			
			previousAverage = averageCollection.get(i-1).getAverage();
			currentAverage = averageCollection.get(i).getAverage();
			
			DecisionChain decisionChain = new DecisionChain();
			DecissionChainFactory decissionChainFactory = new DecissionChainFactory();
			decisionChain = decissionChainFactory.createChanForBuy(currentClose, previousAverage, currentAverage, previousClose);

			
			if(decisionChain.evaluate()) {				
				buySignal.add(averageCollection.get(i).getDate());
			}
		}
		return buySignal;
	}
}
