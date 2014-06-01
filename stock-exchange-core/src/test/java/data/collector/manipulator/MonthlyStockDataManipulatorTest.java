package data.collector.manipulator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;

import org.junit.Test;

import data.collector.StockTickerHistory;
import utils.MocksForTests;

public class MonthlyStockDataManipulatorTest {
	
	final static String PATH = new String("data/collectorManipulator/monthly/");
	MocksForTests mock = new MocksForTests();
	
	@Test
	public void onlyOneDayInMonth() throws ParseException{
		
		StockTickerHistory inputTickerCollection = mock.readTickerData(PATH + "onlyOneDayInMonth_inputTickerCollection");
		StockTickerHistory expectedResults = mock.readTickerData(PATH + "onlyOneDayInMonth_expectedTickerCollection");
		
		try{
			MonthlyStockDataManipulator manipulator = new MonthlyStockDataManipulator();
			StockTickerHistory currentResults = manipulator.manipulate(inputTickerCollection);
			
			assertTrue(currentResults.equals(expectedResults));
		}catch(Exception ex){
			 fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	@Test
	public void severlDaysInOneMonth() throws ParseException{
		
		StockTickerHistory inputTickerCollection = mock.readTickerData(PATH + "severlDaysInOneMonth_inputTickerCollection");
		StockTickerHistory expectedResults = mock.readTickerData(PATH + "severlDaysInOneMonth_expectedTickerCollection");
		
		try{
			MonthlyStockDataManipulator manipulator = new MonthlyStockDataManipulator();
			StockTickerHistory currentResults = manipulator.manipulate(inputTickerCollection);
			
			assertTrue(currentResults.equals(expectedResults));
		}catch(Exception ex){
			 fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	@Test
	public void severlDaysInFewMonth() throws ParseException{
		
		StockTickerHistory inputTickerCollection = mock.readTickerData(PATH + "severlDaysInFewMonth_inputTickerCollection");
		StockTickerHistory expectedResults = mock.readTickerData(PATH + "severlDaysInFewMonth_expectedTickerCollection");
		
		try{
			MonthlyStockDataManipulator manipulator = new MonthlyStockDataManipulator();
			StockTickerHistory currentResults = manipulator.manipulate(inputTickerCollection);
			
			assertTrue(currentResults.equals(expectedResults));
		}catch(Exception ex){
			 fail("Exception when not expected: " + ex.getMessage());
		}
	}
}
