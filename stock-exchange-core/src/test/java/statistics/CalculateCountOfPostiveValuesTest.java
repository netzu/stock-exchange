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

public class CalculateCountOfPostiveValuesTest {
	
	final static String PATH = new String("statistics/countOfPostiveValuesResources/");
	TestBeans mock = new TestBeans();
	
	@Test
	public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		  Constructor<CalculateCountOfPostiveValues> constructor = CalculateCountOfPostiveValues.class.getDeclaredConstructor();
		  assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		  constructor.setAccessible(true);
		  constructor.newInstance();
	}
	
	@Test
	public void emptyList(){
		List<Double> entryList = new ArrayList<Double>();
		String expectedErrorMessage = "Cannot calculate count of postive values from empty list";
		
		try{
			CalculateCountOfPostiveValues.calculate(entryList);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(StockExchangeIllegalStateException ex){
			assertTrue(ex.getMessage().equals(expectedErrorMessage));
		}
	}	
	
	@Test
	public void noPositiveValuesOnList() throws IOException{
		List<Double> entryList = new ArrayList<Double>();
		entryList = mock.getListOfDoubles(PATH + "noPositiveValuesOnList");
		
		int expectedResult = 0;
		
		try{
			int curretnResult = CalculateCountOfPostiveValues.calculate(entryList);
			assertTrue("CurrentResult: " + curretnResult + ", ExpectedResult: ", expectedResult == curretnResult);
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	@Test
	public void onePositiveValueAtBegginingOfList() throws IOException{
		List<Double> entryList = new ArrayList<Double>();
		entryList = mock.getListOfDoubles(PATH + "onePositiveValueAtBegginingOfList");
		
		int expectedResult = 1;
		
		try{
			int curretnResult = CalculateCountOfPostiveValues.calculate(entryList);
			assertTrue("CurrentResult: " + curretnResult + ", ExpectedResult: ", expectedResult == curretnResult);
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
		
	@Test
	public void onePositiveValueAtendOfList() throws IOException{
		List<Double> entryList = new ArrayList<Double>();
		entryList = mock.getListOfDoubles(PATH + "onePositiveValueAtendOfList");
		
		int expectedResult = 1;
		
		try{
			int curretnResult = CalculateCountOfPostiveValues.calculate(entryList);
			assertTrue("CurrentResult: " + curretnResult + ", ExpectedResult: ", expectedResult == curretnResult);
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	@Test
	public void severalPositiveValues() throws IOException{
		List<Double> entryList = new ArrayList<Double>();
		entryList = mock.getListOfDoubles(PATH + "severalPositiveValues");
		
		int expectedResult = 3;
		
		try{
			int curretnResult = CalculateCountOfPostiveValues.calculate(entryList);
			assertTrue("CurrentResult: " + curretnResult + ", ExpectedResult: ", expectedResult == curretnResult);
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	@Test
	public void onlyPositiveValuesOnList() throws IOException{
		List<Double> entryList = new ArrayList<Double>();
		entryList = mock.getListOfDoubles(PATH + "onlyPositiveValuesOnList");
		
		int expectedResult = 6;
		
		try{
			int curretnResult = CalculateCountOfPostiveValues.calculate(entryList);
			assertTrue("CurrentResult: " + curretnResult + ", ExpectedResult: ", expectedResult == curretnResult);
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
}
