package indicators.simpleMovingAverage;

import org.joda.time.DateTime;

public class SimpleMovingAverageData {
	
	private DateTime date;
	private double average;
	
	public DateTime getDate() {
		return date;
	}
	
	public void setDate(DateTime date) {
		this.date = date;
	}
	
	public double getAverage() {
		return average;
	}
	
	public void setAverage(double average) {
		this.average = average;
	}
	
}
