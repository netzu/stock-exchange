package buy.signal.measurements;

import java.util.List;

import com.google.common.base.Optional;

public class ProfitsAnalyser {
	
	
	/**
	 * Method checks if there was a profit in given period and returns a number of day in which first profit occcured
	 * 
	 * @param delta
	 * @return number of the day in which the first profit occured
	 */
	public Optional<Integer> getDayNumberWithFirstProfit(List<Double> delta){
		
		isDeltaEmpty(delta);		
		
		for(int i = 0; i < delta.size(); i++){
			if(delta.get(i) > 0.0){
				return Optional.of(i+1);
			}
		}
		return Optional.absent();
	}
	
	public double percentageOfDaysWithProfits(List<Double> delta){
		
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
