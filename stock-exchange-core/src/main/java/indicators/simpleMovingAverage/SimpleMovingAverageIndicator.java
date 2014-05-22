package indicators.simpleMovingAverage;



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

import org.apache.log4j.Logger;

import data.collector.StockTicker;
import data.collector.StockTickerHistory;

public class SimpleMovingAverageIndicator {	
	
	/*
	 * Calculate SimpleMovingAverage from given period of time for each day for a ticker. Returns List consist of a day and average value for that day from given period.
	 * 
	 * @param period - x number of days
	 * @param tickerCollection - historical data for ticker
	 * @return
	 * 
	 */
	public static ArrayList<SimpleMovingAverageData> calculateSimpleMovingAverage(int period, StockTickerHistory tickerCollection){
		
		ArrayList<SimpleMovingAverageData> average = new ArrayList<SimpleMovingAverageData>();
		
		for(int i=0; i<tickerCollection.getStockTickerDataList().size()-period+1; i++){
			
			ArrayList <StockTicker> subList = new ArrayList<StockTicker>();
			subList = tickerCollection.subListOfCollection(i, i+period);
			
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
	private static SimpleMovingAverageData averageFromPeriod(ArrayList <StockTicker> subList){
		
		SimpleMovingAverageData averageFromPeriod = new SimpleMovingAverageData();
		
		for(int i=0; i<subList.size();i++){
			averageFromPeriod.setAverage(averageFromPeriod.getAverage() + subList.get(i).getClose());
		}
		
		averageFromPeriod.setAverage(averageFromPeriod.getAverage()/subList.size());
		averageFromPeriod.setDate(subList.get(subList.size()-1).getDate());
		
		return averageFromPeriod;
	}

}
