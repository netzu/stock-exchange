package statistics;

import java.util.List;

import data.collector.StockExchangeIllegalStateException;

public class CalculateVariance {
    
	public static double calculate(List<Double> list){
		
		if(list.isEmpty()){
			throw new StockExchangeIllegalStateException("Cannot calculate variance for empty list");
		}
        
		double variance = 0;
        double average = CalculateAverage.calculate(list);
 
        for (int i=0; i<list.size(); i++){
        	variance = variance + Math.pow((list.get(i) - average), 2);
        }
        
        variance = variance/list.size();
        
        return variance;
    }

}
