package statistics;

import java.util.List;

import data.collector.StockExchangeIllegalStateException;

public final class CalculateStandardDeviation {
	
	private CalculateStandardDeviation(){
		
	}

    public static double calculate(List<Double> list){
    	
    	if(list.isEmpty()){
    		throw new StockExchangeIllegalStateException("Cannot calculate standard deviation for empty list");
    	}
        
        return Math.sqrt(CalculateVariance.calculate(list));
    }
}
