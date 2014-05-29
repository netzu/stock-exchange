package buy.signal.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import data.collector.StockExchangeIllegalStateException;
import data.collector.StockTickerHistory;
import utils.MocksForTests;

public class SingleBuysignalEffectivenesTest {
	DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");	
	final static String PATH = new String("buySignal/singleBuysignalEffectivenesTest/");
	
	MocksForTests mock = new MocksForTests();
	
	//is checking if list with current results contains the sam evalues on teh same positions as expected results
	private boolean checkIfListAreEqual(List<Double> expectedResults, boolean result, List<Double> currentResults) {
		//TBD - zucic wyjatek jak nie sa rowne
		for(int i=0; i < expectedResults.size(); i++){
			result = Precision.equalsIncludingNaN(currentResults.get(i), expectedResults.get(i), 0.0001);
			
			if(!result){
				result = false;
				break;		
			}
		}
		return result;
	}
	
	//input for this method have two true values and one false, the results should be true
	@Test
	public void wasSignalCorrect_001() throws IOException{
		List<Boolean> resultsOfBuySignal = new ArrayList<Boolean>();
		
		resultsOfBuySignal = mock.getCorrectSignals(PATH + "wasSignalCorrect_001");
		SingleBuySignalEffectiveness effectivenes = new SingleBuySignalEffectiveness();
		
		boolean result = effectivenes.wasSignalCorrect(resultsOfBuySignal);
		
		assertTrue(result == true);		
		assertTrue(resultsOfBuySignal.size()== 3);
	}
	
	//input for this method have 5 false values should return false
	@Test
	public void wasSignalCorrect_002() throws IOException{
		List<Boolean> resultsOfBuySignal = new ArrayList<Boolean>();
		
		resultsOfBuySignal = mock.getCorrectSignals(PATH + "wasSignalCorrect_002");
		SingleBuySignalEffectiveness effectivenes = new SingleBuySignalEffectiveness();
		
		boolean result = effectivenes.wasSignalCorrect(resultsOfBuySignal);
		
		assertTrue(result == false);
		assertTrue(resultsOfBuySignal.size()==5);
	}
	
	//input for this method have 1 true value 
	@Test
	public void wasSignalCorrect_003() throws IOException{
		List<Boolean> resultsOfBuySignal = new ArrayList<Boolean>();
		
		resultsOfBuySignal = mock.getCorrectSignals(PATH + "wasSignalCorrect_003");
		SingleBuySignalEffectiveness effectivenes = new SingleBuySignalEffectiveness();
		
		boolean result = effectivenes.wasSignalCorrect(resultsOfBuySignal);
		
		assertTrue(result == true);		
		assertTrue(resultsOfBuySignal.size()== 1);
	}
	
	//input for this method have 1 false value 
	@Test
	public void wasSignalCorrect_004() throws IOException{
		List<Boolean> resultsOfBuySignal = new ArrayList<Boolean>();
		
		resultsOfBuySignal = mock.getCorrectSignals(PATH + "wasSignalCorrect_004");
		SingleBuySignalEffectiveness effectivenes = new SingleBuySignalEffectiveness();
		
		boolean result = effectivenes.wasSignalCorrect(resultsOfBuySignal);
		
		assertTrue(result == false);		
		assertTrue(resultsOfBuySignal.size()== 1);
	}
	
