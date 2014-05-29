package utils;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.junit.Test;

import data.collector.StockExchangeIllegalStateException;

public class CalculateVarianceTest {
	
	final static String PATH = new String("utils/calculateVarianceResources/");
	MocksForTests mock = new MocksForTests();
	
	@Test
	public void emptyList(){
		List<Double> entryList = new ArrayList<Double>();
		String expectedErrorMessage = "Cannot calculate variance for empty list";
		
		try{
			CalculateVariance.calculate(entryList);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(StockExchangeIllegalStateException ex){
			assertTrue(ex.getMessage().equals(expectedErrorMessage));
		}
	}
	
	@Test
	public void varianceEqualZero() throws IOException{
		double expectedResult = 0.0000;
		
		List<Double> entryList = new ArrayList<Double>();		
		entryList = mock.getListOfDoubles(PATH + "varianceEqualZero");
		
		try{
			double currentResult = CalculateVariance.calculate(entryList);
			assertTrue("CurrentResult: " + currentResult + ", ExpectedResult: " + expectedResult, Precision.equalsIncludingNaN(expectedResult, currentResult, 0.0001));
		}catch(Exception ex){
			fail("Exception found when not expecting: " + ex.getMessage());
		}		
	}
	
	@Test
	public void checkCorrectnessOfResults() throws IOException{
		double expectedResult = 24.0196;
		
		List<Double> entryList = new ArrayList<Double>();		
		entryList = mock.getListOfDoubles(PATH + "checkCorrectnessOfResults");
		
		try{
			double currentResult = CalculateVariance.calculate(entryList);
			assertTrue("CurrentResult: " + currentResult + ", ExpectedResult: " + expectedResult, Precision.equalsIncludingNaN(expectedResult, currentResult, 0.0001));
		}catch(Exception ex){
			fail("Exception found when not expecting: " + ex.getMessage());
		}	
	}
	
}
