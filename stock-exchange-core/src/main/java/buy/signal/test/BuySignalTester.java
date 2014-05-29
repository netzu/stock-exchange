package buy.signal.test;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import data.collector.StockTicker;
import data.collector.StockTickerHistory;

public class BuySignalTester {
	
	public List <ProfitsFromSignal> calculateProfitsForOneSignal(DateTime buySignal, StockTickerHistory stockCollectionForTicker, int daysToTest){
		
		if(daysToTest==0){
			throw new IllegalArgumentException("Number days to test cannot by 0"); 
		}
		
		List <ProfitsFromSignal> profitsFromOneSignal = new ArrayList<ProfitsFromSignal>();
				
		StockTicker tickerDataFromBuySignal = stockCollectionForTicker.findStockByDate(buySignal);
		
		double price = tickerDataFromBuySignal.getClose();
		
		int index = stockCollectionForTicker.getStockTickerDataList().indexOf(tickerDataFromBuySignal);
		
		for(int i=index+1; i<(index+daysToTest+1); i++){
			
			if(i==(stockCollectionForTicker.getStockTickerDataList().size())){
				break;
			}
			
			ProfitsFromSignal data = new ProfitsFromSignal();
			
			data.setProfit(stockCollectionForTicker.getStockTickerDataList().get(i).getClose()-price);
			data.setProfitInPercentage((stockCollectionForTicker.getStockTickerDataList().get(i).getClose()-price)/price);
			
			profitsFromOneSignal.add(data);
		}		
		
		return profitsFromOneSignal;
	}
	
	public List <List<ProfitsFromSignal>> calculateProfitsForTicker(List<DateTime> buySignal, StockTickerHistory stockCollectionForTicker, int daysToTest){
		
		if(daysToTest==0){
			throw new IllegalArgumentException("Number days to test cannot by 0"); 
		}
		
		List <List <ProfitsFromSignal>> resultsFromBuySignalForTicker = new ArrayList<List <ProfitsFromSignal>>();
		
		for(int i=0; i<buySignal.size(); i++){
			List <ProfitsFromSignal> profits = calculateProfitsForOneSignal(buySignal.get(i), stockCollectionForTicker, daysToTest);
			
			if(!profits.isEmpty()){
				resultsFromBuySignalForTicker.add(profits);
			}
		}
		return resultsFromBuySignalForTicker;
	}
	
	public List <BuySingalStatistics> statisticsForTicker(List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker, int daysToTest){
		
		List <BuySingalStatistics> statisticForAfterEachDay = new ArrayList<BuySingalStatistics>();
		BuySignalStatisticsGeneratorPerDay generator = new BuySignalStatisticsGeneratorPerDay();
		
		for(int i=0; i<daysToTest; i++){
			statisticForAfterEachDay.add(generator.generateStatistics(resultsFromBuySignalForTicker, i));
		}
		
		return statisticForAfterEachDay;
	}
}
