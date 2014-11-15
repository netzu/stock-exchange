package data.collector.manipulator;

import data.collector.EODTick;


public class WeeklyStockDataManipulator extends PeriodicalStockTickerDataManipulator {

	@Override
	public boolean fromSamePeriod(EODTick first, EODTick second) {
		return first.getDate().getWeekOfWeekyear() == second.getDate().getWeekOfWeekyear();
	}
	
}
