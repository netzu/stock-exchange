package statistics;

import java.util.List;

import data.collector.StockExchangeIllegalStateException;

public final class CalculateSumOfNegativeValues {
	
	private CalculateSumOfNegativeValues(){
		
	}

    public static double calculate(List<Double> list){
    	
    	if(list.isEmpty()){
			throw new StockExchangeIllegalStateException("Cannot calculate sum of negative values from empty list");
		}
		
    	double sum = 0 ;
    	
    	for(int i=0; i<list.size();i++){
    		if(list.get(i)<0){
    			sum = sum + list.get(i);
    		}
    	}
    	return sum;
    }
}
