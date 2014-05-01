package indicators.ComplexMovingAverage;

import indicators.simpleMovingAverage.AverageData;
import indicators.simpleMovingAverage.SimpleMovingAverageIndicator;

import java.util.Arrays;
import java.util.List;

import data.collector.StockTickerHistory;

public class CompexMovingAverageIndicator {
	
	public List<AverageData> calculateComplexMovingAverage(int firstPeriod, int secondPeriod, int thirdPeriod, StockTickerHistory tickerCollection){
		
		AverageData averageData = new AverageData(firstPeriod, SimpleMovingAverageIndicator.calculateSimpleMovingAverage(firstPeriod, tickerCollection));
		AverageData averageData2 = new AverageData(secondPeriod, SimpleMovingAverageIndicator.calculateSimpleMovingAverage(secondPeriod, tickerCollection));
		AverageData averageData3 = new AverageData(thirdPeriod, SimpleMovingAverageIndicator.calculateSimpleMovingAverage(thirdPeriod, tickerCollection));

		return Arrays.asList(averageData, averageData2, averageData3);
	}	


}
