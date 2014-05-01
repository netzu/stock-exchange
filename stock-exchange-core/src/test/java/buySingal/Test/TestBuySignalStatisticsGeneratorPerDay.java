package buySingal.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import buySignalTest.BuySignalStatisticsGeneratorPerDay;
import buySignalTest.BuySingalStatistics;
import buySignalTest.ProfitsFromSignal;
import utils.MocksForTests;

public class TestBuySignalStatisticsGeneratorPerDay {
	DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");	
	final static String PATH = new String("buySignal/TestBuySignalStatisticsGeneratorPerDay/");
	
	MocksForTests mock = new MocksForTests();
	
	@Test
	public void testOfSumOfProfits() throws IOException{
		List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker = new ArrayList<List<ProfitsFromSignal>>();
		int day = 0;
		
		List<ProfitsFromSignal> buySignalOne = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalOne");
		List<ProfitsFromSignal> buySignalTwo = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalTwo");
		List<ProfitsFromSignal> buySignalThree = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalThree");
		
		resultsFromBuySignalForTicker.add(buySignalOne);
		resultsFromBuySignalForTicker.add(buySignalTwo);
		resultsFromBuySignalForTicker.add(buySignalThree);		
		
		BuySignalStatisticsGeneratorPerDay stats = new BuySignalStatisticsGeneratorPerDay();
		BuySingalStatistics results = new BuySingalStatistics();
		BuySingalStatistics expectedResults = new BuySingalStatistics();
		
		expectedResults = mock.getBuySignalStatistics(PATH + "multipleBuySignals_ExpectedResults");
		
		try{
			results = stats.generateStatistics(resultsFromBuySignalForTicker, day);
			assertTrue("Expected profits: " + expectedResults.getSumOfProfits() + " got: " + results.getSumOfProfits(),
					expectedResults.getSumOfProfits()==results.getSumOfProfits());		
		}catch(Exception e){
			fail("Exception when not expected: " + e.getMessage());
		}		
	}
	
	@Test
	public void testOfSumOfPercentage() throws IOException{
		List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker = new ArrayList<List<ProfitsFromSignal>>();
		int day = 0;
		
		List<ProfitsFromSignal> buySignalOne = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalOne");
		List<ProfitsFromSignal> buySignalTwo = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalTwo");
		List<ProfitsFromSignal> buySignalThree = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalThree");
		
		resultsFromBuySignalForTicker.add(buySignalOne);
		resultsFromBuySignalForTicker.add(buySignalTwo);
		resultsFromBuySignalForTicker.add(buySignalThree);		
		
		BuySignalStatisticsGeneratorPerDay stats = new BuySignalStatisticsGeneratorPerDay();
		BuySingalStatistics results = new BuySingalStatistics();
		BuySingalStatistics expectedResults = new BuySingalStatistics();
		
		expectedResults = mock.getBuySignalStatistics(PATH + "multipleBuySignals_ExpectedResults");
		
		try{
			results = stats.generateStatistics(resultsFromBuySignalForTicker, day);
			assertTrue("Expected percentage: " + expectedResults.getSumOfPercentage() + " got: " + results.getSumOfPercentage(),
					expectedResults.getSumOfPercentage()==results.getSumOfPercentage());
		}catch(Exception e){
			fail("Exception when not expected: " + e.getMessage());
		}		
	}
	
