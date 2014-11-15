package data.collector.manipulator;

import data.collector.EODTick;

public class MonthlyStockDataManipulator extends PeriodicalStockTickerDataManipulator {

	@Override
	public boolean fromSamePeriod(EODTick first, EODTick second) {
		return first.getDate().getMonthOfYear() == second.getDate().getMonthOfYear();
	}
}
