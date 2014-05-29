package statistics;

import java.util.List;

import data.collector.StockExchangeIllegalStateException;

public final class CalculateAverage {

	private CalculateAverage(){
		
	}

	public static double calculate(List<Double> list){

		if(list.isEmpty()){
			throw new StockExchangeIllegalStateException("Cannot calculate average for empty list");
		}

		return CalculateSum.calculate(list) / list.size() ;
    }
}