	@Test
	public void testAverage() throws IOException{
		List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker = new ArrayList<List<ProfitsFromSignal>>();
		int day = 0;
		
		List<ProfitsFromSignal> buySignalOne = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalOne");
		List<ProfitsFromSignal> buySignalTwo = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalTwo");
		List<ProfitsFromSignal> buySignalThree = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalThree");
		
		resultsFromBuySignalForTicker.add(buySignalOne);
		resultsFromBuySignalForTicker.add(buySignalTwo);
		resultsFromBuySignalForTicker.add(buySignalThree);		
		
		BuySignalStatisticsGeneratorPerDay stats = new BuySignalStatisticsGeneratorPerDay();
		BuySingalStatistics results = new BuySingalStatistics();
		BuySingalStatistics expectedResults = new BuySingalStatistics();
		
		expectedResults = mock.getBuySignalStatistics(PATH + "multipleBuySignals_ExpectedResults");
		
		try{
			results = stats.generateStatistics(resultsFromBuySignalForTicker, day);
			assertTrue("Expected average: " + expectedResults.getAverage() + " got: " + results.getAverage(),
					Math.abs(expectedResults.getAverage()-results.getAverage())<0.000001);
		}catch(Exception e){
			fail("Exception when not expected: " + e.getMessage());
		}
	}
	
	
	@Test
	public void testStandardDeviation() throws IOException{
		List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker = new ArrayList<List<ProfitsFromSignal>>();
		int day = 0;
		
		List<ProfitsFromSignal> buySignalOne = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalOne");
		List<ProfitsFromSignal> buySignalTwo = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalTwo");
		List<ProfitsFromSignal> buySignalThree = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalThree");
		
		resultsFromBuySignalForTicker.add(buySignalOne);
		resultsFromBuySignalForTicker.add(buySignalTwo);
		resultsFromBuySignalForTicker.add(buySignalThree);		
		
		BuySignalStatisticsGeneratorPerDay stats = new BuySignalStatisticsGeneratorPerDay();
		BuySingalStatistics results = new BuySingalStatistics();
		BuySingalStatistics expectedResults = new BuySingalStatistics();
		
		expectedResults = mock.getBuySignalStatistics(PATH + "multipleBuySignals_ExpectedResults");
		
		try{
			results = stats.generateStatistics(resultsFromBuySignalForTicker, day);
			assertTrue("Expected standDev: " + expectedResults.getStandardDeviation() + " got: " + results.getStandardDeviation(), 
					Math.abs(expectedResults.getStandardDeviation()-results.getStandardDeviation())<0.000001);
		}catch(Exception e){
			fail("Exception when not expected: " + e.getMessage());
		}
	}
	
	@Test
	public void testMedian() throws IOException{
		List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker = new ArrayList<List<ProfitsFromSignal>>();
		int day = 0;
		
		List<ProfitsFromSignal> buySignalOne = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalOne");
		List<ProfitsFromSignal> buySignalTwo = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalTwo");
		List<ProfitsFromSignal> buySignalThree = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalThree");
		
		resultsFromBuySignalForTicker.add(buySignalOne);
		resultsFromBuySignalForTicker.add(buySignalTwo);
		resultsFromBuySignalForTicker.add(buySignalThree);		
		
		BuySignalStatisticsGeneratorPerDay stats = new BuySignalStatisticsGeneratorPerDay();
		BuySingalStatistics results = new BuySingalStatistics();
		BuySingalStatistics expectedResults = new BuySingalStatistics();
		
		expectedResults = mock.getBuySignalStatistics(PATH + "multipleBuySignals_ExpectedResults");
		
		try{
			results = stats.generateStatistics(resultsFromBuySignalForTicker, day);
			assertTrue("Expected median: " + expectedResults.getMedian() + " got: " + results.getMedian(), 
					expectedResults.getMedian()==results.getMedian());
		}catch(Exception e){
			fail("Exception when not expected: " + e.getMessage());
		}
	}
	
	@Test
	public void testVariance() throws IOException{
		List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker = new ArrayList<List<ProfitsFromSignal>>();
		int day = 0;
		
		List<ProfitsFromSignal> buySignalOne = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalOne");
		List<ProfitsFromSignal> buySignalTwo = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalTwo");
		List<ProfitsFromSignal> buySignalThree = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalThree");
		
		resultsFromBuySignalForTicker.add(buySignalOne);
		resultsFromBuySignalForTicker.add(buySignalTwo);
		resultsFromBuySignalForTicker.add(buySignalThree);		
		
		BuySignalStatisticsGeneratorPerDay stats = new BuySignalStatisticsGeneratorPerDay();
		BuySingalStatistics results = new BuySingalStatistics();
		BuySingalStatistics expectedResults = new BuySingalStatistics();
		
		expectedResults = mock.getBuySignalStatistics(PATH + "multipleBuySignals_ExpectedResults");
		
		try{
			results = stats.generateStatistics(resultsFromBuySignalForTicker, day);
			assertTrue("Expected variance: " + expectedResults.getVariance() + " got: " + results.getVariance(), 
					Math.abs(expectedResults.getVariance()-results.getVariance())<0.00001);
		}catch(Exception e){
			fail("Exception when not expected: " + e.getMessage());
		}
	}
	
