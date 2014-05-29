package utils;

import java.util.List;

public class Stats {    

    
   
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
