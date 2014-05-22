package indicators.moving_average.simple;

import org.joda.time.DateTime;

public class SimpleMovingAverageData {
	
	private DateTime date;
	private double averageValueFromGivenPeriod;
	
	public DateTime getDate() {
		return date;
	}
	
	public void setDate(DateTime date) {
		this.date = date;
	}
	
	public double getAverage() {
		return averageValueFromGivenPeriod;
	}
	
	public void setAverage(double average) {
		this.averageValueFromGivenPeriod = average;
	}
	
}
