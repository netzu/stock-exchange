package data.collector;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import org.joda.time.DateTime;
import org.junit.Test;
import org.mockito.Mockito;

import utils.MockForCommonsTest;

public class StockDataExtractorTest {
	MockForCommonsTest mock = new MockForCommonsTest();
	
	@Test
	public void extractFromQueryResultsRTest() throws SQLException, ParseException{
		
		Date dateTime = new Date(221435);
		
		EODTick expectedTicker = new EODTick();
		
		expectedTicker.setStockName("Lena");
		expectedTicker.setDate(new DateTime(dateTime));
		expectedTicker.setOpen(2.01);
		expectedTicker.setHigh(2.61);
		expectedTicker.setLow(1.95);
		expectedTicker.setClose(2.47);
		expectedTicker.setVolumen((double)1943);
		
		ResultSet resultSet = Mockito.mock(ResultSet.class);
		Mockito.when(resultSet.getString("ticker")).thenReturn(expectedTicker.getStockName());
		Mockito.when(resultSet.getDate("date")).thenReturn(dateTime);
		Mockito.when(resultSet.getDouble("open")).thenReturn(expectedTicker.getOpen());
		Mockito.when(resultSet.getDouble("high")).thenReturn(expectedTicker.getHigh());
		Mockito.when(resultSet.getDouble("low")).thenReturn(expectedTicker.getLow());
		Mockito.when(resultSet.getDouble("close")).thenReturn(expectedTicker.getClose());
		Mockito.when(resultSet.getDouble("volumen")).thenReturn(expectedTicker.getVolumen());

		StockDataExtractor extractor = new StockDataExtractor();
		EODTick currentResults = extractor.extractFromQueryResults(resultSet);
		
		assertEquals("Ticker name was incorrect","Lena",currentResults.getStockName());
		assertEquals("Date was incorrect", expectedTicker.getDate(),currentResults.getDate());
		assertEquals("Open value was incorrect",expectedTicker.getOpen(),currentResults.getOpen(), 0.0001);
		assertEquals("High vvalue was incorrect", expectedTicker.getHigh(),currentResults.getHigh(), 0.0001);
		assertEquals("Low value was incorrect",expectedTicker.getLow(), currentResults.getLow(), 0.0001);
		assertEquals("Close value was incorrect",expectedTicker.getClose(), currentResults.getClose(), 0.0001);
		assertEquals("Volumen value was incorrect",expectedTicker.getVolumen() ,currentResults.getVolumen(), 0.0001);
	}
}
