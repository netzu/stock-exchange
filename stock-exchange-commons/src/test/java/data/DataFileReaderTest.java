package data;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import utils.MockForCommonsTest;
import data.collector.StockTicker;
import data.collector.StockTickerHistory;

public class DataFileReaderTest {

	MockForCommonsTest mock = new MockForCommonsTest();
	DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");

	@Test
	public void checkingIfGetStockTickerCollection() throws Exception {
		DataFileReader dataFileReader = new DataFileReader();
		URL resource = this.getClass().getClassLoader().getResource("data/DataFileReaderTest_Inputfile.mst");
		File inputFile = new File(resource.getPath());

		StockTickerHistory currentResults = dataFileReader.getStockTickerCollection(inputFile);
		StockTicker ticker = currentResults.getStockTickerDataList().get(0);

		assertTrue("Size of the StockTicker is diffrent that expected(1), has " + currentResults.getStockTickerDataList().size(), currentResults.getStockTickerDataList().size() == 1);
		assertTrue("StockTicker name than expected", ticker.getStockName().equals("Test"));
		assertTrue("StockTicker Date diffrent than expected", ticker.getDate().equals(dateFormater.parseDateTime("20120315")));
		assertTrue("StockTicker Open Value diffrent than expected", ticker.getOpen() == 15.51);
		assertTrue("StockTicker Highest diffrent than expected", ticker.getHigh() == 7.9);
		assertTrue("StockTicker Lowest diffrent than expected", ticker.getLow() == 7.54);
		assertTrue("StockTicker Closed diffrent than expected", ticker.getClose() == 7.54);
		assertTrue("StockTicker Volumen diffrent than expected", ticker.getVolumen() == 502);
	}

	@Test
	public void emptyFileWithoutHeader() throws ParseException {
		DataFileReader dataFileReader = new DataFileReader();
		URL resource = this.getClass().getClassLoader().getResource("data/emptyFile_withoutHeader");
		File filesWithHeader = new File(resource.getPath());

		String expectedErrorMessage = "File with socks data is empty";

		try {
			dataFileReader.getStockTickerCollection(filesWithHeader);
			fail("Exception not found when expected");
		} catch (StockDataReaderExcetion ex) {
			assertTrue("Error message diffrent than expected: " + ex.getMessage(), ex.getMessage().equals(expectedErrorMessage));
		}
	}

	@Test
	public void emptyFileWithHeader() throws ParseException {
		DataFileReader dataFileReader = new DataFileReader();
		URL resource = this.getClass().getClassLoader().getResource("data/emptyFile_withHeader");
		File filesWithHeader = new File(resource.getPath());

		String expectedErrorMessage = "File with socks data is empty";

		try {
			StockTickerHistory tickerHistory = dataFileReader.getStockTickerCollection(filesWithHeader);
			assertTrue("StockTickerHistory is not empty as expected", tickerHistory.getStockTickerDataList().isEmpty());
		} catch (StockDataReaderExcetion ex) {
			assertTrue("Error message diffrent than expected: " + ex.getMessage(), ex.getMessage().equals(expectedErrorMessage));
		}
	}

	@Test
	public void fileWithMultipleEntries() throws ParseException {
		DataFileReader reader = new DataFileReader();
		URL resource = this.getClass().getClassLoader().getResource("data/fileWithMultipleEntries");
		File inputFile = new File(resource.getPath());

		StockTickerHistory expectedResults = mock.readTickerData("data/fileWithMultipleEntries");
		StockTickerHistory currentResults = reader.getStockTickerCollection(inputFile);

		assertTrue(currentResults.equals(expectedResults));
	}

	@Test(expected=StockDataReaderExcetion.class)
	public void ioException() throws ParseException {
		DataFileReader reader = new DataFileReader();
		File inputFile = new File("/data/fakeFile");

		reader.getStockTickerCollection(inputFile);
	}
	
	@Test
	public void fileWithoutHeader() throws ParseException{
		DataFileReader dataFileReader = new DataFileReader();
		URL resource = this.getClass().getClassLoader().getResource("data/fileWithoutHeader");
		File filesWithoutHeader = new File(resource.getPath());

		StockTickerHistory expectedResults = mock.readTickerData("data/fileWithoutHeader");
		StockTickerHistory currentResults = dataFileReader.getStockTickerCollection(filesWithoutHeader);

		assertTrue(currentResults.equals(expectedResults));
	}
	
	@Test
	public void fileWithEmptyLine() throws ParseException{
		DataFileReader dataFileReader = new DataFileReader();
		URL resource = this.getClass().getClassLoader().getResource("data/fileWithEmptyLine");
		File filesWithoutHeader = new File(resource.getPath());

		StockTickerHistory expectedResults = mock.readTickerData("data/fileWithEmptyLine");
		StockTickerHistory currentResults = dataFileReader.getStockTickerCollection(filesWithoutHeader);

		assertTrue(currentResults.equals(expectedResults));
	}
}
