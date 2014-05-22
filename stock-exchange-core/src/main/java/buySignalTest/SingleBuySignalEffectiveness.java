package buySignalTest;

import java.util.ArrayList;

import org.joda.time.DateTime;

import data.collector.StockTickerHistory;

public class SingleBuySignalEffectiveness {
	
	public boolean wasSignalCorrect(ArrayList<Boolean> daysWithPositiveResultsForOneSignal){
		
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
	
	public ArrayList<Boolean> daysWithPositiveResults(int numberOfDays, DateTime buySignals, StockTickerHistory collection){
		
		if(numberOfDays==0){
			throw new BuySignalEffectivenessException("Number days in test cannot be 0");
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
		
	public ArrayList<Double> percentageGainPerDay(int numberOfDays, DateTime buySignals, StockTickerHistory collection){
		
		if(numberOfDays==0){
			throw new BuySignalEffectivenessException("Number days in test cannot be 0");
		}
		
		if(collection.getStockTickerDataList().isEmpty()){
			throw new BuySignalEffectivenessException("Ticker collection cannot be empty");
		}
		
		ArrayList<Double> balanceInPercentage = new ArrayList<Double>();
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
	
	public double[] valueGainPerDay(int numberOfDays, DateTime buySignals, StockTickerHistory collection){
		
		
		double[] balanceInMoney = new double[numberOfDays];
		double priceOfBuy = collection.findStockByDate(buySignals).getClose();
		int index = collection.findStockIndexByDate(buySignals);
		
		for(int i=0; i<numberOfDays; i++){	
			double currentPrice = collection.getStockTickerDataList().get(index+i).getClose();
			double delta = currentPrice - priceOfBuy;
			
			balanceInMoney[i] = delta;
		}
		
		return balanceInMoney;
	}
	
	public double percentageOfDaysWithPositiveResults(boolean[] daysWithPositiveResultsForOneSignal){
		int counter = 0;
		
		for(int i=0; i<daysWithPositiveResultsForOneSignal.length; i++){
			if(daysWithPositiveResultsForOneSignal[i]==true){
				counter++;
			}
		}
		
		return counter/daysWithPositiveResultsForOneSignal.length*100;
	}

}
