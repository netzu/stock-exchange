package indicators.movingaverage.complex;

import indicators.movingaverage.simple.SimpleMovingAverageIndicator;

import java.util.Arrays;
import java.util.List;

import data.collector.StockTickerHistory;

public class CompexMovingAverageIndicator {
	
	public List<AverageData> calculateComplexMovingAverage(int firstPeriod, int secondPeriod, int thirdPeriod, StockTickerHistory tickerCollection){

		SimpleMovingAverageIndicator simpleIndicatore = new SimpleMovingAverageIndicator();
		
		AverageData averageData = new AverageData(firstPeriod, simpleIndicatore.calculateSimpleMovingAverage(firstPeriod, tickerCollection));
		AverageData averageData2 = new AverageData(secondPeriod, simpleIndicatore.calculateSimpleMovingAverage(secondPeriod, tickerCollection));
		AverageData averageData3 = new AverageData(thirdPeriod, simpleIndicatore.calculateSimpleMovingAverage(thirdPeriod, tickerCollection));

		return Arrays.asList(averageData, averageData2, averageData3);
	}	


}
