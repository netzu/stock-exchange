package indicators.movingaverage.simple;

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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(averageValueFromGivenPeriod);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleMovingAverageData other = (SimpleMovingAverageData) obj;
		if (Double.doubleToLongBits(averageValueFromGivenPeriod) != Double
				.doubleToLongBits(other.averageValueFromGivenPeriod))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}
}
