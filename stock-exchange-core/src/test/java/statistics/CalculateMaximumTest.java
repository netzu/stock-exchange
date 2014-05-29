package statistics;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.junit.Test;

import statistics.CalculateMaximum;
import utils.MocksForTests;
import data.collector.StockExchangeIllegalStateException;

public class CalculateMaximumTest {
	final static String PATH = new String("statistics/calculateMaximumResources/");
	MocksForTests mock = new MocksForTests();

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
