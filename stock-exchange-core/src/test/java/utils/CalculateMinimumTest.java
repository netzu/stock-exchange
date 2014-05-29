package utils;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.junit.Test;

import data.collector.StockExchangeIllegalStateException;

public class CalculateMinimumTest {

	final static String PATH = new String("utils/calculateMinimumResources/");
	MocksForTests mock = new MocksForTests();

	@Test
	public void emptyList(){
		List<Double> entryList = new ArrayList<Double>();
		String expectedErrorMessage = "Cannot calculate minimum value from empty list";
		
		try{
			CalculateMinimum.calculate(entryList);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(StockExchangeIllegalStateException ex){
			assertTrue(ex.getMessage().equals(expectedErrorMessage));
		}
	}
	
	
	@Test
	public void minimumValueFirstOnList() throws IOException{
		List<Double> entryList = new ArrayList<Double>();
		entryList = mock.getListOfDoubles(PATH + "minimumValueFirstOnList");
		
		double expectedResult = -56.33;
		
		try{
			double curretnResult = CalculateMinimum.calculate(entryList);
			assertTrue("CurrentResult: " + curretnResult + ", ExpectedResult: " + expectedResult, Precision.equalsIncludingNaN(expectedResult, curretnResult, 0.01));
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	@Test
	public void minimumValueLastOnList() throws IOException{
		List<Double> entryList = new ArrayList<Double>();
		entryList = mock.getListOfDoubles(PATH + "minimumValueLastOnList");
		
		double expectedResult = -15.36;
		
		try{
			double curretnResult = CalculateMinimum.calculate(entryList);
			assertTrue("CurrentResult: " + curretnResult + ", ExpectedResult: " + expectedResult, Precision.equalsIncludingNaN(expectedResult, curretnResult, 0.01));
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	@Test
	public void twoLocalMinimumsOnList() throws IOException{
		List<Double> entryList = new ArrayList<Double>();
		entryList = mock.getListOfDoubles(PATH + "twoLocalMinimumsOnList");
		
		double expectedResult = -0.11;
		
		try{
			double curretnResult = CalculateMinimum.calculate(entryList);
			assertTrue("CurrentResult: " + curretnResult + ", ExpectedResult: " + expectedResult, Precision.equalsIncludingNaN(expectedResult, curretnResult, 0.01));
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
}
