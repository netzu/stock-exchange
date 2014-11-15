package data.collector;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import utils.MockForCommonsTest;

public class EODTickHistoryTest {
	
	MockForCommonsTest mock = new MockForCommonsTest();
	DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");
	
	@Test
	public void setStockTickerDataListTest() throws ParseException{
		StockTickerHistory tickerCollection = mock.readStockTickerHistory("data/collector/tickerHistory/setStockTickerDataListTest");		
		List<EODTick> EODTickDataList = new ArrayList<EODTick>();
		tickerCollection.setEODTickDataList(EODTickDataList);
		
		assertTrue(EODTickDataList.equals(tickerCollection.getEODTickDataList()));
	}
	
	@Test
	public void subListOfCollectionException() throws ParseException{
		StockTickerHistory tickerCollection = mock.readStockTickerHistory("data/collector/tickerHistory/setStockTickerDataListTest");
		
		try{
			List<EODTick> EODTickDataList = tickerCollection.subListOfCollection(4, 2);
			fail("Exception not found when expected");
		}catch(StockExchangeIllegalStateException ex){
			String expectedErrorMessage = "Cannot create sublist when FROM is greather or equal TO";
			assertTrue(ex.getMessage().equals(expectedErrorMessage));
		}		
	}
	
	@Test
	public void subListOfCollectionTest() throws ParseException{
		StockTickerHistory tickerCollection = mock.readStockTickerHistory("data/collector/tickerHistory/subListOfCollectionTest_Input");
		
		try{
			List<EODTick> currentResults = tickerCollection.subListOfCollection(2, 6);
			List<EODTick> expectedResults = mock.readStockTickerHistory("data/collector/tickerHistory/subListOfCollectionTest_Expected").getEODTickDataList();
			assertTrue(currentResults.equals(expectedResults));
		}catch(Exception ex){
			fail("Exception when not expected");
		}		
	}
	
	@Test
	public void findStockByDateEmptyTicker(){
		
		StockTickerHistory tickerCollection = new StockTickerHistory();
		DateTime date = dateFormater.parseDateTime(("20120319"));
		
		try{
			tickerCollection.findStockByDate(date);
			fail("Exception not found when expected");
		}catch(StockExchangeIllegalStateException ex){
			String expectedErrorMessage = "Cannot find stock by date if ticker's collection is empty";
			assertTrue(ex.getMessage().equals(expectedErrorMessage));
		}
	}
	
	@Test
	public void findStockByDateWhenNullDate() throws ParseException{
		
		StockTickerHistory tickerCollection = new StockTickerHistory();
		tickerCollection.setEODTickDataList(mock.readStockTickerHistory("data/collector/tickerHistory/findStockByDateNotFound").getEODTickDataList());
		
		try{
			tickerCollection.findStockByDate(null);
			fail("Exception not found when expected");
		}catch(StockExchangeIllegalStateException ex){
			String expectedErrorMessage = "Cannot find ticker by date if date is null";
			assertTrue(ex.getMessage().equals(expectedErrorMessage));
		}
	}
	
	@Test
	public void findStockByDateNotFound() throws ParseException{
		
		StockTickerHistory tickerCollection = new StockTickerHistory();
		tickerCollection.setEODTickDataList(mock.readStockTickerHistory("data/collector/tickerHistory/findStockByDateNotFound").getEODTickDataList());
		DateTime date = dateFormater.parseDateTime(("20100417"));
		
		try{
			tickerCollection.findStockByDate(date);
			fail("Exception not found when expected");
		}catch(StockExchangeIllegalStateException ex){
			String expectedErrorMessage = "Could not find a stock in given day";
			assertTrue(ex.getMessage().equals(expectedErrorMessage));
		}
	}
	
	@Test
	public void findStockByDateFound() throws ParseException{
		StockTickerHistory tickerCollection = new StockTickerHistory();
		tickerCollection.setEODTickDataList(mock.readStockTickerHistory("data/collector/tickerHistory/findStockByDateNotFound").getEODTickDataList());
		DateTime date = dateFormater.parseDateTime(("20100412"));
		
		EODTick currentResult = tickerCollection.findStockByDate(date);
		
		assertTrue("Diffrent StockName than expected", currentResult.getStockName().equals("Test"));
		assertTrue("Diffrent Date than expected", currentResult.getDate().equals(date));
		assertTrue("Diffrent Open than expected", currentResult.getOpen() == 1.23);
		assertTrue("Diffrent High than expected", currentResult.getHigh() == 2.92);
		assertTrue("Diffrent Low than expected", currentResult.getLow() == 0.23);
		assertTrue("Diffrent Close than expected", currentResult.getClose() == 3.58);
		assertTrue("Diffrent Volumen than expected", currentResult.getVolumen() == 1232);
	}
	
	@Test
	public void findStockIndexByDateTickerEmpty(){
		StockTickerHistory tickerCollection = new StockTickerHistory();
		DateTime date = dateFormater.parseDateTime(("20120319"));
		
		try{
			tickerCollection.findStockIndexByDate(date);
			fail("Exception not found when expected");
		}catch(StockExchangeIllegalStateException ex){
			String expectedErrorMessage = "Cannot find index for a ticker with given date for an empty ticker collection";
			assertTrue(ex.getMessage().equals(expectedErrorMessage));
		}
	}
	
	@Test
	public void findStockIndexByDateIsNull() throws ParseException{
		StockTickerHistory tickerCollection = new StockTickerHistory();
		tickerCollection.setEODTickDataList(mock.readStockTickerHistory("data/collector/tickerHistory/findStockByDateNotFound").getEODTickDataList());
		
		try{
			tickerCollection.findStockIndexByDate(null);
			fail("Exception not found when expected");
		}catch(StockExchangeIllegalStateException ex){
			String expectedErrorMessage = "Cannot find index for a ticker when date is null";
			assertTrue(ex.getMessage().equals(expectedErrorMessage));
		}
	}
	
//	@Test
//	public void findStockIndexByDateFound() throws ParseException{
//		StockTickerHistory tickerCollection = new StockTickerHistory();
//		tickerCollection.setEODTickDataList(mock.readStockTickerHistory("data/collector/tickerHistory/findStockByDateNotFound").getEODTickDataList());
//		DateTime date = dateFormater.parseDateTime(("20120319"));
//		
//		int expectedResult = 6;
//		
//		try{
//			int currentResult = tickerCollection.findStockIndexByDate(date);
//			assertTrue(currentResult == expectedResult);
//		}catch(Exception ex){
//			fail("Exception when not expected");
//		}
//	}
	
	@Test
	public void findStockIndexByDateNotFound() throws ParseException{
		StockTickerHistory tickerCollection = new StockTickerHistory();
		tickerCollection.setEODTickDataList(mock.readStockTickerHistory("data/collector/tickerHistory/findStockByDateNotFound").getEODTickDataList());
		DateTime date = dateFormater.parseDateTime(("20120317"));
		
		try{
			tickerCollection.findStockIndexByDate(date);
			fail("Exception not found when expected");
		}catch(StockExchangeIllegalStateException ex){
			String expectedErrorMessage = "Could not find it";
			assertTrue(ex.getMessage().equals(expectedErrorMessage));
		}
	}
}
