package indicators.williamsr;

import static org.junit.Assert.assertTrue;
import indicators.williamsr.WilliamsRCalculationException;
import indicators.williamsr.WilliamsRData;
import indicators.williamsr.WilliamsRIndicator;

import java.text.ParseException;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Test;

import data.collector.StockTickerHistory;
import utils.MocksForTests;

public class WilliamsRIndicatorTest {
	DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");
	
	final static String PATH_FLAT = new String("indicators/williams/");
	
	MocksForTests mock = new MocksForTests();
	
	@Test
	public void emptyTickerCollection() throws ParseException{
		StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "emptyTickerCollection.mst");
		int period = 10;
		
		WilliamsRIndicator williamsR = new WilliamsRIndicator();
		
		try{
			williamsR.calculateWilliamsR(period, history);
		}
		catch (WilliamsRCalculationException e){
			String expectedErrorMessage = new String("Ticker's history data is empty");
			assertTrue(e.getMessage().equals(expectedErrorMessage));
		}
	}
	
	@Test
	public void periodEqualZero() throws ParseException{
		StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "periodEqualZero.mst");
		int period = 0;
		
		WilliamsRIndicator williamsR = new WilliamsRIndicator();
		
		try{
			williamsR.calculateWilliamsR(period, history);
		}
		catch (WilliamsRCalculationException e){
			String expectedErrorMessage = new String("Period must be grather than 0");
			assertTrue(e.getMessage().equals(expectedErrorMessage));
		}
	}
	
	@Test
	public void highestEqualCurrentClose() throws ParseException{
		StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "highestEqualCurrentClose.mst");
		int period = 8;
		
		WilliamsRIndicator williamsR = new WilliamsRIndicator();		
		ArrayList<WilliamsRData> listwithResults = williamsR.calculateWilliamsR(period, history);
		
		int size  = listwithResults.size();
		double delta = 0.000001;
		
		double williamsRValue = listwithResults.get(0).getWilliamsR();
		DateTime date = listwithResults.get(0).getDate();
		DateTime expectedDate = dateFormater.parseDateTime("20100108");
		
		assertTrue(size==1);
		Assert.assertEquals(williamsRValue, williamsRValue, delta);
		assertTrue(date.equals(expectedDate));
	}
	

	@Test
	public void highestEqualLowest() throws ParseException{
		
		StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "highestEqualLowest.mst");
		int period = 8;
		
		WilliamsRIndicator williamsR = new WilliamsRIndicator();		
		ArrayList<WilliamsRData> listwithResults = williamsR.calculateWilliamsR(period, history);
		
		int size  = listwithResults.size();
		double delta = 0.000001;
		
		double williamsRValue = listwithResults.get(0).getWilliamsR();
		
		DateTime date = listwithResults.get(0).getDate();
		DateTime expectedDate = dateFormater.parseDateTime("20100108");
		
		assertTrue(size==1);
		Assert.assertEquals(williamsRValue, williamsRValue, delta);
		assertTrue(date.equals(expectedDate));
	}
	
	@Test
	public void historySizeLowerThanPeriod() throws ParseException{
		StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "historySizeLowerThanPeriod.mst");
		int period = 9;
		
		WilliamsRIndicator williamsR = new WilliamsRIndicator();		
		
		try{
			williamsR.calculateWilliamsR(period, history);
		}
		catch (WilliamsRCalculationException e){
			String expectedErrorMessage = new String("Size of tickerCollection lowere than period");
			assertTrue(e.getMessage().equals(expectedErrorMessage));
		}
	}
	
	@Test
	public void higestAndLowestIntheFirstDay() throws ParseException{
		
		StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "higestAndLowestIntheFirstDay.mst");
		int period = 8;
		
		WilliamsRIndicator williamsR = new WilliamsRIndicator();		
		ArrayList<WilliamsRData> listwithResults = williamsR.calculateWilliamsR(period, history);
		
		int size  = listwithResults.size();
		double williamsRValue = listwithResults.get(0).getWilliamsR();
		double williamsRExpectedValue = 2.212389;
		double delta = 0.000001;
		
		DateTime date = listwithResults.get(0).getDate();
		DateTime expectedDate = dateFormater.parseDateTime("20100108");
		
		assertTrue(size==1);
		Assert.assertEquals(williamsRExpectedValue, williamsRValue, delta);
		assertTrue(date.equals(expectedDate));
	}
	
	@Test
	public void higestAndLowestIntheLastDay() throws ParseException{
		
		StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "higestAndLowestIntheLastDay.mst");
		int period = 8;
		
		WilliamsRIndicator williamsR = new WilliamsRIndicator();		
		ArrayList<WilliamsRData> listwithResults = williamsR.calculateWilliamsR(period, history);
		
		int size  = listwithResults.size();
		double williamsRValue = listwithResults.get(0).getWilliamsR();
		double williamsRExpectedValue = 24.41472;
		
		double delta = 0.00001;
		
		DateTime date = listwithResults.get(0).getDate();
		DateTime expectedDate = dateFormater.parseDateTime("20100108");
		
		assertTrue(size==1);
		Assert.assertEquals(williamsRExpectedValue, williamsRValue, delta);
		assertTrue(date.equals(expectedDate));
	}
	
	@Test
	public void multipeWilliamsR() throws ParseException{
		StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "multipeWilliamsR.mst");
		int period = 6;
		
		WilliamsRIndicator williamsR = new WilliamsRIndicator();		
		ArrayList<WilliamsRData> listwithResults = williamsR.calculateWilliamsR(period, history);
		
		int size  = listwithResults.size();
		double delta = 0.00001;
		
		double williamsRValue = listwithResults.get(0).getWilliamsR();
		double williamsRExpectedValue = 14.49704;
		
		assertTrue(size==3);
		
		DateTime date = listwithResults.get(0).getDate();
		DateTime expectedDate = dateFormater.parseDateTime("20100106");
		
		Assert.assertEquals(williamsRExpectedValue, williamsRValue, delta);
		assertTrue(date.equals(expectedDate));
		
		williamsRExpectedValue = 74.32432;
		williamsRValue = listwithResults.get(1).getWilliamsR();
		
		date = listwithResults.get(1).getDate();
		expectedDate = dateFormater.parseDateTime("20100107");
		
		Assert.assertEquals(williamsRExpectedValue, williamsRValue, delta);
		assertTrue(date.equals(expectedDate));
		
		williamsRExpectedValue = 24.41472;
		williamsRValue = listwithResults.get(2).getWilliamsR();
		
		date = listwithResults.get(2).getDate();
		expectedDate = dateFormater.parseDateTime("20100108");
		
		Assert.assertEquals(williamsRExpectedValue, williamsRValue, delta);
		assertTrue(date.equals(expectedDate));
	}
}




