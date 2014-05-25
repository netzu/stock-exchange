package indicators.movingaverage.simple;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
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
	   public void test_001() throws IOException, ParseException {

	        StockTickerHistory tickerCollection =  mock.readTickerData(PATH_FLAT + "test_001_ticker.mst");
	        int period = 0;
	        String expectedErrorMessage = "Simple moving avarage cannot be calculated if period is zero";
	        
	        SimpleMovingAverageIndicator indicator = new SimpleMovingAverageIndicator();
	        
	        try{
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
	        
	        SimpleMovingAverageIndicator indicator = new SimpleMovingAverageIndicator();
	        
	        try{
	        	indicator.calculateSimpleMovingAverage(period, tickerCollection);
	        	fail("No exception has been found, expected: " + expectedErrorMessage);
	        }catch(SimpleMovingAverageCalculationException ex){
	        	assertTrue("Exception message is diffrent that expected. Expected: " + expectedErrorMessage + ". Got: " + ex.getMessage(), ex.getMessage().equals(expectedErrorMessage));
	        }
	    }
	 
	 @Test
	 public void test_003(){
		 
	 }
	 
	 
}
