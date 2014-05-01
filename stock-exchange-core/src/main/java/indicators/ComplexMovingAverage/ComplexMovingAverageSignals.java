package indicators.ComplexMovingAverage;

import indicators.simpleMovingAverage.AverageData;
import indicators.simpleMovingAverage.SimpleMovingAverageSignals;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import data.collector.StockTickerHistory;

public class ComplexMovingAverageSignals {
	
	private static org.apache.log4j.Logger log = Logger.getLogger(ComplexMovingAverageSignals.class);

	private final SimpleMovingAverageSignals simpleMovingAverageSignals;

	public ComplexMovingAverageSignals(final SimpleMovingAverageSignals simpleMovingAverageSignals) {
		this.simpleMovingAverageSignals = simpleMovingAverageSignals;
	}
	
	public List<DateTime> generateBuySignals(final List<AverageData> averageData, StockTickerHistory stockCollection) {
		
		ArrayList<DateTime> simpleBuy1 = this.simpleMovingAverageSignals.simpleBuy(averageData.get(0).getAverageData(), stockCollection);
		ArrayList<DateTime> simpleBuy2 = this.simpleMovingAverageSignals.simpleBuy(averageData.get(1).getAverageData(), stockCollection);
		ArrayList<DateTime> simpleBuy3 = this.simpleMovingAverageSignals.simpleBuy(averageData.get(2).getAverageData(), stockCollection);
	
		final List<DateTime> result = new ArrayList<DateTime>();
		
		for(DateTime t : simpleBuy1) {
			if (simpleBuy2.contains(t) && simpleBuy3.contains(t)) {
				result.add(t);
			}
		}
		
		return result;
	}
	
	public List<DateTime> generatSellSignals(final List<AverageData> averageData, StockTickerHistory stockCollection) {
		
		ArrayList<DateTime> simpleSell1 = this.simpleMovingAverageSignals.simpleSell(averageData.get(0).getAverageData(), stockCollection);
		ArrayList<DateTime> simpleSell2 = this.simpleMovingAverageSignals.simpleSell(averageData.get(1).getAverageData(), stockCollection);
		ArrayList<DateTime> simpleSell3 = this.simpleMovingAverageSignals.simpleSell(averageData.get(2).getAverageData(), stockCollection);
	
		final List<DateTime> result = new ArrayList<DateTime>();
		
		for(DateTime t : simpleSell1) {
			if (simpleSell2.contains(t) && simpleSell3.contains(t)) {
				result.add(t);
			}
		}
		
		return result;
	}
	
	

}
