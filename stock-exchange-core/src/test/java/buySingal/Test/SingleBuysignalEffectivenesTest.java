package buySingal.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.commons.math3.util.Precision;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import data.collector.StockTickerHistory;
import buySignalTest.BuySignalEffectivenessException;
import buySignalTest.SingleBuySignalEffectiveness;
import utils.MocksForTests;

public class SingleBuysignalEffectivenesTest {
	DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");	
	final static String PATH = new String("buySignal/singleBuysignalEffectivenesTest/");
	
	MocksForTests mock = new MocksForTests();
	
	//input for this method have two true values and one false, the results should be true
	@Test
	public void wasSignalCorrect_001() throws IOException{
		ArrayList<Boolean> resultsOfBuySignal = new ArrayList<Boolean>();
		
		resultsOfBuySignal = mock.getCorrectSignals(PATH + "wasSignalCorrect_001");
		SingleBuySignalEffectiveness effectivenes = new SingleBuySignalEffectiveness();
		
		boolean result = effectivenes.wasSignalCorrect(resultsOfBuySignal);
		
		assertTrue(result == true);		
		assertTrue(resultsOfBuySignal.size()== 3);
	}
	
	//input for this method have 5 false values should return false
	@Test
	public void wasSignalCorrect_002() throws IOException{
		ArrayList<Boolean> resultsOfBuySignal = new ArrayList<Boolean>();
		
		resultsOfBuySignal = mock.getCorrectSignals(PATH + "wasSignalCorrect_002");
		SingleBuySignalEffectiveness effectivenes = new SingleBuySignalEffectiveness();
		
		boolean result = effectivenes.wasSignalCorrect(resultsOfBuySignal);
		
		assertTrue(result == false);
		assertTrue(resultsOfBuySignal.size()==5);
	}
	
	//input for this method have 1 true value 
	@Test
	public void wasSignalCorrect_003() throws IOException{
		ArrayList<Boolean> resultsOfBuySignal = new ArrayList<Boolean>();
		
		resultsOfBuySignal = mock.getCorrectSignals(PATH + "wasSignalCorrect_003");
		SingleBuySignalEffectiveness effectivenes = new SingleBuySignalEffectiveness();
		
		boolean result = effectivenes.wasSignalCorrect(resultsOfBuySignal);
		
		assertTrue(result == true);		
		assertTrue(resultsOfBuySignal.size()== 1);
	}
	
	//input for this method have 1 false value 
	@Test
	public void wasSignalCorrect_004() throws IOException{
		ArrayList<Boolean> resultsOfBuySignal = new ArrayList<Boolean>();
		
		resultsOfBuySignal = mock.getCorrectSignals(PATH + "wasSignalCorrect_004");
		SingleBuySignalEffectiveness effectivenes = new SingleBuySignalEffectiveness();
		
		boolean result = effectivenes.wasSignalCorrect(resultsOfBuySignal);
		
		assertTrue(result == false);		
		assertTrue(resultsOfBuySignal.size()== 1);
	}
	
