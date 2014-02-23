package indicators.simpleMovingAverage;

import java.util.ArrayList;

import data.collector.StockTickerHistory;

public class ComplexMovingAverageIndicator {
	ArrayList<SimpleMovingAverageData> firstMovingAverage = new ArrayList<SimpleMovingAverageData>();
	ArrayList<SimpleMovingAverageData> secondMovingAverage = new ArrayList<SimpleMovingAverageData>();
	ArrayList<SimpleMovingAverageData> thirdMovingAverage = new ArrayList<SimpleMovingAverageData>();
	
	public void calculateComplexMovingAverage(int firstPeriod, int secondPeriod, int thirdPeriod, StockTickerHistory tickerCollection){
		SimpleMovingAverageIndicator firstAverageIndicator = new SimpleMovingAverageIndicator();
		SimpleMovingAverageIndicator secondAverageIndicator = new SimpleMovingAverageIndicator();
		SimpleMovingAverageIndicator thirdAverageIndicator = new SimpleMovingAverageIndicator();
		
		firstAverageIndicator.calculateSimpleMovingAverage(firstPeriod, tickerCollection);
		secondAverageIndicator.calculateSimpleMovingAverage(secondPeriod, tickerCollection);
		thirdAverageIndicator.calculateSimpleMovingAverage(thirdPeriod, tickerCollection);
		
		firstMovingAverage = firstAverageIndicator.getAverage();
		secondMovingAverage = secondAverageIndicator.getAverage();
		thirdMovingAverage = thirdAverageIndicator.getAverage();		
	}	
	
	public ArrayList<SimpleMovingAverageData> getFirstMovingAverage() {
		return firstMovingAverage;
	}

	public void setFirstMovingAverage(
			ArrayList<SimpleMovingAverageData> firstMovingAverage) {
		this.firstMovingAverage = firstMovingAverage;
	}

	public ArrayList<SimpleMovingAverageData> getSecondMovingAverage() {
		return secondMovingAverage;
	}

	public void setSecondMovingAverage(
			ArrayList<SimpleMovingAverageData> secondMovingAverage) {
		this.secondMovingAverage = secondMovingAverage;
	}

	public ArrayList<SimpleMovingAverageData> getThirdMovingAverage() {
		return thirdMovingAverage;
	}

	public void setThirdMovingAverage(
			ArrayList<SimpleMovingAverageData> thirdMovingAverage) {
		this.thirdMovingAverage = thirdMovingAverage;
	}

}
