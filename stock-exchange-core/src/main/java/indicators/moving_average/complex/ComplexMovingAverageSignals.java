package indicators.moving_average.complex;

import indicators.moving_average.simple.SimpleMovingAverageSignals;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import data.collector.StockTickerHistory;

public class ComplexMovingAverageSignals {
	
	public ArrayList<DateTime> buysignal(final List<AverageData> averageData,  SimpleMovingAverageSignals simpleMovingAverageSignals, StockTickerHistory stockCollection) {
		
		List<DateTime> simpleBuy1 = simpleMovingAverageSignals.buySignal(averageData.get(0).getAverageData(), stockCollection);
		List<DateTime> simpleBuy2 = simpleMovingAverageSignals.buySignal(averageData.get(1).getAverageData(), stockCollection);
		List<DateTime> simpleBuy3 = simpleMovingAverageSignals.buySignal(averageData.get(2).getAverageData(), stockCollection);
	
		final ArrayList<DateTime> result = new ArrayList<DateTime>();
		
		for(DateTime t : simpleBuy1) {
			if (simpleBuy2.contains(t) && simpleBuy3.contains(t)) {
				result.add(t);
			}
		}
		
		return result;
	}
	
	public ArrayList<DateTime> generatSellSignals(final List<AverageData> averageData, SimpleMovingAverageSignals simpleMovingAverageSignals, StockTickerHistory stockCollection) {
		
		ArrayList<DateTime> simpleSell1 = simpleMovingAverageSignals.simpleSell(averageData.get(0).getAverageData(), stockCollection);
		ArrayList<DateTime> simpleSell2 = simpleMovingAverageSignals.simpleSell(averageData.get(1).getAverageData(), stockCollection);
		ArrayList<DateTime> simpleSell3 = simpleMovingAverageSignals.simpleSell(averageData.get(2).getAverageData(), stockCollection);
	
		final ArrayList<DateTime> result = new ArrayList<DateTime>();
		
		for(DateTime t : simpleSell1) {
			if (simpleSell2.contains(t) && simpleSell3.contains(t)) {
				result.add(t);
			}
		}
		
		return result;
	}
	
	

}
