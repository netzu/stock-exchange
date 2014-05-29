package statistics;

import java.util.List;

import data.collector.StockExchangeIllegalStateException;

public final class CalculateCountOfNegativeValues {
	
	private CalculateCountOfNegativeValues(){
		
	}
	
    public static int calculate(List<Double> list){
    	
		if(list.isEmpty()){
			throw new StockExchangeIllegalStateException("Cannot calculate count of negative values from empty list");
		}
		
    	int counter = 0 ;
    	
    	for(int i=0; i<list.size();i++){
    		if(list.get(i)<0){
    			counter++;
    		}
    	}
    	return counter;
    }

}
