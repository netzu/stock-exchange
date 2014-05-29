package utils;

import java.util.List;

import data.collector.StockExchangeIllegalStateException;

public class CalculateSum {
    
	public static double calculate(List<Double> list){
        
		if (list.size() > 0) {
            double sum = 0;
 
            for (int i=0; i<list.size(); i++) {
                sum = sum + list.get(i);
            }
            return sum;
        }
        
		throw new StockExchangeIllegalStateException("Cannot calculate sum for empty list");
    }
}