	@Test
	public void testMin() throws IOException{
		List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker = new ArrayList<List<ProfitsFromSignal>>();
		int day = 0;
		
		List<ProfitsFromSignal> buySignalOne = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalOne");
		List<ProfitsFromSignal> buySignalTwo = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalTwo");
		List<ProfitsFromSignal> buySignalThree = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalThree");
		
		resultsFromBuySignalForTicker.add(buySignalOne);
		resultsFromBuySignalForTicker.add(buySignalTwo);
		resultsFromBuySignalForTicker.add(buySignalThree);		
		
		BuySignalStatisticsGeneratorPerDay stats = new BuySignalStatisticsGeneratorPerDay();
		BuySingalStatistics results = new BuySingalStatistics();
		BuySingalStatistics expectedResults = new BuySingalStatistics();
		
		expectedResults = mock.getBuySignalStatistics(PATH + "multipleBuySignals_ExpectedResults");
		
		try{
			results = stats.generateStatistics(resultsFromBuySignalForTicker, day);
			assertTrue("Expected min: " + expectedResults.getMin() + " got: " + results.getMin(), expectedResults.getMin()==results.getMin());
		}catch(Exception e){
			fail("Exception when not expected: " + e.getMessage());
		}
	}
	
	@Test
	public void testMax() throws IOException{
		List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker = new ArrayList<List<ProfitsFromSignal>>();
		int day = 0;
		
		List<ProfitsFromSignal> buySignalOne = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalOne");
		List<ProfitsFromSignal> buySignalTwo = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalTwo");
		List<ProfitsFromSignal> buySignalThree = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalThree");
		
		resultsFromBuySignalForTicker.add(buySignalOne);
		resultsFromBuySignalForTicker.add(buySignalTwo);
		resultsFromBuySignalForTicker.add(buySignalThree);		
		
		BuySignalStatisticsGeneratorPerDay stats = new BuySignalStatisticsGeneratorPerDay();
		BuySingalStatistics results = new BuySingalStatistics();
		BuySingalStatistics expectedResults = new BuySingalStatistics();
		
		expectedResults = mock.getBuySignalStatistics(PATH + "multipleBuySignals_ExpectedResults");
		
		try{
			results = stats.generateStatistics(resultsFromBuySignalForTicker, day);
			assertTrue("Expected max: " + expectedResults.getMax() + " got: " + results.getMax(), expectedResults.getMax()==results.getMax());
		}catch(Exception e){
			fail("Exception when not expected: " + e.getMessage());
		}
	}
	
	@Test
	public void testSumOfNegativeProfits() throws IOException{
		List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker = new ArrayList<List<ProfitsFromSignal>>();
		int day = 0;
		
		List<ProfitsFromSignal> buySignalOne = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalOne");
		List<ProfitsFromSignal> buySignalTwo = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalTwo");
		List<ProfitsFromSignal> buySignalThree = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalThree");
		
		resultsFromBuySignalForTicker.add(buySignalOne);
		resultsFromBuySignalForTicker.add(buySignalTwo);
		resultsFromBuySignalForTicker.add(buySignalThree);		
		
		BuySignalStatisticsGeneratorPerDay stats = new BuySignalStatisticsGeneratorPerDay();
		BuySingalStatistics results = new BuySingalStatistics();
		BuySingalStatistics expectedResults = new BuySingalStatistics();
		
		expectedResults = mock.getBuySignalStatistics(PATH + "multipleBuySignals_ExpectedResults");
		
		try{
			results = stats.generateStatistics(resultsFromBuySignalForTicker, day);
			assertTrue("Expected sum of Negative Profits: " + expectedResults.getSumNegativeProfits() + " got: " + results.getSumNegativeProfits(), 
					expectedResults.getSumNegativeProfits()==results.getSumNegativeProfits());
		}catch(Exception e){
			fail("Exception when not expected: " + e.getMessage());
		}
	}
	
