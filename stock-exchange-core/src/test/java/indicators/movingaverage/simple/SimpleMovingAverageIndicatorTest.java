package indicators.movingaverage.simple;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import data.collector.StockTickerHistory;
import utils.MocksForTests;

public class SimpleMovingAverageIndicatorTest {
	
	DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");	
	final static String PATH_FLAT = new String("indicators/simpleMovingAverage/indicator/");
	
	MocksForTests mock = new MocksForTests();
	
	/*
	 * period is equal 0, exception should be thrown
	 */
	 @Test
	   public void periodEqualZero() throws IOException, ParseException {

	        StockTickerHistory tickerCollection =  mock.readTickerData(PATH_FLAT + "periodEqualZero.mst");
	        int period = 0;
	        String expectedErrorMessage = "Simple moving avarage cannot be calculated if period is zero";
	        	        
	        try{
	        	SimpleMovingAverageIndicator indicator = new SimpleMovingAverageIndicator();
	        	indicator.calculateSimpleMovingAverage(period, tickerCollection);
	        	fail("No exception has been found, expected: " + expectedErrorMessage);
	        }catch(SimpleMovingAverageCalculationException ex){
	        	assertTrue("Exception message is diffrent that expected. Expected: " + expectedErrorMessage + ". Got: " + ex.getMessage(), ex.getMessage().equals(expectedErrorMessage));
	        }
	    }
	 
	/*
	 * ticker Collection for which the simple moving average should be calculated is empty
	 */
	 @Test
	   public void test_002() throws IOException, ParseException {

	        StockTickerHistory tickerCollection =  mock.readTickerData(PATH_FLAT + "test_002_ticker.mst");
	        int period = 3;
	        String expectedErrorMessage = "Simple moving average cannot be calculated for ticker without now history";
	        
	        try{
	        	SimpleMovingAverageIndicator indicator = new SimpleMovingAverageIndicator();
	        	indicator.calculateSimpleMovingAverage(period, tickerCollection);
	        	fail("No exception has been found, expected: " + expectedErrorMessage);
	        }catch(SimpleMovingAverageCalculationException ex){
	        	assertTrue("Exception message is diffrent that expected. Expected: " + expectedErrorMessage + ". Got: " + ex.getMessage(), ex.getMessage().equals(expectedErrorMessage));
	        }
	    }
	 
	 @Test
	 public void periodEqualOne() throws ParseException, IOException{
		 
		 StockTickerHistory tickerCollection =  mock.readTickerData(PATH_FLAT + "periodEqualOne_ticker.mst");
		 List<SimpleMovingAverageData> expectedResults = mock.getAverageData(PATH_FLAT + "periodEqualOne_ExpectedResults");
		 
		 int period = 1;
		 
		 try{
			 SimpleMovingAverageIndicator indicator = new SimpleMovingAverageIndicator();
			 List<SimpleMovingAverageData> currentResults = indicator.calculateSimpleMovingAverage(period, tickerCollection);
			 
			 assertTrue("Size of current list is wrong, expecting 13 got: " + currentResults.size(), currentResults.size() == 13);
			 assertTrue("Two lists are not same", checkIfEquals(expectedResults, currentResults));
			 
		 }catch(Exception ex){
			 fail("Exception when not expected: " + ex.getMessage());
		 }
	 }
	 
	 @Test
	 public void periodLongerThanTickerCollection() throws ParseException, IOException{
		 StockTickerHistory tickerCollection =  mock.readTickerData(PATH_FLAT + "periodLongerThanTickerCollection_ticker.mst");
		 List<SimpleMovingAverageData> expectedResults = mock.getAverageData(PATH_FLAT + "periodLongerThanTickerCollection_ExpectedResults");
		 
		 int period = 6;
		 
		 try{
			 SimpleMovingAverageIndicator indicator = new SimpleMovingAverageIndicator();
			 List<SimpleMovingAverageData> currentResults = indicator.calculateSimpleMovingAverage(period, tickerCollection);
			 assertTrue("Two lists are not same", checkIfEquals(expectedResults, currentResults));			 
		 }catch(Exception ex){
			 fail("Exception when not expected: " + ex.getMessage());
		 }
	 }
	 
	 @Test
	 public void checkIfProperValuesAreCalculated() throws ParseException, IOException{
		 StockTickerHistory tickerCollection =  mock.readTickerData(PATH_FLAT + "checkIfProperValuesAreCalculated_ticker.mst");
		 List<SimpleMovingAverageData> expectedResults = mock.getAverageData(PATH_FLAT + "checkIfProperValuesAreCalculated_ExpectedResults");
		 
		 int period = 5;
		 
		 try{
			 SimpleMovingAverageIndicator indicator = new SimpleMovingAverageIndicator();
			 List<SimpleMovingAverageData> currentResults = indicator.calculateSimpleMovingAverage(period, tickerCollection);
			 int expectedSizeOfList = tickerCollection.getStockTickerDataList().size()-period+1;
			 
			 assertTrue("Size of current list is wrong: " + currentResults.size() + ", was expecting: " + expectedSizeOfList, currentResults.size()  == expectedSizeOfList);
			 assertTrue(checkIfEquals(expectedResults, currentResults));
			 
		 }catch(Exception ex){
			 fail("Exception when not expected: " + ex.getMessage());
		 }
	 }

	private boolean checkIfEquals(List<SimpleMovingAverageData> expectedResults, List<SimpleMovingAverageData> currentResults) {
		try{
			for(int i=0; i<expectedResults.size(); i++){
				 if(!Precision.equalsIncludingNaN(expectedResults.get(i).getAverage(), currentResults.get(i).getAverage(), 0.01)){
					 return false;
				 }
			 }
		}catch(Exception ex){
			return false;
		}
		return true;
	}
	 
}
