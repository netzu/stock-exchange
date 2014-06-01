package indicators.movingaverage.complex;

import indicators.movingaverage.simple.SimpleMovingAverageIndicator;

import java.util.Arrays;
import java.util.List;

import data.collector.StockTickerHistory;

public class CompexMovingAverageIndicator {
	
	public List<ComplexMovingAverageAverageData> calculateComplexMovingAverage(int firstPeriod, int secondPeriod, int thirdPeriod, StockTickerHistory tickerCollection){

		SimpleMovingAverageIndicator simpleIndicatore = new SimpleMovingAverageIndicator();
		
		ComplexMovingAverageAverageData averageData = new ComplexMovingAverageAverageData(firstPeriod, simpleIndicatore.calculateSimpleMovingAverage(firstPeriod, tickerCollection));
		ComplexMovingAverageAverageData averageData2 = new ComplexMovingAverageAverageData(secondPeriod, simpleIndicatore.calculateSimpleMovingAverage(secondPeriod, tickerCollection));
		ComplexMovingAverageAverageData averageData3 = new ComplexMovingAverageAverageData(thirdPeriod, simpleIndicatore.calculateSimpleMovingAverage(thirdPeriod, tickerCollection));

		return Arrays.asList(averageData, averageData2, averageData3);
	}	


}
