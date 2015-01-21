package buy.signal.measurements;

import java.util.ArrayList;
import java.util.Collection;

import utils.histogram.FirstDayWithProfitsHistogram;
import utils.histogram.HistogramItem;

public class PriceDeltaData {

	private String indicator;
	private String settingsOfIndicator;
	private int daysUnderTest;
	private int totalNumerOfSignals;
	
	Collection<HistogramItem<Integer>> histogramResults;
	
	PriceDeltaData(String indicator, String settingsOfIndicator, int daysUnderTest, int totalNumerOfSignals, ArrayList<Integer> collectionOfPricesDifrences){
		this.indicator = indicator;
		this.settingsOfIndicator = settingsOfIndicator;
		this.daysUnderTest = daysUnderTest;
		this.totalNumerOfSignals = totalNumerOfSignals;
		
		FirstDayWithProfitsHistogram histogram = FirstDayWithProfitsHistogram.createBuckets(daysUnderTest);
		histogramResults = histogram.calculateHistogram(collectionOfPricesDifrences);
	}
	
	public void displayData(ArrayList<Integer> collectionOfPricesDifrences){
		
		int counter = 0;

		for(HistogramItem<Integer> item : histogramResults){
			counter = counter + item.getItems().size();
		}
		
		System.out.println("histogramResults.size(): " + histogramResults.size());
		
		System.out.println("Counter: "  + counter );
		double correctness = 0;
		 
		for(HistogramItem<Integer> item : histogramResults){
			double percentage = (double)item.getItems().size() / (double)totalNumerOfSignals;
			double percentageOfProfits = (double)item.getItems().size() / (double)counter;
			
			System.out.println(item.getPredicate() + " , " + item.getItems().size() + ", " + percentage + ", " + percentageOfProfits);
			
			correctness = correctness + percentage;
		}
		 
		System.out.println("Correctness: " + correctness);
	}
	
	public void printToExcel(ArrayList<Integer> collectionOfPricesDifrences, String PATH, String file){
		FirstDayWithProfitsHistogram histogram = FirstDayWithProfitsHistogram.createBuckets(daysUnderTest);
		Collection<HistogramItem<Integer>> histogramResults = histogram.calculateHistogram(collectionOfPricesDifrences);
		
		int counter = 0;
		
		histogram.printToExcel(histogramResults, totalNumerOfSignals, PATH, file);
		System.out.println("KONIEC");
		
		//print histogram
		for(HistogramItem<Integer> item : histogramResults){
			System.out.println(item.getPredicate() + " has elements: " + item.getItems().size());
			counter = counter + item.getItems().size();
		}
		
		System.out.println("Sum: " + counter);
		System.out.println("Total number of signals: " + totalNumerOfSignals);
	}
	
	public String getIndicator() {
		return indicator;
	}
	
	public String getSettingsOfIndicator() {
		return settingsOfIndicator;
	}
	
	public int getDaysUnderTest() {
		return daysUnderTest;
	}
	
	public int getTotalNumerOfSignals() {
		return totalNumerOfSignals;
	}
	
	public void setTotalNumerOfSignals(int totalNumerOfSignals) {
		this.totalNumerOfSignals = totalNumerOfSignals;
	}
	
	public Collection<HistogramItem<Integer>> getHistogramResults() {
		return histogramResults;
	}
	
	public void setHistogramResults(Collection<HistogramItem<Integer>> histogramResults) {
		this.histogramResults = histogramResults;
	}	
	
}
