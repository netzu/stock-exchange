package indicators.williamsR;

import java.util.ArrayList;

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

 Szczególnie dobrze zachowuje się, kiedy notowania danego instrumentu poruszają się w trendzie horyzontalnym z dużymi wahaniami cen. Należy jednak wziąć pod uwagę sytuację, kiedy stan wykupienia/wyprzedania rynku może się utrzymywać przez dłuższy okres, dlatego zaleca się używanie innych narzędzi analizy technicznej razem z oscylatorem %R Williamsa.

 Williams używał wskaźnika opartego na 10 dniach, wartości wskaźnika poniżej -80 uważał za wyprzedanie, wartości powyżej -20 wykupienie.
 */


public class WilliamsRIndicator {
	
	private WilliamsRCollectionForTicker williamsR;
	
	public WilliamsRIndicator() {
		this.williamsR = new WilliamsRCollectionForTicker();
	}
	
	public WilliamsRCollectionForTicker getWilliamsRCollectionForTicker(){
		return this.williamsR;
	}

	private double GetHighestHighFromPeriod(StockTickerHistory stockCollection){
		
		double highest = stockCollection.getStockTickerDataList().get(0).getHigh();
		double temporaryHighest  = stockCollection.getStockTickerDataList().get(0).getHigh();
		
		for(int i = 0; i<stockCollection.getStockTickerDataList().size(); i++){
			
			temporaryHighest = stockCollection.getStockTickerDataList().get(i).getHigh();
			
			if(highest<temporaryHighest){
				highest = temporaryHighest; 
			}
		}
		
		return highest;
	}
	
	private double GetLowestLowFromPeriod(StockTickerHistory stockCollection){
		
		double lowest = stockCollection.getStockTickerDataList().get(0).getLow();
		double temporaryLowest = stockCollection.getStockTickerDataList().get(0).getLow();
		
		for(int i = 0; i<stockCollection.getStockTickerDataList().size(); i++){
			
			temporaryLowest = stockCollection.getStockTickerDataList().get(i).getLow();
			
			if(lowest > temporaryLowest){
				lowest = temporaryLowest; 
			}
		}
		
		return lowest;
	}
	

	
	private void addNewWilliamsRData(StockTickerHistory stockCollection){
		WilliamsRData data = new WilliamsRData();
		
		data.setHighestHigh(GetHighestHighFromPeriod(stockCollection));
		data.setLowestLow(GetLowestLowFromPeriod(stockCollection));
		
		int period = stockCollection.getStockTickerDataList().size()-1;
		
		data.setDate(stockCollection.getStockTickerDataList().get(period).getDate());
		data.setCurrentClose(stockCollection.getStockTickerDataList().get(period).getClose());
		
		data.CallculateWilliamsRValue();
		
		williamsR.addNewElementToTheList(data);
	}
	
	public void calculateWilliamsR(int period, StockTickerHistory tickercollection){
		StockTickerHistory subListFromGivenPeriod = new StockTickerHistory();
		
		for (int i = 0; i<(tickercollection.getStockTickerDataList().size() - period +2); i++){
			ArrayList <StockTicker> subList = tickercollection.subListOfCollection(i, i + period -1);
			subListFromGivenPeriod.setStockTickerDataList(subList);
			
			addNewWilliamsRData(subListFromGivenPeriod);
		}
	}
	
	public void print(){
		this.williamsR.printToFile();
	}
}
