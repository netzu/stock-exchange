package utils;

import indicators.movingaverage.simple.SimpleMovingAverageData;

import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class CompareSimpleMovingAverageLists {
	
	static DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");	
	
	public static boolean compare(List<SimpleMovingAverageData> expectedResults, List<SimpleMovingAverageData> currentResults) {
		try{
			for(int i=0; i<expectedResults.size(); i++){
				 if(!Precision.equalsIncludingNaN(expectedResults.get(i).getAverage(), currentResults.get(i).getAverage(), 0.01)){
					 return false;
				 }
				 if(!expectedResults.get(i).getDate().equals(currentResults.get(i).getDate())){
					 return false;
				 }
			 }
		}catch(Exception ex){
			return false;
		}
		return true;
	}
	
	public static boolean compare(List<SimpleMovingAverageData> expectedResults, List<SimpleMovingAverageData> currentResults, double precision) {
		try{
			for(int i=0; i<expectedResults.size(); i++){
				 if(!Precision.equalsIncludingNaN(expectedResults.get(i).getAverage(), currentResults.get(i).getAverage(), precision)){
					 return false;
				 }
				 if(!expectedResults.get(i).getDate().equals(currentResults.get(i).getDate())){
					 return false;
				 }
			 }
		}catch(Exception ex){
			return false;
		}
		return true;
	}
}