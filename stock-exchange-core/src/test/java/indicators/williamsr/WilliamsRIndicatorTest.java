package indicators.williamsr;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import configuration.Share;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Test;

import data.collector.StockTickerHistory;
import org.omg.CORBA.PERSIST_STORE;
import sun.security.provider.SHA;

public class WilliamsRIndicatorTest extends TestBeans {

	final static String PATH_FLAT = "indicators/williams/";

	
	@Test
	public void emptyTickerCollection() throws ParseException{
		StockTickerHistory history =  readTickerData(PATH_FLAT + "emptyTickerCollection.mst");
		int period = 10;
		
		WilliamsRIndicator williamsR = new WilliamsRIndicator(period);
		
		try{
			williamsR.calculateWilliamsR(history);
		}
		catch (WilliamsRCalculationException e){
			String expectedErrorMessage = new String("Ticker's history data is empty");
			assertTrue(e.getMessage().equals(expectedErrorMessage));
		}
	}
	
	@Test
	public void periodEqualZero() throws ParseException{
		StockTickerHistory history =  readTickerData(PATH_FLAT + "periodEqualZero.mst");
		int period = 0;
		
		WilliamsRIndicator williamsR = new WilliamsRIndicator(period);
		
		try{
			williamsR.calculateWilliamsR(history);
		}
		catch (WilliamsRCalculationException e){
			String expectedErrorMessage = new String("Period must be grather than 0");
			assertTrue(e.getMessage().equals(expectedErrorMessage));
		}
	}
	
	@Test
	public void highestEqualCurrentClose() throws ParseException{
		StockTickerHistory history =  readTickerData(PATH_FLAT + "highestEqualCurrentClose.mst");
		int period = 8;
		
		WilliamsRIndicator williamsR = new WilliamsRIndicator(period);
		List<WilliamsRData> listwithResults = williamsR.calculateWilliamsR(history);
		
		int size  = listwithResults.size();
		double delta = 0.000001;
		
		double williamsRValue = listwithResults.get(0).getWilliamsR();
		DateTime date = listwithResults.get(0).getDate();
		DateTime expectedDate = Share.COMMON_FORMATTER.parseDateTime("20100108");
		
		assertTrue(size==1);
		Assert.assertEquals(williamsRValue, williamsRValue, delta);
		assertTrue(date.equals(expectedDate));
	}
	

	@Test
	public void highestEqualLowest() throws ParseException{
		
		StockTickerHistory history =  readTickerData(PATH_FLAT + "highestEqualLowest.mst");
		int period = 8;
		
		WilliamsRIndicator williamsR = new WilliamsRIndicator(period);
		List<WilliamsRData> listwithResults = williamsR.calculateWilliamsR(history);
		
		int size  = listwithResults.size();
		double delta = 0.000001;
		
		double williamsRValue = listwithResults.get(0).getWilliamsR();
		
		DateTime date = listwithResults.get(0).getDate();
		DateTime expectedDate = Share.COMMON_FORMATTER.parseDateTime("20100108");
		
		assertTrue(size==1);
		Assert.assertEquals(williamsRValue, williamsRValue, delta);
		assertTrue(date.equals(expectedDate));
	}
	
	@Test
	public void historySizeLowerThanPeriod() throws ParseException{
		StockTickerHistory history =  readTickerData(PATH_FLAT + "historySizeLowerThanPeriod.mst");
		int period = 9;
		
		WilliamsRIndicator williamsR = new WilliamsRIndicator(period);
		
		try{
			williamsR.calculateWilliamsR(history);
		}
		catch (WilliamsRCalculationException e){
			String expectedErrorMessage = new String("Size of tickerCollection lowere than period");
			assertTrue(e.getMessage().equals(expectedErrorMessage));
		}
	}
	
	@Test
	public void higestAndLowestIntheFirstDay() throws ParseException{
		
		StockTickerHistory history =  readTickerData(PATH_FLAT + "higestAndLowestIntheFirstDay.mst");
		int period = 8;
		
		WilliamsRIndicator williamsR = new WilliamsRIndicator(period);
		List<WilliamsRData> listwithResults = williamsR.calculateWilliamsR(history);
		
		int size  = listwithResults.size();
		double williamsRValue = listwithResults.get(0).getWilliamsR();
		double williamsRExpectedValue = 2.212389;
		double delta = 0.000001;
		
		DateTime date = listwithResults.get(0).getDate();
		DateTime expectedDate = Share.COMMON_FORMATTER.parseDateTime("20100108");
		
		assertTrue(size==1);
		Assert.assertEquals(williamsRExpectedValue, williamsRValue, delta);
		assertTrue(date.equals(expectedDate));
	}
	
	@Test
	public void higestAndLowestIntheLastDay() throws ParseException{
		
		StockTickerHistory history =  readTickerData(PATH_FLAT + "higestAndLowestIntheLastDay.mst");
		int period = 8;
		
		WilliamsRIndicator williamsR = new WilliamsRIndicator(period);
		List<WilliamsRData> listwithResults = williamsR.calculateWilliamsR(history);
		
		int size  = listwithResults.size();
		double williamsRValue = listwithResults.get(0).getWilliamsR();
		double williamsRExpectedValue = 24.41472;
		
		double delta = 0.00001;
		
		DateTime date = listwithResults.get(0).getDate();
		DateTime expectedDate = Share.COMMON_FORMATTER.parseDateTime("20100108");
		
		assertTrue(size==1);
		Assert.assertEquals(williamsRExpectedValue, williamsRValue, delta);
		assertTrue(date.equals(expectedDate));
	}
	
	@Test
	public void multipeWilliamsR() throws ParseException{
		StockTickerHistory history =  readTickerData(PATH_FLAT + "multipeWilliamsR.mst");
		int period = 6;
		
		WilliamsRIndicator williamsR = new WilliamsRIndicator(period);
		List<WilliamsRData> listwithResults = williamsR.calculateWilliamsR(history);
		
		int size  = listwithResults.size();
		double delta = 0.00001;
		
		double williamsRValue = listwithResults.get(0).getWilliamsR();
		double williamsRExpectedValue = 14.49704;
		
		assertTrue(size==3);
		
		DateTime date = listwithResults.get(0).getDate();
		DateTime expectedDate = Share.COMMON_FORMATTER.parseDateTime("20100106");
		
		Assert.assertEquals(williamsRExpectedValue, williamsRValue, delta);
		assertTrue(date.equals(expectedDate));
		
		williamsRExpectedValue = 74.32432;
		williamsRValue = listwithResults.get(1).getWilliamsR();
		
		date = listwithResults.get(1).getDate();
		expectedDate = Share.COMMON_FORMATTER.parseDateTime("20100107");
		
		Assert.assertEquals(williamsRExpectedValue, williamsRValue, delta);
		assertTrue(date.equals(expectedDate));
		
		williamsRExpectedValue = 24.41472;
		williamsRValue = listwithResults.get(2).getWilliamsR();
		
		date = listwithResults.get(2).getDate();
		expectedDate = Share.COMMON_FORMATTER.parseDateTime("20100108");
		
		Assert.assertEquals(williamsRExpectedValue, williamsRValue, delta);
		assertTrue(date.equals(expectedDate));
	}
}




