package data.collector.manipulator;

import java.util.ArrayList;
import java.util.List;

import data.collector.StockTicker;
import data.collector.StockTickerHistory;

public abstract class PeriodicalStockTickerDataManipulator implements StockTickerCollectionManipulator {

	
	public StockTickerHistory manipulate(final StockTickerHistory input) {
		List<StockTicker>  dailyCollection = input.getStockTickerDataList();
		
		if (dailyCollection.isEmpty()) {
			return new StockTickerHistory();
		}
		
		StockTicker aggragatedData = StockTicker.copy(dailyCollection.get(0));
		final ArrayList<StockTicker> aggregator = new ArrayList<StockTicker>();
		aggregator.add(aggragatedData);
		
		for (int i = 1; i < dailyCollection.size(); i++) {
			StockTicker dailyData = dailyCollection.get(i);
			if (fromSamePeriod(aggragatedData, dailyData)) {				
				updatePeriodDataWithDailyData(aggragatedData, dailyData);
				
			} else {
				aggragatedData = StockTicker.copy(dailyData);
				aggregator.add(aggragatedData);
			}			
		}
		
		StockTickerHistory result = new StockTickerHistory();
		result.setStockTickerDataList(aggregator);
		
		return result;
	}
	
	
	public abstract boolean fromSamePeriod(final StockTicker first, final StockTicker second);


	private void updatePeriodDataWithDailyData(final StockTicker aggragated, final StockTicker dailyData) {
		
		if(dailyData.getHigh() > aggragated.getHigh()){
			aggragated.setHigh(dailyData.getHigh());
		}
		
		if(dailyData.getLow() < aggragated.getLow()){
			aggragated.setLow(dailyData.getLow());
		}
		
		aggragated.setClose(dailyData.getClose());
		aggragated.setVolumen(aggragated.getVolumen() + dailyData.getVolumen());
		//aggragated.setDate(dailyData.getDate());
	
	}
}
