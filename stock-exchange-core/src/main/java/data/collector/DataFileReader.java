package data.collector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import configuration.StockExchangeProperties;

public class DataFileReader {

	
	private StockTickerCollection readStockData(final File tickerFile) throws ParseException {
		StockTickerCollection stockTickerCollection = new StockTickerCollection();
		
		try {
			FileReader stockDataReader = new FileReader(tickerFile);
			BufferedReader reader = new BufferedReader(stockDataReader);
			
			String fileLine = reader.readLine();
			String exclude = "<TICKER>,<DTYYYYMMDD>,<OPEN>,<HIGH>,<LOW>,<CLOSE>,<VOL>";
			
			if (fileLine.startsWith(exclude)){
				fileLine=reader.readLine();
			}
			
			while(fileLine != null){
				String[] data = fileLine.split(",");
				
				StockTicker stockDataFromOneDay = new StockTicker();
				stockDataFromOneDay.extractStockdata(data);
				stockTickerCollection.add(stockDataFromOneDay);
				
				fileLine = reader.readLine();
			}
			
		} catch (final IOException ioe) {
			ioe.printStackTrace();
		}
		return stockTickerCollection;
	}

	public StockTickerCollection getStockTickerCollection(final File tickerFile) throws ParseException {
		return readStockData(tickerFile);
	}
}
