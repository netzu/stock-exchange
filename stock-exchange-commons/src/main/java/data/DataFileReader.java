package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import data.collector.StockDataExtractor;
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
				throw new StockDataReaderExcetion("File with socks data is empty");
			}
			
			String exclude = "<TICKER>,<DTYYYYMMDD>,<OPEN>,<HIGH>,<LOW>,<CLOSE>,<VOL>";
			
			if (fileLine.startsWith(exclude)){
				fileLine=reader.readLine();
			}
			
			while(fileLine != null){
				String[] data = fileLine.split(",");
				
				StockDataExtractor extractor = new StockDataExtractor();
				
				StockTicker stockDataFromOneDay = StockTicker.copy(extractor.extractFromString(data));
				stockTickerCollection.add(stockDataFromOneDay);
				
				fileLine = reader.readLine();
			}
			
		} catch (final IOException ioe) {
			throw ioe;
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
			throw new StockDataReaderExcetion(ex.getMessage());
		}		
	}
}