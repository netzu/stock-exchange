package indicators.movingaverage.complex;

import indicators.movingaverage.simple.SimpleMovingAverageSignals;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import data.collector.StockTickerHistory;

public class ComplexMovingAverageSignals {
	
	public List<DateTime> buysignal(final List<ComplexMovingAverageAverageData> averageData,  SimpleMovingAverageSignals simpleMovingAverageSignals, StockTickerHistory stockCollection) {
		
		List<DateTime> simpleBuy1 = simpleMovingAverageSignals.getBuySignal(averageData.get(0).getAverageData(), stockCollection);
		List<DateTime> simpleBuy2 = simpleMovingAverageSignals.getBuySignal(averageData.get(1).getAverageData(), stockCollection);
		List<DateTime> simpleBuy3 = simpleMovingAverageSignals.getBuySignal(averageData.get(2).getAverageData(), stockCollection);
	
		final List<DateTime> result = new ArrayList<DateTime>();
		
		for(DateTime t : simpleBuy1) {
			if (simpleBuy2.contains(t) && simpleBuy3.contains(t)) {
				result.add(t);
			}
		}
		
		return result;
	}
	
	public List<DateTime> generatSellSignals(final List<ComplexMovingAverageAverageData> averageData, SimpleMovingAverageSignals simpleMovingAverageSignals, StockTickerHistory stockCollection) {
		
		List<DateTime> simpleSell1 = simpleMovingAverageSignals.getSellSignals(averageData.get(0).getAverageData(), stockCollection);
		List<DateTime> simpleSell2 = simpleMovingAverageSignals.getSellSignals(averageData.get(1).getAverageData(), stockCollection);
		List<DateTime> simpleSell3 = simpleMovingAverageSignals.getSellSignals(averageData.get(2).getAverageData(), stockCollection);
	
		final List<DateTime> result = new ArrayList<DateTime>();
		
		for(DateTime t : simpleSell1) {
			if (simpleSell2.contains(t) && simpleSell3.contains(t)) {
				result.add(t);
			}
		}
		
		return result;
	}
	
	

}
