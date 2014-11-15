package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import data.DataFileReader;
import data.collector.EODTick;
import data.collector.StockTickerHistory;

public class MockForCommonsTest {
	
	private static final int TICKER_NAME = 0;
	private static final int DATE = 1;
	private static final int OPEN_PRICE = 2;
	private static final int HIGH_PRICE = 3;
	private static final int LOW_PRICE = 4;
	private static final int CLOSE_PRICE = 5;
	private static final int VOLUMEN = 6;	
		
	private DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");
	
	public StockTickerHistory readStockTickerHistory(final String path) throws ParseException {

		URL resource = this.getClass().getClassLoader().getResource(path);
		final String filePath = resource.getPath();
		DataFileReader fileReader = new DataFileReader();

		return fileReader.getStockTickerCollection(new File(filePath));
	}
	
	public EODTick readStockTicker(final String path) throws IOException{
		
		final InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
		final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		
		EODTick results = new EODTick();

		String line = null;

		while ((line = reader.readLine()) != null) {
			String[] splitData = line.split(",");
			
			results.setStockName(splitData[TICKER_NAME]);
			results.setDate(dateFormater.parseDateTime(splitData[DATE]));
			results.setOpen(Double.parseDouble(splitData[OPEN_PRICE]));
			results.setHigh(Double.parseDouble(splitData[HIGH_PRICE]));
			results.setLow(Double.parseDouble(splitData[LOW_PRICE]));
			results.setClose(Double.parseDouble(splitData[CLOSE_PRICE]));
			results.setVolumen(Double.parseDouble(splitData[VOLUMEN]));
		}
		return results;	
	}
}