	//empty list
	@Test
	public void wasSignalCorrect_005() throws IOException{
		ArrayList<Boolean> resultsOfBuySignal = new ArrayList<Boolean>();
		
		resultsOfBuySignal = mock.getCorrectSignals(PATH + "wasSignalCorrect_005");
		SingleBuySignalEffectiveness effectivenes = new SingleBuySignalEffectiveness();
		
		String expectedErrorMessage = "List with results for each day is empty";
		
		try{
			effectivenes.wasSignalCorrect(resultsOfBuySignal);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(BuySignalEffectivenessException ex){
			assertTrue("Exception message is diffrent that expected. Expected: " + expectedErrorMessage + ". Got: " + ex.getMessage(), ex.getMessage().equals(expectedErrorMessage));
		}
	}
	
	//number of days equal 0, expecting an exception
	@Test
	public void daysWithPositiveResults_01() throws ParseException{
		
		int days = 0;
		DateTime buySignal = dateFormater.parseDateTime("20100315");
		StockTickerHistory collection = new StockTickerHistory();
		collection = mock.readTickerData(PATH + "daysWithPositiveResults_tickerData_01");
		
		SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
		String expectedErrorMessage = "Number days in test cannot be 0";
		
		try{
			signalEffectivnes.daysWithPositiveResults(days, buySignal, collection);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(BuySignalEffectivenessException ex){
			assertTrue("Exception message is diffrent that expected. Expected: " + expectedErrorMessage + ". Got: " + ex.getMessage(), ex.getMessage().equals(expectedErrorMessage));
		}
	}
	
	//stock ticker empty
	@Test
	public void daysWithPositiveResults_02() throws ParseException{
		
		int days = 3;
		DateTime buySignal = dateFormater.parseDateTime("20100315");
		StockTickerHistory collection = new StockTickerHistory();
		collection = mock.readTickerData(PATH + "daysWithPositiveResults_tickerData_02");
		
		SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
		String expectedErrorMessage = "Ticker collection cannot be empty";
		
		try{
			signalEffectivnes.daysWithPositiveResults(days, buySignal, collection);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(BuySignalEffectivenessException ex){
			assertTrue("Exception message is diffrent that expected. Expected: " + expectedErrorMessage + ". Got: " + ex.getMessage(), ex.getMessage().equals(expectedErrorMessage));
		}
	}
	
	//checking if the correct values has been determines
	@Test
	public void daysWithPositiveResults_03() throws ParseException, IOException{
		
		int days = 7;
		DateTime buySignal = dateFormater.parseDateTime("20100315");
		StockTickerHistory collection = new StockTickerHistory();
		collection = mock.readTickerData(PATH + "daysWithPositiveResults_tickerData_03");
		
		ArrayList<Boolean> expectedResults = new ArrayList<Boolean>();		
		expectedResults = mock.getCorrectSignals(PATH + "daysWithPositiveResults_expectedResults_03");
		
		try{
			SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
			ArrayList<Boolean> currentResults = signalEffectivnes.daysWithPositiveResults(days, buySignal, collection);
			assertTrue(currentResults.equals(expectedResults));
		}catch(Exception ex){
			fail("Exception when not excpected: " + ex.getMessage());
		}		
	}
	
	//more days to test than available
	@Test
	public void daysWithPositiveResults_04() throws ParseException, IOException{
		
		int days = 8;
		DateTime buySignal = dateFormater.parseDateTime("20100315");
		StockTickerHistory collection = new StockTickerHistory();
		collection = mock.readTickerData(PATH + "daysWithPositiveResults_tickerData_04");
		
		ArrayList<Boolean> expectedResults = new ArrayList<Boolean>();		
		expectedResults = mock.getCorrectSignals(PATH + "daysWithPositiveResults_expectedResults_04");
		
		try{
			SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
			ArrayList<Boolean> currentResults = signalEffectivnes.daysWithPositiveResults(days, buySignal, collection);
			assertTrue(currentResults.equals(expectedResults));
		}catch(Exception ex){
			fail("Exception when not excpected: " + ex.getMessage());
		}		
	}
	
	//buy day in the last session day
	@Test
	public void daysWithPositiveResults_05() throws ParseException, IOException{
		
		int days = 3;
		DateTime buySignal = dateFormater.parseDateTime("20100322");
		StockTickerHistory collection = new StockTickerHistory();
		collection = mock.readTickerData(PATH + "daysWithPositiveResults_tickerData_05");
		
		ArrayList<Boolean> expectedResults = new ArrayList<Boolean>();		
		expectedResults = mock.getCorrectSignals(PATH + "daysWithPositiveResults_expectedResults_05");
		
		try{
			SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
			ArrayList<Boolean> currentResults = signalEffectivnes.daysWithPositiveResults(days, buySignal, collection);
			assertTrue(currentResults.equals(expectedResults));
			assertTrue(currentResults.size() == 0);
		}catch(Exception ex){
			fail("Exception when not excpected: " + ex.getMessage());
		}		
	}
	
	//number of days equal 0, expecting an exception
	@Test
	public void percentageGained_01() throws ParseException{
		
		int days = 0;
		DateTime buySignal = dateFormater.parseDateTime("20100315");
		StockTickerHistory collection = new StockTickerHistory();
		collection = mock.readTickerData(PATH + "percentageGained_01");
		
		SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
		String expectedErrorMessage = "Number days in test cannot be 0";
		
		try{
			signalEffectivnes.percentageGainPerDay(days, buySignal, collection);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(BuySignalEffectivenessException ex){
			assertTrue("Exception message is diffrent that expected. Expected: " + expectedErrorMessage + ". Got: " + ex.getMessage(), ex.getMessage().equals(expectedErrorMessage));
		}
	}
	
	//stock ticker empty
	@Test
	public void percentageGained_02() throws ParseException{
		
		int days = 3;
		DateTime buySignal = dateFormater.parseDateTime("20100315");
		StockTickerHistory collection = new StockTickerHistory();
		collection = mock.readTickerData(PATH + "percentageGained_02");
		
		SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
		String expectedErrorMessage = "Ticker collection cannot be empty";
		
		try{
			signalEffectivnes.percentageGainPerDay(days, buySignal, collection);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(BuySignalEffectivenessException ex){
			assertTrue("Exception message is diffrent that expected. Expected: " + expectedErrorMessage + ". Got: " + ex.getMessage(), ex.getMessage().equals(expectedErrorMessage));
		}
	}
	
	//current price and buy price is same
	@Test
	public void percentageGained_03() throws ParseException, IOException{
		
		int days = 7;
		DateTime buySignal = dateFormater.parseDateTime("20100315");
		StockTickerHistory collection = new StockTickerHistory();
		collection = mock.readTickerData(PATH + "percentageGained_tickerData_03");
		
		ArrayList<Double> expectedResults = new ArrayList<Double>();		
		expectedResults = mock.getGainedPercentag(PATH + "percentageGained_ExpectedResults_03");
		
		boolean result = false;
		
		try{
			SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
			ArrayList<Double> currentResults = signalEffectivnes.percentageGainPerDay(days, buySignal, collection);
			
			for(int i=0; i < expectedResults.size(); i++){
				result = Precision.equalsIncludingNaN(currentResults.get(i), expectedResults.get(i), 0.0001);
				
				if(!result){
					result = false;
					break;		
				}
			}
			assertTrue(result);
			assertTrue(currentResults.size() == 7);
		}catch(Exception ex){
			fail("Exception when not excpected: " + ex.getMessage());
		}		
	}
	
	//more days to test than available
	@Test
	public void percentageGained_04() throws ParseException, IOException{
		
		int days = 10;
		DateTime buySignal = dateFormater.parseDateTime("20100315");
		StockTickerHistory collection = new StockTickerHistory();
		collection = mock.readTickerData(PATH + "percentageGained_tickerData_04");
		
		ArrayList<Double> expectedResults = new ArrayList<Double>();		
		expectedResults = mock.getGainedPercentag(PATH + "percentageGained_ExpectedResults_04");
		
		boolean result = false;
		
		try{
			SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
			ArrayList<Double> currentResults = signalEffectivnes.percentageGainPerDay(days, buySignal, collection);
			
			for(int i=0; i < expectedResults.size(); i++){
				result = Precision.equalsIncludingNaN(currentResults.get(i), expectedResults.get(i), 0.0001);
				
				if(!result){
					result = false;
					break;		
				}
			}
			assertTrue(result);
		}catch(Exception ex){
			fail("Exception when not excpected: " + ex.getMessage());
		}		
	}
	
	//buy day in the last day of session
	@Test
	public void percentageGained_05() throws ParseException, IOException{
		
		int days = 3;
		DateTime buySignal = dateFormater.parseDateTime("20100322");
		StockTickerHistory collection = new StockTickerHistory();
		collection = mock.readTickerData(PATH + "percentageGained_tickerData_05");
		
		ArrayList<Double> expectedResults = new ArrayList<Double>();		
		expectedResults = mock.getGainedPercentag(PATH + "percentageGained_ExpectedResults_05");
		
		try{
			SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
			ArrayList<Double> currentResults = signalEffectivnes.percentageGainPerDay(days, buySignal, collection);

			assertTrue(currentResults.isEmpty());
			assertTrue(expectedResults.equals(currentResults));
			
		}catch(Exception ex){
			fail("Exception when not excpected: " + ex.getMessage());
		}		
	}
	
}
