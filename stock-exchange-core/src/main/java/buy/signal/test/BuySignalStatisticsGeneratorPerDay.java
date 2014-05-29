package buy.signal.test;

import java.util.ArrayList;
import java.util.List;

import utils.CalculateAverage;
import utils.CalculateCountOfNegativeValues;
import utils.CalculateCountOfPostiveValues;
import utils.CalculateMaximum;
import utils.CalculateMaximumTest;
import utils.CalculateMedian;
import utils.CalculateMinimum;
import utils.CalculateStandardDeviation;
import utils.CalculateSum;
import utils.CalculateSumOfPositiveValues;
import utils.CalculateVariance;
import utils.Stats;

public class BuySignalStatisticsGeneratorPerDay {

	public BuySingalStatistics generateStatistics(List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker, int day){
		
		BuySingalStatistics stats = new BuySingalStatistics();
		
		stats.setSumOfProfits(calculateSum(resultsFromBuySignalForTicker, day));
		stats.setSumOfPercentage(calculatePercentage(resultsFromBuySignalForTicker, day));
		stats.setAverage(calculateAverage(resultsFromBuySignalForTicker, day));
		stats.setStandardDeviation(calculateStandDev(resultsFromBuySignalForTicker, day));
		stats.setMedian(calculateMedian(resultsFromBuySignalForTicker, day));
		stats.setVariance(calculateVarience(resultsFromBuySignalForTicker, day));
		stats.setMin(calculateMin(resultsFromBuySignalForTicker, day));
		stats.setMax(calculateMax(resultsFromBuySignalForTicker, day));
		stats.setSumNegativeProfits(sumNegativeProfits(resultsFromBuySignalForTicker, day));
		stats.setSumPositiveProfits(sumPositiveProfits(resultsFromBuySignalForTicker, day));
		stats.setCountNegativeProfits(countNegativeProfits(resultsFromBuySignalForTicker, day));
		stats.setCountPositiveProfits(countPositiveProfits(resultsFromBuySignalForTicker, day));
		
		return stats;		
	}
	
	private List<Double> getProfitsFromOneDay(List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker, int day){
		
		if(resultsFromBuySignalForTicker.isEmpty()){
			throw new IllegalArgumentException("Results from Buy Signal for Ticker is empty");
		}
		
		if(day>resultsFromBuySignalForTicker.get(0).size()){
			throw new IllegalArgumentException("You are checking for day " + day + " which dosn't exist!");
		}
		
		List <Double> aggregated = new ArrayList<Double>();
		
		for(int i = 0; i<resultsFromBuySignalForTicker.size(); i++){
			
			if(day<=resultsFromBuySignalForTicker.get(i).size()-1){
				
				aggregated.add(resultsFromBuySignalForTicker.get(i).get(day).getProfit());
			}
		}
		
		if (aggregated.isEmpty()) {
			System.out.println("dupa");
		}
		
		return aggregated;
	}
	
	private List<Double> getPercentageFromOneDay(List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker, int day){
		List <Double> aggregated = new ArrayList<Double>();
		
		for(int i = 0; i<resultsFromBuySignalForTicker.size(); i++){
			
			if(day<resultsFromBuySignalForTicker.get(i).size()){
				
				aggregated.add(resultsFromBuySignalForTicker.get(i).get(day).getProfitInPercentage());
			}
		}
		return aggregated;
	}
	
	public double calculateSum(List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker, int day){
		
		List <Double> aggregated = getProfitsFromOneDay(resultsFromBuySignalForTicker, day);
		
		return CalculateSum.calculate(aggregated);
	}
	
	public double calculatePercentage(List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker, int day){
		
		List <Double> aggregated = getPercentageFromOneDay(resultsFromBuySignalForTicker, day);
		
		return CalculateSum.calculate(aggregated);
	}
	
	public double calculateAverage(List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker, int day){
		
		List <Double> aggregated = getProfitsFromOneDay(resultsFromBuySignalForTicker, day);
		
		return CalculateAverage.calculate(aggregated);
	}

	public double calculateStandDev(List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker, int day){
		
		List <Double> aggregated = getProfitsFromOneDay(resultsFromBuySignalForTicker, day);
		
		return CalculateStandardDeviation.calculate(aggregated);
	}
	
	public double calculateMedian(List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker, int day){
		
		List <Double> aggregated = getProfitsFromOneDay(resultsFromBuySignalForTicker, day);
		
		return CalculateMedian.calculate(aggregated);
	}
	
	public double calculateVarience(List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker, int day){
		
		List <Double> aggregated = getProfitsFromOneDay(resultsFromBuySignalForTicker, day);
		
		return CalculateVariance.calculate(aggregated);
	}
	
	public double calculateMin(List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker, int day){
		
		List <Double> aggregated = getProfitsFromOneDay(resultsFromBuySignalForTicker, day);
		
		if (aggregated.isEmpty()) {
			System.out.println("pusto");
		}
		
		return CalculateMinimum.calculate(aggregated);
	}
	
	public double calculateMax(List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker, int day){
		
		List <Double> aggregated = getProfitsFromOneDay(resultsFromBuySignalForTicker, day);
		
		return CalculateMaximum.calculate(aggregated);
	}
	
	public double sumNegativeProfits(List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker, int day){
		
		List <Double> aggregated = getProfitsFromOneDay(resultsFromBuySignalForTicker, day);
		
		return Stats.sumNegative(aggregated);
	}
	
	public double sumPositiveProfits(List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker, int day){
		
		List <Double> aggregated = getProfitsFromOneDay(resultsFromBuySignalForTicker, day);
		
		return CalculateSumOfPositiveValues.sumPositive(aggregated);
	}
	
	public int countNegativeProfits(List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker, int day){
		
		List <Double> aggregated = getProfitsFromOneDay(resultsFromBuySignalForTicker, day);
		
		return CalculateCountOfNegativeValues.calculate(aggregated);
	}
	
	public int countPositiveProfits(List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker, int day){
		
		List <Double> aggregated = getProfitsFromOneDay(resultsFromBuySignalForTicker, day);
		
		return CalculateCountOfPostiveValues.calculate(aggregated);
	}
	
}
