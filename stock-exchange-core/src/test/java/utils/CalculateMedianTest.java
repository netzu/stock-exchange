package utils;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.junit.Test;

import data.collector.StockExchangeIllegalStateException;

public class CalculateMedianTest {
	
	final static String PATH = new String("utils/");
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
		double expectedResult = 5;
		
		List<Double> entryList = new ArrayList<Double>();		
		entryList = mock.getListOfDoubles(PATH + "listWithSeveralEntries");
		
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
		entryList = mock.getListOfDoubles(PATH + "CalculateMedianTest_oddNumberOfElementsOnList");
		
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
		entryList = mock.getListOfDoubles(PATH + "CalculateMedianTest_evenNumberOfElementsOnList");
		
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
		entryList = mock.getListOfDoubles(PATH + "CalculateMedianTest_hugeList");
		
		try{
			double currentResult = CalculateMedian.calculate(entryList);
			assertTrue("CurrentResult: " + currentResult + ", ExpectedResult: " + expectedResult, Precision.equalsIncludingNaN(expectedResult, currentResult, 0.01));
		}catch(Exception ex){
			fail("Exception found when not expecting: " + ex.getMessage());
		}
	}
}
