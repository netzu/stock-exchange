package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import data.collector.StockTicker;
import data.collector.StockTickerHistory;

public class DataFileReader {

	private StockTickerHistory readStockData(final File tickerFile) throws ParseException, IOException {
		StockTickerHistory stockTickerCollection = new StockTickerHistory();
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(tickerFile));
			
			String fileLine = reader.readLine();
			
			if(fileLine == null){
				throw new IllegalStateException("Stock ticker is empty");
			}
			
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
		}finally{
			if (null != reader) {
				reader.close();
			}
		}
		return stockTickerCollection;
	}

	public StockTickerHistory getStockTickerCollection(final File tickerFile) throws ParseException {
		
		try{
			StockTickerHistory result = readStockData(tickerFile);
			return result;
		}catch(IOException ex){
			throw new IllegalStateException();
		}		
	}
}