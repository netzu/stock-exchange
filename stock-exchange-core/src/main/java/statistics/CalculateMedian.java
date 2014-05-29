package statistics;

import java.util.Collections;
import java.util.List;

import data.collector.StockExchangeIllegalStateException;

public class CalculateMedian {
    public static double calculate(List<Double> list){

    	if (list.size() == 1) {
    		return list.get(0);
    	}
    	
    	if(list.isEmpty()) {
    		throw new StockExchangeIllegalStateException("Cannot calculate median for empty list");
    	}
    	
    	Collections.sort(list);
    	
        int middle = list.size()/2;
        
 
        if (list.size() % 2 == 1) {
            return list.get(middle);
        } else {
           return (list.get(middle-1) + list.get(middle)) / 2.0;
        }
    }
}
