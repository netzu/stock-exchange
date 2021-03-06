package statistics;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.junit.Test;

import indicators.williamsr.TestBeans;
import data.collector.StockExchangeIllegalStateException;

public class CalculateSumTest {
	
	final static String PATH = new String("statistics/");
	TestBeans mock = new TestBeans();
	
	@Test
	public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		  Constructor<CalculateSum> constructor = CalculateSum.class.getDeclaredConstructor();
		  assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		  constructor.setAccessible(true);
		  constructor.newInstance();
	}
	
	@Test
	public void emptyList(){
		List<Double> entryList = new ArrayList<Double>();
		String expectedErrorMessage = "Cannot calculate sum for empty list";
		
		try{
			CalculateSum.calculate(entryList);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(StockExchangeIllegalStateException ex){
			assertTrue(ex.getMessage().equals(expectedErrorMessage));
		}
	}
	
	@Test
	public void listWithOneEntryOnly(){
		List<Double> entryList = new ArrayList<Double>();
		double expectedResults = 5.23;
		
		entryList.add(expectedResults);
		
		try{
			double currentResults = CalculateSum.calculate(entryList);
			assertTrue("CurrentResult: " + currentResults + ", ExpectedResult: ", Precision.equalsIncludingNaN(expectedResults, currentResults, 0.01));
		}catch(Exception ex){
			fail("Exception found when not expecting: " + ex.getMessage());
		}
		
	}
	
	@Test
	public void listWithSeveralEntries() throws IOException{
		List<Double> entryList = new ArrayList<Double>();		
		entryList = mock.getListOfDoubles(PATH + "listWithSeveralEntries");
		
		double expectedResults = 1401.59;
		
		try{
			double currentResults = CalculateSum.calculate(entryList);
			String errorMessage = "CurrentResults: " + currentResults + ", expectedResults: " + expectedResults;
			assertTrue(errorMessage, Precision.equalsIncludingNaN(expectedResults, currentResults, 0.01));
		}catch(Exception ex){
			fail("Exception found when not expecting: " + ex.getMessage());
		}
		
	}

}
