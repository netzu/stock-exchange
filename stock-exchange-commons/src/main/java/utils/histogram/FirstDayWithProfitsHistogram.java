package utils.histogram;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.google.common.base.Predicate;
import com.google.common.collect.Range;


public class FirstDayWithProfitsHistogram implements HistogramCalculator<Integer>{	
	
	private DefaultHistogramCalculator<Integer> daysOfProfit;
	
	private FirstDayWithProfitsHistogram(final List<Predicate<Integer>> predicates) {
		daysOfProfit = new DefaultHistogramCalculator<Integer>(predicates);
	}
	
	
	public static FirstDayWithProfitsHistogram createBuckets(final int numberOfBackets) {
		
		List<Predicate<Integer>> predicates = Lists.newArrayList();
		for (int i = 1 ; i <= numberOfBackets; i++){
			predicates.add(Range.closedOpen(i, i+1));
		}
					
		return new FirstDayWithProfitsHistogram(predicates);
	}


	@Override
	public Collection<HistogramItem<Integer>> calculateHistogram(
			Collection<Integer> input) {
		
		return daysOfProfit.calculateHistogram(input);
	}
	
	public int sumOfItemsInBuckets(Collection<HistogramItem<Integer>> histogramResults){
		
		int counter = 0;
		
		for(HistogramItem<Integer> item : histogramResults){
			counter = counter + item.getItems().size();
		}
		
		return counter;		
	}
	
	public void printToExcel(Collection<HistogramItem<Integer>> histogramToPrint, int amoutOfAllSignals, String path, String name){
		
		Writer writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + name), "utf-8"));
		    writer.write("Day; Count; Percentage From Profits; Percentage From all signals\n");
		    
		    double percentageFromProfits = 0.0;
		    double percentageFromAllSignals = 0.0;
		    
		    for(HistogramItem<Integer> item : histogramToPrint){
		    	
		    	int amountInBucket = item.getItems().size();
		    	
		    	percentageFromProfits = amountInBucket / sumOfItemsInBuckets(histogramToPrint);
		    	percentageFromAllSignals = amountInBucket / amoutOfAllSignals;
		    	
		    	writer.write(item.getPredicate() + ";" + amountInBucket + ";" + percentageFromProfits + ";" + percentageFromAllSignals + "\n");
			}
		} catch (IOException ex) {
			// report
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
	}
}
