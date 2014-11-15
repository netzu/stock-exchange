package statistics;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

public class CalculateAverageTest {
	final static String PATH = new String("statistics/");
	TestBeans mock = new TestBeans();
	
	@Test
	public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		  Constructor<CalculateAverage> constructor = CalculateAverage.class.getDeclaredConstructor();
		  assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		  constructor.setAccessible(true);
		  constructor.newInstance();
	}

	@Test
	public void emptyList(){
		List<Double> entryList = new ArrayList<Double>();
		String expectedErrorMessage = "Cannot calculate average for empty list";
		
		try{
			CalculateAverage.calculate(entryList);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(StockExchangeIllegalStateException ex){
			assertTrue(ex.getMessage().equals(expectedErrorMessage));
		}
	}
	
	@Test
	public void oneElementOnly(){
		double expectedResults = 3.45;
		
		List<Double> entryList = new ArrayList<Double>();
		entryList.add(expectedResults);
		
		try{
			double currentResult = CalculateAverage.calculate(entryList);
			assertTrue("CurrentResult: " + currentResult + ", ExpectedResult: ", Precision.equalsIncludingNaN(expectedResults, currentResult, 0.01));
		}catch(StockExchangeIllegalStateException ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	
	@Test
	public void multipleElement() throws IOException{
		List<Double> entryList = new ArrayList<Double>();		
		entryList = mock.getListOfDoubles(PATH + "listWithSeveralEntries");
		
		double expectedResults = 175.1988;
		
		try{
			double currentResult = CalculateAverage.calculate(entryList);
			assertTrue("CurrentResult: " + currentResult + ", ExpectedResult: ", Precision.equalsIncludingNaN(expectedResults, currentResult, 0.0001));
		}catch(StockExchangeIllegalStateException ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
}
