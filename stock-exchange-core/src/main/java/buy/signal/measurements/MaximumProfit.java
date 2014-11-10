package buy.signal.measurements;

import java.util.List;

public class MaximumProfit {

	public double calulateInValue(List<Double> delta){		
		
		if(!wasThereProfit(delta)){
			throw new ProfitException("There was no profit fot this signal");		
		}
		
		return findMaxProfit(delta);			
	}
	
	private boolean wasThereProfit(List<Double> delta){
		
		if( findMaxProfit(delta) > 0.0 ){
			return true;
		}
		
		return false;
	}

	private double findMaxProfit(List<Double> delta) {
		
		if(delta.size() == 0){
			throw new PriceDeltaEmptyException("Delta is empty");
		}
		
		double temp = delta.get(0).doubleValue(); 
		
		for(int i = 0; i < delta.size(); i++){
			if(temp < delta.get(i).doubleValue()){
				temp = delta.get(i).doubleValue();
			}
		}
		
		return temp;
	}
}
