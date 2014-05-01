package buySignalTest;

public class ProfitsFromSignal {
	double profit;
	double purchasePrice;
	double profitInPercentage;
	
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
