package buy.signal.test;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import data.collector.StockExchangeIllegalStateException;
import data.collector.StockTickerHistory;

public class SingleBuySignalEffectiveness {
	
	public boolean wasSignalCorrect(List<Boolean> daysWithPositiveResultsForOneSignal){
		
		if(daysWithPositiveResultsForOneSignal.isEmpty()){
			throw new BuySignalEffectivenessException("List with results for each day is empty");
		}
		
		boolean results = false;
		
		for(int i=0; i <daysWithPositiveResultsForOneSignal.size(); i++){
			if(daysWithPositiveResultsForOneSignal.get(i) == true){
				results = true;
				break;
			}
		}
		
		return results;
	}
	
	public List<Boolean> daysWithPositiveResults(int numberOfDays, DateTime buySignals, StockTickerHistory collection){
		
		if(numberOfDays==0){
			throw new BuySignalEffectivenessException("Number of days in test cannot be 0");
		}
		
		if(collection.getStockTickerDataList().isEmpty()){
			throw new BuySignalEffectivenessException("Ticker collection cannot be empty");
		}
		
		ArrayList<Boolean> dayWithPositiveResults = new ArrayList<Boolean>();
		double priceOfBuy = collection.findStockByDate(buySignals).getClose();
		int index = collection.findStockIndexByDate(buySignals);
		
		if(collection.getStockTickerDataList().size()-index <= numberOfDays){
			numberOfDays = collection.getStockTickerDataList().size()-index-1;
		}
		
		for(int i=0; i<numberOfDays; i++){	
			double currentPrice = collection.getStockTickerDataList().get(index+i+1).getClose();
			
			if(priceOfBuy < currentPrice){
				dayWithPositiveResults.add(i, true); 
			}
			else {
				dayWithPositiveResults.add(i, false); 
			}

		}
		
		return dayWithPositiveResults;
	}
		
	public List<Double> percentageGainPerDay(int numberOfDays, DateTime buySignals, StockTickerHistory collection){
		
		if(numberOfDays==0){
			throw new BuySignalEffectivenessException("Number of days in test cannot be 0");
		}
		
		if(collection.getStockTickerDataList().isEmpty()){
			throw new BuySignalEffectivenessException("Ticker collection cannot be empty");
		}
		
		List<Double> balanceInPercentage = new ArrayList<Double>();
		double priceOfBuy = collection.findStockByDate(buySignals).getClose();
		int index = collection.findStockIndexByDate(buySignals);
		
		if(collection.getStockTickerDataList().size()-index <= numberOfDays){
			numberOfDays = collection.getStockTickerDataList().size()-index-1;
		}
		
		for(int i=0; i<numberOfDays; i++){	
			double currentPrice = collection.getStockTickerDataList().get(index+i+1).getClose();
			double delta = currentPrice - priceOfBuy;
			double percentage = (delta*100)/priceOfBuy;
			
			balanceInPercentage.add(percentage);
		}
		
		return balanceInPercentage;
	}
	
	public List<Double> valueGainPerDay(int numberOfDays, DateTime buySignals, StockTickerHistory collection){			
		
		if(numberOfDays==0){
			throw new BuySignalEffectivenessException("Number days in test cannot be 0");
		}
		
		if(collection.getStockTickerDataList().isEmpty()){
			throw new BuySignalEffectivenessException("Ticker collection cannot be empty");
		}
		
		List<Double> balanceInMoney = new ArrayList<Double>();
		double priceOfBuy = collection.findStockByDate(buySignals).getClose();
		int index = collection.findStockIndexByDate(buySignals);
		
		if(collection.getStockTickerDataList().size()-index <= numberOfDays){
			numberOfDays = collection.getStockTickerDataList().size()-index-1;
		}
		
		for(int i=0; i<numberOfDays; i++){	
			double currentPrice = collection.getStockTickerDataList().get(index+i+1).getClose();
			double delta = currentPrice - priceOfBuy;
			
			balanceInMoney.add(delta);
		}
		
		return balanceInMoney;
	}
	
	public double percentageOfDaysWithPositiveResults(List<Boolean> daysWithPositiveResultsForOneSignal){
		
		if(daysWithPositiveResultsForOneSignal.isEmpty()){
			throw new StockExchangeIllegalStateException("There is no days with results (empty list passed as a argument) so cannot calculate percentage" );
		}
		
		int counter = 0;
		
		for(int i=0; i<daysWithPositiveResultsForOneSignal.size(); i++){
			if(daysWithPositiveResultsForOneSignal.get(i)==true){
				counter++;
			}
		}
		
		return (double) counter/ (double)daysWithPositiveResultsForOneSignal.size()*100.0;
	}

}
