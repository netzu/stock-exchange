package buy.signal.measurements;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import data.collector.StockTickerHistory;

import indicators.williamsr.TestBeans;

public class PriceDeltaTest {
	DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");	
	final static String PATH = new String("buySignalMeasurements/PriceDeltaTest/");
	
	TestBeans mock = new TestBeans();
	
	@Test
	public void signalNotFound() throws ParseException{
		StockTickerHistory stockCollection = mock.readTickerData(PATH + "signalNotFound");
		DateTime buySignal = dateFormater.parseDateTime("20120302");
		
		String expectedErrorMessage = "Could not find it";
		
		PriceDeltaCalculator priceDelta = new PriceDeltaCalculator();
		
		try{
			List<Double> currentResults = priceDelta.getDelta(buySignal, stockCollection, 3);
			fail("No exception has been found, expected" + expectedErrorMessage);
		}catch(Exception ex){
			assertTrue("Exception message is diffrent that expected. Expected: " + expectedErrorMessage + ". Got: " + ex.getMessage(), ex.getMessage().equals(expectedErrorMessage));
		}
	}
	
	//Signal Day has been generated on the last day from stock ticker collection
	@Test
	public void signalOnTheLastDay() throws ParseException{
		StockTickerHistory stockCollection = mock.readTickerData(PATH + "signalOnTheLastDay");
		DateTime buySignal = dateFormater.parseDateTime("20120314");
		
		PriceDeltaCalculator priceDelta = new PriceDeltaCalculator();
		List<Double> currentResults = priceDelta.getDelta(buySignal, stockCollection, 3);
		
		assertTrue(currentResults.isEmpty());
	}
	
	@Test
	public void emptyTestRange() throws ParseException{
		StockTickerHistory stockCollection = mock.readTickerData(PATH + "signalOnTheLastDay");
		DateTime buySignal = dateFormater.parseDateTime("20120304");
		
		PriceDeltaCalculator priceDelta = new PriceDeltaCalculator();
		List<Double> currentResults = priceDelta.getDelta(buySignal, stockCollection, 0);
		
		assertTrue(currentResults.isEmpty());
	}
	
	@Test
	public void rangeBiggerThanAvailableHistory() throws ParseException{
		StockTickerHistory stockCollection = mock.readTickerData(PATH + "rangeBiggerThanAvailableHistory");
		DateTime buySignal = dateFormater.parseDateTime("20120312");
		
		PriceDeltaCalculator priceDelta = new PriceDeltaCalculator();
		List<Double> currentResults = priceDelta.getDelta(buySignal, stockCollection, 20);
		
		assertTrue("Current size: " + currentResults.size() + ", should be 2", currentResults.size() == 2);
	}
	
	@Test
	public void checkCorrectnessOfDelta() throws ParseException, IOException{
		StockTickerHistory stockCollection = mock.readTickerData(PATH + "checkCorrectnessOfDelta");
		DateTime buySignal = dateFormater.parseDateTime("20120305");
		
		List<Double> expectedResults = new ArrayList<Double>();
		expectedResults = mock.getListOfDoubles(PATH + "checkCorrectnessOfDeltaExpectedResults");
		
		PriceDeltaCalculator priceDelta = new PriceDeltaCalculator();
		List<Double> currentResults = priceDelta.getDelta(buySignal, stockCollection, 6);
		
		
//		assertTrue(errorMessage, currentResults.size() == expectedResults.size());
		assertThat(currentResults).hasSameSizeAs(expectedResults);
		int index = 0;
		for (final Double expectedItem : expectedResults) {
			final Double currentItem = currentResults.get(index++);
			
			assertThat(Math.abs(currentItem - expectedItem)).isLessThan(0.001);
		}
	}
}
