package indicators.williamsr;

import org.joda.time.DateTime;

public class WilliamsRData {	 
	 private DateTime date;
	 private double williamsR;
	 
	public DateTime getDate() {
		return date;
	}
	public void setDate(DateTime date) {
		this.date = date;
	}
	public double getWilliamsR() {
		return williamsR;
	}
	public void setWilliamsR(double williamsR) {
		this.williamsR = williamsR;
	}
}
