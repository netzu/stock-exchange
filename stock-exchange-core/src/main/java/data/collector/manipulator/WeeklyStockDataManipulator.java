package data.collector.manipulator;

import data.collector.StockTicker;


public class WeeklyStockDataManipulator extends PeriodicalStockTickerDataManipulator {

	@Override
	public boolean fromSamePeriod(StockTicker first, StockTicker second) {
		return first.getDate().getWeekOfWeekyear() == second.getDate().getWeekOfWeekyear();
	}
	
}
