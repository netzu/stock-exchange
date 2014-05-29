package statistics;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.junit.Test;

import statistics.CalculateMedian;
import utils.MocksForTests;
import data.collector.StockExchangeIllegalStateException;

public class CalculateMedianTest {
	
	final static String PATH = new String("statistics/calculateMedianResources/");
	MocksForTests mock = new MocksForTests();
	
	@Test
	public void emptyList(){
		List<Double> entryList = new ArrayList<Double>();
		String expectedErrorMessage = "Cannot calculate median for empty list";
		
		try{
			CalculateMedian.calculate(entryList);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(StockExchangeIllegalStateException ex){
			assertTrue(ex.getMessage().equals(expectedErrorMessage));
		}
	}
	
	@Test
	public void istWithOneElementOnly() throws IOException{
		double expectedResult = 5.22;
		
		List<Double> entryList = new ArrayList<Double>();		
		entryList.add(expectedResult);
		
		try{
			double currentResult = CalculateMedian.calculate(entryList);
			assertTrue("CurrentResult: " + currentResult + ", ExpectedResult: ", Precision.equalsIncludingNaN(expectedResult, currentResult, 0.01));
		}catch(Exception ex){
			fail("Exception found when not expecting: " + ex.getMessage());
		}		
	}
	
	@Test
	public void oddNumberOfElementsOnList() throws IOException{
		double expectedResult = 5.98;
		
		List<Double> entryList = new ArrayList<Double>();		
		entryList = mock.getListOfDoubles(PATH + "oddNumberOfElementsOnList");
		
		try{
			double currentResult = CalculateMedian.calculate(entryList);
			assertTrue("CurrentResult: " + currentResult + ", ExpectedResult: " + expectedResult, Precision.equalsIncludingNaN(expectedResult, currentResult, 0.01));
		}catch(Exception ex){
			fail("Exception found when not expecting: " + ex.getMessage());
		}
	}
	
	@Test
	public void evenNumberOfElementsOnList() throws IOException{
		double expectedResult = 6.67;
		
		List<Double> entryList = new ArrayList<Double>();		
		entryList = mock.getListOfDoubles(PATH + "evenNumberOfElementsOnList");
		
		try{
			double currentResult = CalculateMedian.calculate(entryList);
			assertTrue("CurrentResult: " + currentResult + ", ExpectedResult: " + expectedResult, Precision.equalsIncludingNaN(expectedResult, currentResult, 0.01));
		}catch(Exception ex){
			fail("Exception found when not expecting: " + ex.getMessage());
		}
	}
	
	@Test
	public void hugeList() throws IOException{
		double expectedResult = 984.275;
		
		List<Double> entryList = new ArrayList<Double>();		
		entryList = mock.getListOfDoubles(PATH + "hugeList");
		
		try{
			double currentResult = CalculateMedian.calculate(entryList);
			assertTrue("CurrentResult: " + currentResult + ", ExpectedResult: " + expectedResult, Precision.equalsIncludingNaN(expectedResult, currentResult, 0.01));
		}catch(Exception ex){
			fail("Exception found when not expecting: " + ex.getMessage());
		}
	}
}
