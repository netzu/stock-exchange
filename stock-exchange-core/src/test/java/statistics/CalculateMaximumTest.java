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

public class CalculateMaximumTest {
	final static String PATH = new String("statistics/calculateMaximumResources/");
	TestBeans mock = new TestBeans();

	@Test
	public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		  Constructor<CalculateMaximum> constructor = CalculateMaximum.class.getDeclaredConstructor();
		  assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		  constructor.setAccessible(true);
		  constructor.newInstance();
	}
	
	@Test
	public void emptyList(){
		List<Double> entryList = new ArrayList<Double>();
		String expectedErrorMessage = "Cannot calculate maximum value from empty list";
		
		try{
			CalculateMaximum.calculate(entryList);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(StockExchangeIllegalStateException ex){
			assertTrue(ex.getMessage().equals(expectedErrorMessage));
		}
	}
	
	
	@Test
	public void maximumValueFirstOnList() throws IOException{
		List<Double> entryList = new ArrayList<Double>();
		entryList = mock.getListOfDoubles(PATH + "maximumValueFirstOnList");
		
		double expectedResult = 13.67;
		
		try{
			double curretnResult = CalculateMaximum.calculate(entryList);
			assertTrue("CurrentResult: " + curretnResult + ", ExpectedResult: " + expectedResult, Precision.equalsIncludingNaN(expectedResult, curretnResult, 0.01));
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	@Test
	public void maximumValueLastOnList() throws IOException{
		List<Double> entryList = new ArrayList<Double>();
		entryList = mock.getListOfDoubles(PATH + "maximumValueLastOnList");
		
		double expectedResult = 42.11;
		
		try{
			double curretnResult = CalculateMaximum.calculate(entryList);
			assertTrue("CurrentResult: " + curretnResult + ", ExpectedResult: " + expectedResult, Precision.equalsIncludingNaN(expectedResult, curretnResult, 0.01));
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	@Test
	public void twoLocalMaximumOnList() throws IOException{
		List<Double> entryList = new ArrayList<Double>();
		entryList = mock.getListOfDoubles(PATH + "twoLocalMaximumOnList");
		
		double expectedResult = 65.98;
		
		try{
			double curretnResult = CalculateMaximum.calculate(entryList);
			assertTrue("CurrentResult: " + curretnResult + ", ExpectedResult: " + expectedResult, Precision.equalsIncludingNaN(expectedResult, curretnResult, 0.01));
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}

}
