package buy.signal.measurements;

import java.util.List;

public class Profits {
	
	public int dayWithFirstProfit(List<Double> delta){
		
		isDeltaEmpty(delta);
		
		int counter = 0;
		
		for(int i = 0; i < delta.size(); i++, counter++){
			if(delta.get(i) > 0.0){
				break;
			}
		}
		
		return counter;		
	}
	
	public double percentageOfDaysWithProfit(List<Double> delta){
		
		isDeltaEmpty(delta);
		
		int numberOfDays = delta.size();
		int daysWithProfit = 0;
		
		for(int i = 0; i < numberOfDays; i++){
			if(delta.get(i).doubleValue() > 0.0){
				daysWithProfit++;
			}
		}
		
		return daysWithProfit / numberOfDays;		
	}
	
	private void isDeltaEmpty(List<Double> delta){
		if(delta.size() == 0){
			throw new PriceDeltaEmptyException("Delta is empty");
		}
	}
}
