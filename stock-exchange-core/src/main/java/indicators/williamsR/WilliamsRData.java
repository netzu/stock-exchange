package indicators.williamsR;

import org.joda.time.DateTime;

public class WilliamsRData {
	 private double highestHigh; 
	 private double lowestLow;
	 private double currentClose;
	 
	 private DateTime date;
	 private double williamsR;
	 

	private void EquationForWilliamsPercentage(){
		williamsR = ((highestHigh-currentClose)/(highestHigh - lowestLow)) * -100;
	}
	
	public void CallculateWilliamsRValue(){
		EquationForWilliamsPercentage();
	}
	 
	public double getHighestHigh() {
		return highestHigh;
	}
	public void setHighestHigh(double highestHigh) {
		this.highestHigh = highestHigh;
	}
	public double getLowestLow() {
		return lowestLow;
	}
	public void setLowestLow(double lowestLow) {
		this.lowestLow = lowestLow;
	}
	public double getCurrentClose() {
		return currentClose;
	}
	public void setCurrentClose(double currentClose) {
		this.currentClose = currentClose;
	}
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
