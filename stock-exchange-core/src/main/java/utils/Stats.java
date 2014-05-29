package utils;

import java.util.List;

public class Stats {     
  

    
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
