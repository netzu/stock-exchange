package buy.signal.test;

public class BuySingalStatistics {
	private double sumOfProfits;
	private double sumOfPercentage;
	private double average;
	private double standardDeviation;
	private double median;
	private double variance;
	private double min;
	private double max;
	private double sumNegativeProfits;
	private double sumPositiveProfits;
	private double countNegativeProfits;
	private double countPositiveProfits;
	
	public String printStatsToString(){
		String stats = new String();
		
		stats = getSumOfProfits() + ";" + 
				getSumOfPercentage() + ";" +
				getAverage() + ";" +
				getStandardDeviation() + ";" +
				getMedian() + ";" +
				getVariance() + ";" +
				getMin() + ";" +
				getMax() + ";" +
				getSumNegativeProfits() + ";" +
				getSumPositiveProfits() + ";" +
				getCountNegativeProfits() + ";" +
				getCountPositiveProfits();
		
		return stats;
	}
	
	public String printHeaderToString(){
		String stats = new String();
		
		stats = "SumOfProfits;SumOfPercentage;Average;StandardDeviation;Median;Variance;Min;Max;"
				+ "SumNegativeProfits;SumPositiveProfits;CountNegativeProfits;CountPositiveProfits";
		
		return stats;
	}
	
	public double getSumOfProfits() {
		return sumOfProfits;
	}
	
	public void setSumOfProfits(double sumOfProfits) {
		this.sumOfProfits = sumOfProfits;
	}
	
	public double getSumOfPercentage() {
		return sumOfPercentage;
	}
	
	public void setSumOfPercentage(double sumOfPercentage) {
		this.sumOfPercentage = sumOfPercentage;
	}
	
	public double getAverage() {
		return average;
	}
	
	public void setAverage(double average) {
		this.average = average;
	}
	
	public double getStandardDeviation() {
		return standardDeviation;
	}
	
	public void setStandardDeviation(double standardDeviation) {
		this.standardDeviation = standardDeviation;
	}
	
	public double getMedian() {
		return median;
	}
	
	public void setMedian(double median) {
		this.median = median;
	}
	
	public double getVariance() {
		return variance;
	}
	
	public void setVariance(double variance) {
		this.variance = variance;
	}
	
	public double getMin() {
		return min;
	}
	
	public void setMin(double min) {
		this.min = min;
	}
	
	public double getMax() {
		return max;
	}
	
	public void setMax(double max) {
		this.max = max;
	}
	
	public double getSumNegativeProfits() {
		return sumNegativeProfits;
	}
	
	public void setSumNegativeProfits(double sumNegativeProfits) {
		this.sumNegativeProfits = sumNegativeProfits;
	}
	
	public double getSumPositiveProfits() {
		return sumPositiveProfits;
	}
	
	public void setSumPositiveProfits(double sumPositiveProfits) {
		this.sumPositiveProfits = sumPositiveProfits;
	}
	
	public double getCountNegativeProfits() {
		return countNegativeProfits;
	}
	
	public void setCountNegativeProfits(double countNegativeProfits) {
		this.countNegativeProfits = countNegativeProfits;
	}
	
	public double getCountPositiveProfits() {
		return countPositiveProfits;
	}
	
	public void setCountPositiveProfits(double countPositiveProfits) {
		this.countPositiveProfits = countPositiveProfits;
	}
	
	
}
