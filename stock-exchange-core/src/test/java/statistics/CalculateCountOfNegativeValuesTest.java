package statistics;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import indicators.williamsr.TestBeans;
import data.collector.StockExchangeIllegalStateException;

public class CalculateCountOfNegativeValuesTest {

	final static String PATH = new String("statistics/calculateCountOfNegativeValuesResources/");
	TestBeans mock = new TestBeans();
	
	@Test
	public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		  Constructor<CalculateCountOfNegativeValues> constructor = CalculateCountOfNegativeValues.class.getDeclaredConstructor();
		  assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		  constructor.setAccessible(true);
		  constructor.newInstance();
	}
	
	@Test
	public void emptyList(){
		List<Double> entryList = new ArrayList<Double>();
		String expectedErrorMessage = "Cannot calculate count of negative values from empty list";
		
		try{
			CalculateCountOfNegativeValues.calculate(entryList);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(StockExchangeIllegalStateException ex){
			assertTrue(ex.getMessage().equals(expectedErrorMessage));
		}
	}	
	
	@Test
	public void noNegativeValuesOnList() throws IOException{
		List<Double> entryList = new ArrayList<Double>();
		entryList = mock.getListOfDoubles(PATH + "noNegativeValuesOnList");
		
		int expectedResult = 0;
		
		try{
			int curretnResult = CalculateCountOfNegativeValues.calculate(entryList);
			assertTrue("CurrentResult: " + curretnResult + ", ExpectedResult: ", expectedResult == curretnResult);
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	@Test
	public void oneNegativeValueAtBegginingOfList() throws IOException{
		List<Double> entryList = new ArrayList<Double>();
		entryList = mock.getListOfDoubles(PATH + "oneNegativeValueAtBegginingOfList");
		
		int expectedResult = 1;
		
		try{
			int curretnResult = CalculateCountOfNegativeValues.calculate(entryList);
			assertTrue("CurrentResult: " + curretnResult + ", ExpectedResult: ", expectedResult == curretnResult);
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
		
	@Test
	public void oneNegativeValueAtendOfList() throws IOException{
		List<Double> entryList = new ArrayList<Double>();
		entryList = mock.getListOfDoubles(PATH + "oneNegativeValueAtendOfList");
		
		int expectedResult = 1;
		
		try{
			int curretnResult = CalculateCountOfNegativeValues.calculate(entryList);
			assertTrue("CurrentResult: " + curretnResult + ", ExpectedResult: ", expectedResult == curretnResult);
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	@Test
	public void severalNegativeValues() throws IOException{
		List<Double> entryList = new ArrayList<Double>();
		entryList = mock.getListOfDoubles(PATH + "severalNegativeValues");
		
		int expectedResult = 3;
		
		try{
			int curretnResult = CalculateCountOfNegativeValues.calculate(entryList);
			assertTrue("CurrentResult: " + curretnResult + ", ExpectedResult: ", expectedResult == curretnResult);
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	@Test
	public void onlyNegativeValuesOnList() throws IOException{
		List<Double> entryList = new ArrayList<Double>();
		entryList = mock.getListOfDoubles(PATH + "onlyNegativeValuesOnList");
		
		int expectedResult = 6;
		
		try{
			int curretnResult = CalculateCountOfNegativeValues.calculate(entryList);
			assertTrue("CurrentResult: " + curretnResult + ", ExpectedResult: ", expectedResult == curretnResult);
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
}
