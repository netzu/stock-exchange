package buy.signal.test;

public class ProfitsFromSignal {
	private double profit;
	private double purchasePrice;
	private double profitInPercentage;
	
	public double getProfit() {
		return profit;
	}
	
	public void setProfit(double profit) {
		this.profit = profit;
	}
	
	public double getProfitInPercentage() {
		return profitInPercentage;
	}
	
	public void setProfitInPercentage(double percentage) {
		this.profitInPercentage = percentage;
	}
	
	public double calculateProfitInPercentage(double currentClose){
		return (currentClose-purchasePrice)/purchasePrice;
	}
	
	
}
