package indicators.movingaverage.simple;

public class DecissionChainFactory {
	
	public DecisionChain createChanForSell(double currentClose, double previousAverage, double currentAverage, double previousClose) {
		DecisionChain dc = new DecisionChain();
		
		Smaller currentCloseSmallerPreviousClose = new Smaller(currentClose, previousAverage);
		SmallerOrEqual currentAverageSmallerOrEqualPreviousAverage = new SmallerOrEqual(currentAverage, previousAverage);
		Smaller currentCloseSmallerPreviousAverage = new Smaller(currentClose, previousAverage);
		Smaller previousAverageSmallerPreviousClose = new Smaller(previousAverage, previousClose);
		Smaller currentCloseSmallerCurrentAverage = new Smaller(currentClose, currentAverage);
		Smaller currentAverageSmallerPreviousClose = new Smaller(currentAverage, previousClose);
		
		dc.add(currentCloseSmallerPreviousClose);
		dc.add(currentAverageSmallerOrEqualPreviousAverage);
		dc.add(currentCloseSmallerPreviousAverage);
		dc.add(previousAverageSmallerPreviousClose);
		dc.add(currentCloseSmallerCurrentAverage);
		dc.add(currentAverageSmallerPreviousClose);
		
		return dc;
	}
	
	public DecisionChain createChanForBuy(double currentClose, double previousAverage, double currentAverage, double previousClose) {
		DecisionChain dc = new DecisionChain();
		
		Smaller previousCloseSmallerThanCurrentClose = new Smaller(previousClose, currentClose);
		SmallerOrEqual previousAverageSmallerOrEqualCurrentAverage = new SmallerOrEqual(previousAverage, currentAverage);
		Smaller previousCloseSmallerPreviousAverage = new Smaller(previousClose, previousAverage);
		Smaller previousAverageSmallerCurrentClose = new Smaller(previousAverage, currentClose);
		Smaller previousCloseSmallerCurrentAverage = new Smaller(previousClose, currentAverage);
		Smaller currentAverageSmallerCurrentClose = new Smaller(currentAverage, currentClose);
		
		dc.add(previousCloseSmallerThanCurrentClose);
		dc.add(previousAverageSmallerOrEqualCurrentAverage);
		dc.add(previousCloseSmallerPreviousAverage);
		dc.add(previousAverageSmallerCurrentClose);
		dc.add(previousCloseSmallerCurrentAverage);
		dc.add(currentAverageSmallerCurrentClose);
		
		return dc;
	}
}
