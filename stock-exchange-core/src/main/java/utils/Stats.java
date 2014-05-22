package utils;

import java.util.Collections;
import java.util.List;

public class Stats {

    public static double sum (List<Double> list){
        if (list.size() > 0) {
            double sum = 0;
 
            for (int i=0; i<list.size(); i++) {
                sum = sum + list.get(i);
            }
            return sum;
        }
        return 0;
    }
    
    
    public static double average (List<Double> list){
        double sum = sum(list);
        double average = sum/list.size();

        return average;
    }
    
    
    public static double median (List<Double> list){

    	if (list.size() == 1) {
    		return list.get(0);
    	}
    	
    	if(list.size() == 0) {
    		System.out.println("Brak mediany");
    		return 0;
    	}
    	
    	Collections.sort(list);
    	
        int middle = list.size()/2;
        
 
        if (list.size() % 2 == 1) {
            return list.get(middle);
        } else {
           return (list.get(middle-1) + list.get(middle)) / 2.0;
        }
    }
    
    public static double varaince(List<Double> list){
        double variance = 0;
        double average = average(list);
 
        for (int i=0; i<list.size(); i++){
        	variance = variance + Math.pow((list.get(i) - average), 2);
        }
        
        variance = variance/list.size();
        
        return variance;
    }
    
    public static double standardDev (List<Double> list){
        double variance = varaince(list);
        
        double deviation = Math.sqrt(variance);
        
        return deviation;
    }
    
    public static double min(List<Double> list){
    	double min = list.get(0);
    	
    	for(int i=1; i<list.size(); i++){
    		if(list.get(i)<min){
    			min = list.get(i);
    		}
    	}
    	return min;
    }
    
    public static double max(List<Double> list){
    	double max = list.get(0);
    	
    	for(int i=1; i<list.size(); i++){
    		if(list.get(i)>max){
    			max = list.get(i);
    		}
    	}
    	return max;
    }
    
    public static int count(List<Double> list){
    	return list.size();
    }
    
    public static int countPositive(List<Double> list){
    	int counter = 0 ;
    	
    	for(int i=0; i<list.size();i++){
    		if(list.get(i)>=0){
    			counter++;
    		}
    	}
    	return counter;
    }
    
    public static int countNegative(List<Double> list){
    	int counter = 0 ;
    	
    	for(int i=0; i<list.size();i++){
    		if(list.get(i)<0){
    			counter++;
    		}
    	}
    	return counter;
    }
    
    public static double sumPositive(List<Double> list){
    	double sum = 0 ;
    	
    	for(int i=0; i<list.size();i++){
    		if(list.get(i)>=0){
    			sum = sum + list.get(i);
    		}
    	}
    	return sum;
    }
    
    public static double sumNegative(List<Double> list){
    	double sum = 0 ;
    	
    	for(int i=0; i<list.size();i++){
    		if(list.get(i)<0){
    			sum = sum + list.get(i);
    		}
    	}
    	return sum;
    }
}
