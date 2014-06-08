package data.collector;

import static org.junit.Assert.*;

import java.io.IOException;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import utils.MockForCommonsTest;

public class StockTickerTest {

	MockForCommonsTest mock = new MockForCommonsTest();
	DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");
	
	@Test
	public void equalsDifferentHigh() throws IOException{
		
		StockTicker fistTicker = mock.readStockTicker("data/collector/equals/differentHigh_firstTicker");
		StockTicker secondTicker = mock.readStockTicker("data/collector/equals/differentHigh_SecondTicker");
		
		assertTrue("Two objects are equal, expected High Value being diffrnet", !fistTicker.equals(secondTicker));
		assertTrue("High value form first ticker diffrent than expected", fistTicker.getHigh() == 7.63);
		assertTrue("High value form second ticker diffrent than expected", secondTicker.getHigh() == 7.64);
	}
	
	@Test
	public void equalsDifferentLow() throws IOException{
		
		StockTicker fistTicker = mock.readStockTicker("data/collector/equals/differentLow_firstTicker");
		StockTicker secondTicker = mock.readStockTicker("data/collector/equals/differentLow_SecondTicker");
		
		assertTrue("Two objects are equal, expected High Value being diffrnet", !fistTicker.equals(secondTicker));
		assertTrue("Low value form first ticker diffrent than expected", fistTicker.getLow() == 6.99);
		assertTrue("Low value form second ticker diffrent than expected", secondTicker.getLow() == 7.00);
	}
	
	@Test
	public void equalsDifferentOpen() throws IOException{
		
		StockTicker fistTicker = mock.readStockTicker("data/collector/equals/differentOpen_firstTicker");
		StockTicker secondTicker = mock.readStockTicker("data/collector/equals/differentOpen_SecondTicker");
		
		assertTrue("Two objects are equal, expected open value being diffrnet", !fistTicker.equals(secondTicker));
		assertTrue("Open value form first ticker diffrent than expected", fistTicker.getOpen() == 13.45);
		assertTrue("Open value form second ticker diffrent than expected", secondTicker.getOpen() == 13.44);
	}
	
	@Test
	public void equalsDifferentClose() throws IOException{
		
		StockTicker fistTicker = mock.readStockTicker("data/collector/equals/differentClose_firstTicker");
		StockTicker secondTicker = mock.readStockTicker("data/collector/equals/differentClose_SecondTicker");
		
		assertTrue("Two objects are equal, expected Close value being diffrnet", !fistTicker.equals(secondTicker));
		assertTrue("Close value form first ticker diffrent than expected", fistTicker.getClose() == 0.62);
		assertTrue("Close value form second ticker diffrent than expected", secondTicker.getClose() == 0.63);
	}
	
	@Test
	public void equalsDifferentVolumen() throws IOException{
		
		StockTicker fistTicker = mock.readStockTicker("data/collector/equals/differentVolumen_firstTicker");
		StockTicker secondTicker = mock.readStockTicker("data/collector/equals/differentVolumen_SecondTicker");
		
		assertTrue("Two objects are equal, expected volumen value being diffrnet", !fistTicker.equals(secondTicker));
		assertTrue("Volumen value form first ticker diffrent than expected", fistTicker.getVolumen() == 8471);
		assertTrue("Volumen value form second ticker diffrent than expected", secondTicker.getVolumen() == 8472);
	}
	
	@Test
	public void equalsDifferentTickerName() throws IOException{
		
		StockTicker fistTicker = mock.readStockTicker("data/collector/equals/differentTickerName_firstTicker");
		StockTicker secondTicker = mock.readStockTicker("data/collector/equals/differentTickerName_SecondTicker");
		
		assertTrue("Two objects are equal, expected Ticker Names being diffrnet", !fistTicker.equals(secondTicker));
		assertTrue("Ticker Name value form first ticker diffrent than expected", fistTicker.getStockName().equals("Ticker_1"));
		assertTrue("Ticker Name value form second ticker diffrent than expected", secondTicker.getStockName().endsWith("Ticker_2"));
	}
	
	@Test
	public void equalsDifferentDates() throws IOException{
		
		StockTicker fistTicker = mock.readStockTicker("data/collector/equals/equalsDifferentDates_firstTicker");
		StockTicker secondTicker = mock.readStockTicker("data/collector/equals/equalsDifferentDates_SecondTicker");
		
		assertTrue("Two objects are equal, expected dates being diffrnet", !fistTicker.equals(secondTicker));
		assertTrue("Date form first ticker diffrent than expected", fistTicker.getDate().equals(dateFormater.parseDateTime(("20120319"))));
		assertTrue("Date value form second ticker diffrent than expected", secondTicker.getDate().equals(dateFormater.parseDateTime(("20120320"))));
	}
	
	@Test
	public void equalsForNullObject() throws IOException{
		
		StockTicker fistTicker = mock.readStockTicker("data/collector/equals/equalsForNullObject_firstTicker");
		StockTicker secondTicker = null;
		
		assertTrue(!fistTicker.equals(secondTicker));
	}
	
	@Test
	public void twoEqualObjects() throws IOException{
		
		StockTicker fistTicker = mock.readStockTicker("data/collector/equals/twoEqualObjects");
		StockTicker secondTicker = mock.readStockTicker("data/collector/equals/twoEqualObjects");;
		
		assertTrue(fistTicker.equals(secondTicker));
		assertTrue(secondTicker.equals(fistTicker));
	}
	
	@Test
	public void equalsDiffrentClasses() throws IOException{
		
		StockTicker fistTicker = mock.readStockTicker("data/collector/equals/equalsNullDate");		
		assertTrue(!fistTicker.equals(2.31));
	}
	
	@Test
	public void equalsNullDate() throws IOException{
		
		StockTicker fistTicker = mock.readStockTicker("data/collector/equals/equalsNullDate");		
		StockTicker secondTicker = mock.readStockTicker("data/collector/equals/equalsNullDate");	
		
		secondTicker.setDate(null);
		
		assertTrue(!fistTicker.equals(secondTicker));
		assertTrue(!secondTicker.equals(fistTicker));
	}
	
	@Test
	public void equalsNullStockName() throws IOException{
		
		StockTicker fistTicker = mock.readStockTicker("data/collector/equals/equalsNullDate");		
		StockTicker secondTicker = mock.readStockTicker("data/collector/equals/equalsNullDate");	
		
		secondTicker.setStockName(null);
		
		assertTrue(!fistTicker.equals(secondTicker));
		assertTrue(!secondTicker.equals(fistTicker));
	}
}