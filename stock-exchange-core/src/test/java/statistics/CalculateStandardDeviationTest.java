package statistics;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.junit.Test;

import statistics.CalculateStandardDeviation;
import utils.MocksForTests;
import data.collector.StockExchangeIllegalStateException;

public class CalculateStandardDeviationTest {
	
	final static String PATH = new String("statistics/");
	MocksForTests mock = new MocksForTests();

	@Test
	public void emptyList(){
		List<Double> entryList = new ArrayList<Double>();
		String expectedErrorMessage = "Cannot calculate standard deviation for empty list";
		
		try{
			CalculateStandardDeviation.calculate(entryList);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(StockExchangeIllegalStateException ex){
			assertTrue(ex.getMessage().equals(expectedErrorMessage));
		}
	}
	
	@Test
	public void onlyOneElementOnList(){
		List<Double> entryList = new ArrayList<Double>();
		entryList.add(-56.33);
		
		double expectedResult = 0.00;
		
		try{
			double curretnResult = CalculateStandardDeviation.calculate(entryList);
			assertTrue("CurrentResult: " + curretnResult + ", ExpectedResult: ", Precision.equalsIncludingNaN(expectedResult, curretnResult, 0.01));
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	
	@Test
	public void checkIfResultsAreCorrect() throws IOException{
		List<Double> entryList = new ArrayList<Double>();
		entryList = mock.getListOfDoubles(PATH + "CalculateStandardDeviationTest_checkIfResultsAreCorrect");
		
		double expectedResult = 22.23576;
		
		try{
			double curretnResult = CalculateStandardDeviation.calculate(entryList);
			assertTrue("CurrentResult: " + curretnResult + ", ExpectedResult: ", Precision.equalsIncludingNaN(expectedResult, curretnResult, 0.00001));
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
}
