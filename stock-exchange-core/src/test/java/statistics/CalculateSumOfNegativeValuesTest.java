package statistics;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.junit.Test;

import statistics.CalculateSumOfNegativeValues;
import utils.MocksForTests;
import data.collector.StockExchangeIllegalStateException;

public class CalculateSumOfNegativeValuesTest {
	
	final static String PATH = new String("statistics/calculateSumOfNegativeValuesResources/");
	MocksForTests mock = new MocksForTests();
	
	@Test
	public void emptyList(){
		List<Double> entryList = new ArrayList<Double>();
		String expectedErrorMessage = "Cannot calculate sum of negative values from empty list";
		
		try{
			CalculateSumOfNegativeValues.calculate(entryList);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(StockExchangeIllegalStateException ex){
			assertTrue(ex.getMessage().equals(expectedErrorMessage));
		}
	}
	
	@Test
	public void severalNegativeValues() throws IOException{
		List<Double> entryList = new ArrayList<Double>();
		entryList = mock.getListOfDoubles(PATH + "severalNegativeValues");
		
		double expectedResult = -128.19;
		
		try{
			double curretnResult = CalculateSumOfNegativeValues.calculate(entryList);
			assertTrue("CurrentResult: " + curretnResult + ", ExpectedResult: " + expectedResult, Precision.equalsIncludingNaN(expectedResult, curretnResult, 0.01));
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
}
