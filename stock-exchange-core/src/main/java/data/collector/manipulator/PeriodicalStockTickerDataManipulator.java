package data.collector.manipulator;

import java.util.ArrayList;
import java.util.List;

import data.collector.EODTick;
import data.collector.StockTickerHistory;

public abstract class PeriodicalStockTickerDataManipulator implements StockTickerCollectionManipulator {

	abstract boolean fromSamePeriod(final EODTick first, final EODTick second);
	
	public StockTickerHistory manipulate(final StockTickerHistory input) {
		List<EODTick>  dailyCollection = input.getEODTickDataList();
		
		if (dailyCollection.isEmpty()) {
			return new StockTickerHistory();
		}
		
		EODTick aggragatedData = EODTick.copy(dailyCollection.get(0));
		final ArrayList<EODTick> aggregator = new ArrayList<EODTick>();
		aggregator.add(aggragatedData);
		
		for (int i = 1; i < dailyCollection.size(); i++) {
			EODTick dailyData = dailyCollection.get(i);
			if (fromSamePeriod(aggragatedData, dailyData)) {				
				updatePeriodDataWithDailyData(aggragatedData, dailyData);
				
			} else {
				aggragatedData = EODTick.copy(dailyData);
				aggregator.add(aggragatedData);
			}			
		}
		
		StockTickerHistory result = new StockTickerHistory();
		result.setEODTickDataList(aggregator);
		
		return result;
	}
	
	private void updatePeriodDataWithDailyData(final EODTick aggragated, final EODTick dailyData) {
		
		if(dailyData.getHigh() > aggragated.getHigh()){
			aggragated.setHigh(dailyData.getHigh());
		}
		
		if(dailyData.getLow() < aggragated.getLow()){
			aggragated.setLow(dailyData.getLow());
		}
		
		aggragated.setClose(dailyData.getClose());
		aggragated.setVolumen(aggragated.getVolumen() + dailyData.getVolumen());	
	}
}