	@Test
	public void testSumOfPositiveProfits() throws IOException{
		List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker = new ArrayList<List<ProfitsFromSignal>>();
		int day = 0;
		
		List<ProfitsFromSignal> buySignalOne = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalOne");
		List<ProfitsFromSignal> buySignalTwo = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalTwo");
		List<ProfitsFromSignal> buySignalThree = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalThree");
		
		resultsFromBuySignalForTicker.add(buySignalOne);
		resultsFromBuySignalForTicker.add(buySignalTwo);
		resultsFromBuySignalForTicker.add(buySignalThree);		
		
		BuySignalStatisticsGeneratorPerDay stats = new BuySignalStatisticsGeneratorPerDay();
		BuySingalStatistics results = new BuySingalStatistics();
		BuySingalStatistics expectedResults = new BuySingalStatistics();
		
		expectedResults = mock.getBuySignalStatistics(PATH + "multipleBuySignals_ExpectedResults");
		
		try{
			results = stats.generateStatistics(resultsFromBuySignalForTicker, day);
			assertTrue("Expected sum of Positive Profits: " + expectedResults.getSumPositiveProfits() + " got: " + results.getSumPositiveProfits(), 
					expectedResults.getSumPositiveProfits()==results.getSumPositiveProfits());
		}catch(Exception e){
			fail("Exception when not expected: " + e.getMessage());
		}
	}
	
	@Test
	public void testCountOfNegativeProfits() throws IOException{
		List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker = new ArrayList<List<ProfitsFromSignal>>();
		int day = 0;
		
		List<ProfitsFromSignal> buySignalOne = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalOne");
		List<ProfitsFromSignal> buySignalTwo = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalTwo");
		List<ProfitsFromSignal> buySignalThree = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalThree");
		
		resultsFromBuySignalForTicker.add(buySignalOne);
		resultsFromBuySignalForTicker.add(buySignalTwo);
		resultsFromBuySignalForTicker.add(buySignalThree);		
		
		BuySignalStatisticsGeneratorPerDay stats = new BuySignalStatisticsGeneratorPerDay();
		BuySingalStatistics results = new BuySingalStatistics();
		BuySingalStatistics expectedResults = new BuySingalStatistics();
		
		expectedResults = mock.getBuySignalStatistics(PATH + "multipleBuySignals_ExpectedResults");
		
		try{
			results = stats.generateStatistics(resultsFromBuySignalForTicker, day);
			assertTrue("Expected count of Negative Profits: " + expectedResults.getCountNegativeProfits() + " got: " + results.getCountNegativeProfits(), 
					expectedResults.getCountNegativeProfits()==results.getCountNegativeProfits());
		}catch(Exception e){
			fail("Exception when not expected: " + e.getMessage());
		}
	}
	
	@Test
	public void testCountOfPositiveProfits() throws IOException{
		List <List<ProfitsFromSignal>> resultsFromBuySignalForTicker = new ArrayList<List<ProfitsFromSignal>>();
		int day = 0;
		
		List<ProfitsFromSignal> buySignalOne = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalOne");
		List<ProfitsFromSignal> buySignalTwo = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalTwo");
		List<ProfitsFromSignal> buySignalThree = mock.getProfitsFromSignal(PATH + "multipleBuySignals_BuySignalThree");
		
		resultsFromBuySignalForTicker.add(buySignalOne);
		resultsFromBuySignalForTicker.add(buySignalTwo);
		resultsFromBuySignalForTicker.add(buySignalThree);		
		
		BuySignalStatisticsGeneratorPerDay stats = new BuySignalStatisticsGeneratorPerDay();
		BuySingalStatistics results = new BuySingalStatistics();
		BuySingalStatistics expectedResults = new BuySingalStatistics();
		
		expectedResults = mock.getBuySignalStatistics(PATH + "multipleBuySignals_ExpectedResults");
		
		try{
			results = stats.generateStatistics(resultsFromBuySignalForTicker, day);
			assertTrue("Expected count of Positive Profits: " + expectedResults.getCountPositiveProfits() + " got: " + results.getCountPositiveProfits(), 
					expectedResults.getCountPositiveProfits()==results.getCountPositiveProfits());
		}catch(Exception e){
			fail("Exception when not expected: " + e.getMessage());
		}
	}
}
