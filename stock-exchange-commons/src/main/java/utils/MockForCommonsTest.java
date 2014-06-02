package utils;

import java.io.File;
import java.net.URL;
import java.text.ParseException;

import data.DataFileReader;
import data.collector.StockTickerHistory;

public class MockForCommonsTest {
	public StockTickerHistory readTickerData(final String path) throws ParseException {

		URL resource = this.getClass().getClassLoader().getResource(path);

		final String filePath = resource.getPath();

		DataFileReader fileReader = new DataFileReader();

		return fileReader.getStockTickerCollection(new File(filePath));
	}
}
