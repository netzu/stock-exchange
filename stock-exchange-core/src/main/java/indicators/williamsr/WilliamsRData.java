package indicators.williamsr;

import org.joda.time.DateTime;

class WilliamsRData {
	 private DateTime date;
	 private double williamsR;
	 
	DateTime getDate() {
		return date;
	}
	void setDate(DateTime date) {
		this.date = date;
	}
	double getWilliamsR() {
		return williamsR;
	}
	void setWilliamsR(double williamsR) {
		this.williamsR = williamsR;
	}
}
