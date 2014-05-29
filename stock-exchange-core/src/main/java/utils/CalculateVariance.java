package utils;

import java.util.List;

public class CalculateVariance {
    
	public static double varaince(List<Double> list){
        
		double variance = 0;
        double average = CalculateAverage.calculate(list);
 
        for (int i=0; i<list.size(); i++){
        	variance = variance + Math.pow((list.get(i) - average), 2);
        }
        
        variance = variance/list.size();
        
        return variance;
    }
}
