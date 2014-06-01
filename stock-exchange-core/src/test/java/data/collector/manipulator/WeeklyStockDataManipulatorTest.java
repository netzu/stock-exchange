package data.collector.manipulator;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import data.collector.StockTickerHistory;
import utils.MocksForTests;

public class WeeklyStockDataManipulatorTest {
	
	final static String PATH = new String("data/collectorManipulator/weekly/");
	MocksForTests mock = new MocksForTests();
	
	@Test
	public void onlyOneDayInWeek() throws ParseException{
		
		StockTickerHistory inputTickerCollection = mock.readTickerData(PATH + "onlyOneDayInWeek_inputTickerCollection");
		StockTickerHistory expectedResults = mock.readTickerData(PATH + "onlyOneDayInWeek_expectedTickerCollection");
		
		try{
			WeeklyStockDataManipulator manipulator = new WeeklyStockDataManipulator();
			StockTickerHistory currentResults = manipulator.manipulate(inputTickerCollection);
			
			assertTrue(currentResults.equals(expectedResults));
		}catch(Exception ex){
			 fail("Exception when not expected: " + ex.getMessage());
		}
	}

	@Test
	public void severlDaysInOneWeek() throws ParseException{
		
		StockTickerHistory inputTickerCollection = mock.readTickerData(PATH + "severlDaysInWeek_inputTickerCollection");
		StockTickerHistory expectedResults = mock.readTickerData(PATH + "severlDaysInWeek_expectedTickerCollection");
		
		try{
			WeeklyStockDataManipulator manipulator = new WeeklyStockDataManipulator();
			StockTickerHistory currentResults = manipulator.manipulate(inputTickerCollection);
			
			assertTrue(currentResults.equals(expectedResults));
		}catch(Exception ex){
			 fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	@Test
	public void severlDaysInFewWeeks() throws ParseException{
		
		StockTickerHistory inputTickerCollection = mock.readTickerData(PATH + "severlDaysInFewWeeks_inputTickerCollection");
		StockTickerHistory expectedResults = mock.readTickerData(PATH + "severlDaysInFewWeeks_expectedTickerCollection");
		
		try{
			WeeklyStockDataManipulator manipulator = new WeeklyStockDataManipulator();
			StockTickerHistory currentResults = manipulator.manipulate(inputTickerCollection);
			
			assertTrue(currentResults.equals(expectedResults));
		}catch(Exception ex){
			 fail("Exception when not expected: " + ex.getMessage());
		}
	}
}
