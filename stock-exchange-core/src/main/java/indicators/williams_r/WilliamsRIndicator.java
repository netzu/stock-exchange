package indicators.williams_r;

import java.util.ArrayList;

import org.joda.time.DateTime;

import data.collector.StockTicker;
import data.collector.StockTickerHistory;

/*
 Opracowany przez Larry'ego Williamsa wskaźnik przypomina oscylator stochastyczny. Pokazuje, jak bardzo obecna cena odbiega od poziomów zamknięcia z ostatnich N dni. 
 Oscylator ten mieści się w granicach od -100 do 0. Poziom -100 oznacza, że obecna cena jest najniższa spośród wszystkich cen z badanego okresu. Analogicznie poziom 
 0 oznacza, że obecna cena jest najwyższą z N sesji.

 Po ustaleniu poziomu wykupienia (-20 do 0) oraz poziomu wyprzedania (-80 do -100) można upatrywać zmiany trendu na rynku, jeśli notowania danego instrumentu 
 wpadają w omawiane obszary.

 Sygnał kupna zostaje wygenerowany, jeśli wskaźnik wpadnie a następnie wzrośnie ponad poziom wyprzedania, natomiast sygnał sprzedaży kiedy wchodzi do strefy 
 wykupienia a następnie spada poniżej poziomu wykupienia.

 Szczególnie dobrze zachowuje się, kiedy notowania danego instrumentu poruszają się w trendzie horyzontalnym z dużymi wahaniami cen. Należy jednak wziąć pod uwagę sytuację,
 kiedy stan wykupienia/wyprzedania rynku może się utrzymywać przez dłuższy okres, dlatego zaleca się używanie innych narzędzi analizy technicznej razem z oscylatorem %R Williamsa.

 Williams używał wskaźnika opartego na 10 dniach, wartości wskaźnika poniżej -80 uważał za wyprzedanie, wartości powyżej -20 wykupienie.
 */


public class WilliamsRIndicator {

	private double getHighestHighFromPeriod(StockTickerHistory subCollection){
		
		double highest = subCollection.getStockTickerDataList().get(0).getHigh();
		double temporaryHighest  = subCollection.getStockTickerDataList().get(0).getHigh();
		
		for(int i = 0; i<subCollection.getStockTickerDataList().size(); i++){
			
			temporaryHighest = subCollection.getStockTickerDataList().get(i).getHigh();
			
			if(highest<temporaryHighest){
				highest = temporaryHighest; 
			}
		}
		
		return highest;
	}
	
	private double getLowestLowFromPeriod(StockTickerHistory subCollection){
		
		double lowest = subCollection.getStockTickerDataList().get(0).getLow();
		double temporaryLowest = subCollection.getStockTickerDataList().get(0).getLow();
		
		for(int i = 0; i<subCollection.getStockTickerDataList().size(); i++){
			
			temporaryLowest = subCollection.getStockTickerDataList().get(i).getLow();
			
			if(lowest > temporaryLowest){
				lowest = temporaryLowest; 
			}
		}
		
		return lowest;
	}
	
	private WilliamsRData calculateSinglewilliamsR(StockTickerHistory subCollection){
		WilliamsRData data = new WilliamsRData();
		
		double highest = getHighestHighFromPeriod(subCollection);
		double lowest = getLowestLowFromPeriod(subCollection);
		
		int period = subCollection.getStockTickerDataList().size()-1;
		
		DateTime date = subCollection.getStockTickerDataList().get(period).getDate();
		double curentClose = subCollection.getStockTickerDataList().get(period).getClose();
		
		data.setDate(date);
		data.setWilliamsR(equationForWilliamsPercentage(highest, curentClose, lowest));
		
		return data;		
	}
	
	public ArrayList<WilliamsRData> calculateWilliamsR(int period, StockTickerHistory tickercollection) {
		
		if(tickercollection.getStockTickerDataList().size()==0){
			throw new WilliamsRCalculationException("Ticker's history data is empty");
		}
		
		if(period<1){
			throw new WilliamsRCalculationException("Period must be grather than 0");
		}
		
		if(tickercollection.getStockTickerDataList().size()<period){
			throw new WilliamsRCalculationException("Size of tickerCollection lowere than period");
		}
		
		StockTickerHistory subListFromGivenPeriod = new StockTickerHistory();
		ArrayList<WilliamsRData> williamsR = new ArrayList<WilliamsRData>();
		
		for (int i = 0; i<(tickercollection.getStockTickerDataList().size() - period +1); i++){
			ArrayList <StockTicker> subList = tickercollection.subListOfCollection(i, i + period);
			subListFromGivenPeriod.setStockTickerDataList(subList);
			
			williamsR.add(calculateSinglewilliamsR(subListFromGivenPeriod));
		}
		
		return williamsR;
	}
	
	private double equationForWilliamsPercentage(double highestHigh, double currentClose, double lowestLow){
		double williamsR; 
		
		if(highestHigh==lowestLow){
			williamsR = 0.0;
		}else{		
			williamsR = ((highestHigh-currentClose)/(highestHigh - lowestLow)) * -100;
		}
		
		return williamsR;
	
	}
}
