package indicators.ComplexMovingAverage;

import indicators.simpleMovingAverage.SimpleMovingAverageData;

import java.util.List;

public class AverageData {
	
	private final int period;
	private final List<SimpleMovingAverageData> averageData;
	
	public AverageData(final int period, final List<SimpleMovingAverageData> averageData) {
		this.period = period;
		this.averageData = averageData;
	}

	public int getPeriod() {
		return period;
	}

	public List<SimpleMovingAverageData> getAverageData() {
		return averageData;
	}

}
