package statistics;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.junit.Test;

import statistics.CalculateSumOfPositiveValues;
import utils.MocksForTests;
import data.collector.StockExchangeIllegalStateException;

public class CalculateSumOfPositiveValuesTest {
	
	final static String PATH = new String("statistics/calculateSumOfPositiveValuesResources/");
	MocksForTests mock = new MocksForTests();
	
	@Test
	public void emptyList(){
		List<Double> entryList = new ArrayList<Double>();
		String expectedErrorMessage = "Cannot calculate sum of positive values for empty list";
		
		try{
			CalculateSumOfPositiveValues.calculate(entryList);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(StockExchangeIllegalStateException ex){
			assertTrue(ex.getMessage().equals(expectedErrorMessage));
		}
	}
	
	@Test
	public void severalPositiveValues() throws IOException{
		List<Double> entryList = new ArrayList<Double>();
		entryList = mock.getListOfDoubles(PATH + "severalPositiveValues");
		
		double expectedResult = 32.69;
		
		try{
			double curretnResult = CalculateSumOfPositiveValues.calculate(entryList);
			assertTrue("CurrentResult: " + curretnResult + ", ExpectedResult: " + expectedResult, Precision.equalsIncludingNaN(expectedResult, curretnResult, 0.01));
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}

}
