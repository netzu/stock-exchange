package data.collector.manipulator;

import data.collector.StockTicker;

public class MonthlyStockDataManipulator extends PeriodicalStockTickerDataManipulator {

	@Override
	public boolean fromSamePeriod(StockTicker first, StockTicker second) {
		return first.getDate().getMonthOfYear() == second.getDate().getMonthOfYear();
	}
}
