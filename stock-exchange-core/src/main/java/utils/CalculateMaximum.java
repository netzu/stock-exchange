package utils;

import java.util.List;

import data.collector.StockExchangeIllegalStateException;

public class CalculateMaximum{
	
    public static double calculate(List<Double> list){
    	
    	if(list.isEmpty()){
			throw new StockExchangeIllegalStateException("Cannot calculate maximum value from empty list");
		}
    	
    	double max = list.get(0);
    	
    	for(int i=1; i<list.size(); i++){
    		if(list.get(i)>max){
    			max = list.get(i);
    		}
    	}
    	return max;
    }

}
