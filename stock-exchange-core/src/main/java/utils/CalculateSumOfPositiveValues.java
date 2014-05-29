package utils;

import java.util.List;

import data.collector.StockExchangeIllegalStateException;

public class CalculateSumOfPositiveValues {
    public static double sumPositive(List<Double> list){
    	
    	if(list.isEmpty()){
    		throw new StockExchangeIllegalStateException("Cannot calculate sum of positive values for empty list");
    	}
    	
    	double sum = 0 ;
    	
    	for(int i=0; i<list.size();i++){
    		if(list.get(i)>=0){
    			sum = sum + list.get(i);
    		}
    	}
    	return sum;
    }

}
