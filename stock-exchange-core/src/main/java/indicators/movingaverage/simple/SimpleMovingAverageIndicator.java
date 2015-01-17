package indicators.movingaverage.simple;



/*
 * 
 * Jednym z pierwszych wskaźników stosowanych w analizie technicznej była Prosta Średnia Krocząca/Ruchoma (Simple Moving Average - SMA). 
 * Ten wskaźnik oparty jest na średniej arytmetycznej cen z określonej liczby sesji i generuje czasami bardzo dobre sygnały, jednak pod warunkiem
 * odpowiedniego ustawienia parametru średniej. Parametr ten zależy od indywidualnego charakteru zmian kursu danej akcji, jak i od sposobu inwestowania 
 * (np. krótko-, długoterminowy). Jest wiele zastosowań dla średnich ruchomych. Najczęściej używa się ich do identyfikowania trendu i jego potwierdzenia 
 * na wykresie, wyróżnienia i potwierdzenia poziomów wsparcia i oporu, bezpośredniego generowania sygnałów lub jako podstawę do tworzenia różnych wskaźników.
 * 
 */

import java.util.ArrayList;
import java.util.List;

import data.collector.EODTick;
import data.collector.StockTickerHistory;

public class SimpleMovingAverageIndicator {

    public SimpleMovingAverageIndicator() {

    }

	/*
	 * Calculate SimpleMovingAverage from given period of time for each day for a ticker. Returns List consist of a day and average value for that day from given period.
	 * 
	 * @param period - x number of days
	 * @param tickerCollection - historical data for ticker
	 * @return
	 * 
	 */
	public List<SimpleMovingAverageData> calculateSimpleMovingAverage(int period, StockTickerHistory tickerCollection){
		
		if(period < 2){
			throw new SimpleMovingAverageCalculationException("Simple moving avarage cannot be calculated if period is zero");
		}
		
		if(tickerCollection.getEODTickDataList().isEmpty()){
			throw new SimpleMovingAverageCalculationException("Simple moving average cannot be calculated for ticker without now history");
		}
		
		List<SimpleMovingAverageData> average = new ArrayList<SimpleMovingAverageData>();
		
		for(int i=0; i<tickerCollection.getEODTickDataList().size()-period+1; i++){
			
			List <EODTick> subList = tickerCollection.subListOfCollection(i, i+period);
			
			average.add(averageFromPeriod(subList));			
		}
		
		return average;
	}
	
	/*
	 * Calculate an simple moving average value for a given list. The number of days is equal the size of the given list
	 * 
	 * @param subList - a sublist for which the simple moving average need to be calculated
	 * @return
	 */
	private static SimpleMovingAverageData averageFromPeriod(List <EODTick> subList){
		
		SimpleMovingAverageData averageFromPeriod = new SimpleMovingAverageData();
		
		for(int i=0; i<subList.size();i++){
			averageFromPeriod.setAverage(averageFromPeriod.getAverage() + subList.get(i).getClose());
		}
		
		averageFromPeriod.setAverage(averageFromPeriod.getAverage()/subList.size());
		averageFromPeriod.setDate(subList.get(subList.size()-1).getDate());
		
		return averageFromPeriod;
	}

}