	//empty list
	@Test
	public void wasSignalCorrect_005() throws IOException{
		List<Boolean> resultsOfBuySignal = new ArrayList<Boolean>();
		
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
		
		List<Boolean> expectedResults = new ArrayList<Boolean>();		
		expectedResults = mock.getCorrectSignals(PATH + "daysWithPositiveResults_expectedResults_03");
		
		try{
			SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
			List<Boolean> currentResults = signalEffectivnes.daysWithPositiveResults(days, buySignal, collection);
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
		
		List<Boolean> expectedResults = new ArrayList<Boolean>();		
		expectedResults = mock.getCorrectSignals(PATH + "daysWithPositiveResults_expectedResults_04");
		
		try{
			SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
			List<Boolean> currentResults = signalEffectivnes.daysWithPositiveResults(days, buySignal, collection);
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
		
		List<Boolean> expectedResults = new ArrayList<Boolean>();		
		expectedResults = mock.getCorrectSignals(PATH + "daysWithPositiveResults_expectedResults_05");
		
		try{
			SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
			List<Boolean> currentResults = signalEffectivnes.daysWithPositiveResults(days, buySignal, collection);
			assertTrue(currentResults.equals(expectedResults));
			assertTrue(currentResults.size() == 0);
		}catch(Exception ex){
			fail("Exception when not excpected: " + ex.getMessage());
		}		
	}
	
	//chech if test correct number of days
	@Test
	public void daysWithPositiveResults_06() throws ParseException, IOException{
		
		int days = 7;
		DateTime buySignal = dateFormater.parseDateTime("20100315");
		StockTickerHistory collection = new StockTickerHistory();
		collection = mock.readTickerData(PATH + "daysWithPositiveResults_tickerData_06");
		
		List<Boolean> expectedResults = new ArrayList<Boolean>();		
		expectedResults = mock.getCorrectSignals(PATH + "daysWithPositiveResults_expectedResults_06");
		
		try{
			SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
			List<Boolean> currentResults = signalEffectivnes.daysWithPositiveResults(days, buySignal, collection);
			assertTrue(currentResults.equals(expectedResults));
			assertTrue(currentResults.size() == 7);
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
		collection = mock.readTickerData(PATH + "percentageGained_tickerData_01");
		
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
		collection = mock.readTickerData(PATH + "percentageGained_tickerData_02");
		
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
		
		List<Double> expectedResults = new ArrayList<Double>();		
		expectedResults = mock.getListOfDoubles(PATH + "percentageGained_ExpectedResults_03");
		
		boolean result = false;
		
		try{
			SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
			List<Double> currentResults = signalEffectivnes.percentageGainPerDay(days, buySignal, collection);
			
			result = checkIfListAreEqual(expectedResults, result, currentResults);
			
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
		
		List<Double> expectedResults = new ArrayList<Double>();		
		expectedResults = mock.getListOfDoubles(PATH + "percentageGained_ExpectedResults_04");
		
		boolean result = false;
		
		try{
			SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
			List<Double> currentResults = signalEffectivnes.percentageGainPerDay(days, buySignal, collection);
			
			result = checkIfListAreEqual(expectedResults, result, currentResults);
			assertTrue(result);
		}catch(Exception ex){
			fail("Exception when not excpected: " + ex.getMessage());
		}		
	}
	
	//chech if test correct number of days
	@Test
	public void percentageGained_05() throws ParseException, IOException{
		
		int days = 4;
		DateTime buySignal = dateFormater.parseDateTime("20100315");
		StockTickerHistory collection = new StockTickerHistory();
		collection = mock.readTickerData(PATH + "percentageGained_tickerData_05");
		
		List<Double> expectedResults = new ArrayList<Double>();		
		expectedResults = mock.getListOfDoubles(PATH + "percentageGained_ExpectedResults_05");
		
		boolean result = false;
		
		try{
			SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
			List<Double> currentResults = signalEffectivnes.percentageGainPerDay(days, buySignal, collection);
			
			result = checkIfListAreEqual(expectedResults, result, currentResults);
			
			assertTrue(result);
			assertTrue(expectedResults.size()==currentResults.size());
			assertTrue(currentResults.size()==days);
		}catch(Exception ex){
			fail("Exception when not excpected: " + ex.getMessage());
		}		
	}

	//buy day in the last day of session
	@Test
	public void valueGain_01() throws ParseException, IOException{
		
		int days = 0;
		DateTime buySignal = dateFormater.parseDateTime("20100315");
		StockTickerHistory collection = new StockTickerHistory();
		collection = mock.readTickerData(PATH + "valueGain_tickerData_01");
		
		SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
		String expectedErrorMessage = "Number days in test cannot be 0";
		
		try{
			signalEffectivnes.valueGainPerDay(days, buySignal, collection);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(BuySignalEffectivenessException ex){
			assertTrue("Exception message is diffrent that expected. Expected: " + expectedErrorMessage + ". Got: " + ex.getMessage(), ex.getMessage().equals(expectedErrorMessage));
		}	
	}
	
	//stock ticker empty
	@Test
	public void valueGain_02() throws ParseException{
		
		int days = 3;
		DateTime buySignal = dateFormater.parseDateTime("20100315");
		StockTickerHistory collection = new StockTickerHistory();
		collection = mock.readTickerData(PATH + "valueGain_tickerData_02");
		
		SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
		String expectedErrorMessage = "Ticker collection cannot be empty";
		
		try{
			signalEffectivnes.valueGainPerDay(days, buySignal, collection);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(BuySignalEffectivenessException ex){
			assertTrue("Exception message is diffrent that expected. Expected: " + expectedErrorMessage + ". Got: " + ex.getMessage(), ex.getMessage().equals(expectedErrorMessage));
		}
	}
	
	//more days to test than available
	@Test
	public void valueGain_03() throws ParseException, IOException{
		
		int days = 10;
		DateTime buySignal = dateFormater.parseDateTime("20100315");
		StockTickerHistory collection = new StockTickerHistory();
		collection = mock.readTickerData(PATH + "valueGain_tickerData_03");
		
		List<Double> expectedResults = new ArrayList<Double>();		
		expectedResults = mock.getListOfDoubles(PATH + "valueGain_ExpectedResults_03");
		
		boolean result = false;
		
		try{
			SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
			List<Double> currentResults = signalEffectivnes.valueGainPerDay(days, buySignal, collection);
			
			result = checkIfListAreEqual(expectedResults, result, currentResults);
			assertTrue(expectedResults.size() == currentResults.size());
			assertTrue(result);
		}catch(Exception ex){
			fail("Exception when not excpected: " + ex.getMessage());
		}
	}
	
	@Test
	public void valueGain_04() throws ParseException, IOException{
		
		int days = 10;
		DateTime buySignal = dateFormater.parseDateTime("20100315");
		StockTickerHistory collection = new StockTickerHistory();
		collection = mock.readTickerData(PATH + "valueGain_tickerData_04");
		
		List<Double> expectedResults = new ArrayList<Double>();		
		expectedResults = mock.getListOfDoubles(PATH + "valueGain_ExpectedResults_04");
		
		boolean result = false;
		
		try{
			SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
			List<Double> currentResults = signalEffectivnes.valueGainPerDay(days, buySignal, collection);
			
			result = checkIfListAreEqual(expectedResults, result, currentResults);
			assertTrue(expectedResults.size() == currentResults.size());
			assertTrue(result);
		}catch(Exception ex){
			fail("Exception when not excpected: " + ex.getMessage());
		}
	}
	
	@Test
	public void valueGain_05() throws ParseException, IOException{
		
		int days = 5;
		DateTime buySignal = dateFormater.parseDateTime("20100315");
		StockTickerHistory collection = new StockTickerHistory();
		collection = mock.readTickerData(PATH + "valueGain_tickerData_05");
		
		List<Double> expectedResults = new ArrayList<Double>();		
		expectedResults = mock.getListOfDoubles(PATH + "valueGain_ExpectedResults_05");
		
		boolean result = false;
		
		try{
			SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
			List<Double> currentResults = signalEffectivnes.valueGainPerDay(days, buySignal, collection);
			
			result = checkIfListAreEqual(expectedResults, result, currentResults);
			assertTrue(expectedResults.size() == currentResults.size());
			assertTrue(result);
		}catch(Exception ex){
			fail("Exception when not excpected: " + ex.getMessage());
		}
	}
	
	
	/*
	 * Check if correct percentage is returned when there are days with gain and loose
	 */
	@Test
	public void percentageOfDaysWithPositiveResults_01() throws ParseException, IOException{

		double expectedResults = 28.57;
		
		List<Boolean> positiveResults = new ArrayList<Boolean>();		
		positiveResults = mock.getCorrectSignals(PATH + "percentageOfDaysWithPositiveResults_expectedResults_01");

		SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
		
		try{
			double currentResult = signalEffectivnes.percentageOfDaysWithPositiveResults(positiveResults);
			boolean result = Precision.equalsIncludingNaN(expectedResults, currentResult, 0.01);
			assertTrue(result);
		}catch(Exception ex){
			fail("Exception when not excpected: " + ex.getMessage());
		}
	}
	
	/*
	 * Check if correct value will be returned if there are days only with loose
	 */
	@Test
	public void percentageOfDaysWithPositiveResults_02() throws ParseException, IOException{

		double expectedResults = 0.00;
		
		List<Boolean> positiveResults = new ArrayList<Boolean>();		
		positiveResults = mock.getCorrectSignals(PATH + "percentageOfDaysWithPositiveResults_expectedResults_02");

		SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
		
		try{
			double currentResult = signalEffectivnes.percentageOfDaysWithPositiveResults(positiveResults);
			boolean result = Precision.equalsIncludingNaN(expectedResults, currentResult, 0.01);
			assertTrue(result);
		}catch(Exception ex){
			fail("Exception when not excpected: " + ex.getMessage());
		}
	}
	
	/*
	 * Check if correct value will be returned if there are days only with gain
	 */
	@Test
	public void percentageOfDaysWithPositiveResults_03() throws ParseException, IOException{

		double expectedResults = 100.00;
		
		List<Boolean> positiveResults = new ArrayList<Boolean>();		
		positiveResults = mock.getCorrectSignals(PATH + "percentageOfDaysWithPositiveResults_expectedResults_03");

		SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
		
		try{
			double currentResult = signalEffectivnes.percentageOfDaysWithPositiveResults(positiveResults);
			boolean result = Precision.equalsIncludingNaN(expectedResults, currentResult, 0.01);
			assertTrue(result);
		}catch(Exception ex){
			fail("Exception when not excpected: " + ex.getMessage());
		}
	}
	
	/*
	 * Check if method will correctly behave if array with list of daysWithPositiveResults is empty
	 */
	@Test
	public void percentageOfDaysWithPositiveResults_04() throws ParseException, IOException{
		
		List<Boolean> positiveResults = new ArrayList<Boolean>();		
		positiveResults = mock.getCorrectSignals(PATH + "percentageOfDaysWithPositiveResults_expectedResults_04");
		
		SingleBuySignalEffectiveness signalEffectivnes = new SingleBuySignalEffectiveness();
		
		try{		
			signalEffectivnes.percentageOfDaysWithPositiveResults(positiveResults);
			
			fail("Exception was not thrown when expected");
		}catch(StockExchangeIllegalStateException ex){
			String expectedErrorMessage = "There is no days with results (empty list passed as a argument) so cannot calculate percentage";
			assertTrue(expectedErrorMessage.equals(ex.getMessage()));
		}
	}
}
