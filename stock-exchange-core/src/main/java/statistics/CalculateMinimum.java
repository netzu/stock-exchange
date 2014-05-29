package statistics;

import java.util.List;

import data.collector.StockExchangeIllegalStateException;

public final class CalculateMinimum {
	
	private CalculateMinimum(){
		
	}
    
	public static double calculate(List<Double> list){
		
		if(list.isEmpty()){
			throw new StockExchangeIllegalStateException("Cannot calculate minimum value from empty list");
		}
		
    	double min = list.get(0);
    	
    	for(int i=1; i<list.size(); i++){
    		if(list.get(i)<min){
    			min = list.get(i);
    		}
    	}
    	return min;
    }
}
