package data;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URL;
import java.text.ParseException;

import data.collector.EODTick;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import utils.MockForCommonsTest;
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
		EODTick ticker = currentResults.getEODTickDataList().get(0);

		assertTrue("Size of the EODTick is diffrent that expected(1), has " + currentResults.getEODTickDataList().size(), currentResults.getEODTickDataList().size() == 1);
		assertTrue("EODTick name than expected", ticker.getStockName().equals("Test"));
		assertTrue("EODTick Date diffrent than expected", ticker.getDate().equals(dateFormater.parseDateTime("20120315")));
		assertTrue("EODTick Open Value diffrent than expected", ticker.getOpen() == 15.51);
		assertTrue("EODTick Highest diffrent than expected", ticker.getHigh() == 7.9);
		assertTrue("EODTick Lowest diffrent than expected", ticker.getLow() == 7.54);
		assertTrue("EODTick Closed diffrent than expected", ticker.getClose() == 7.54);
		assertTrue("EODTick Volumen diffrent than expected", ticker.getVolumen() == 502);
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
		} catch (StockDataReaderException ex) {
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
			assertTrue("StockTickerHistory is not empty as expected", tickerHistory.getEODTickDataList().isEmpty());
		} catch (StockDataReaderException ex) {
			assertTrue("Error message diffrent than expected: " + ex.getMessage(), ex.getMessage().equals(expectedErrorMessage));
		}
	}

	@Test
	public void fileWithMultipleEntries() throws ParseException {
		DataFileReader reader = new DataFileReader();
		URL resource = this.getClass().getClassLoader().getResource("data/fileWithMultipleEntries");
		File inputFile = new File(resource.getPath());

		StockTickerHistory expectedResults = mock.readStockTickerHistory("data/fileWithMultipleEntries");
		StockTickerHistory currentResults = reader.getStockTickerCollection(inputFile);

		assertTrue(currentResults.equals(expectedResults));
	}

	@Test(expected=StockDataReaderException.class)
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

		StockTickerHistory expectedResults = mock.readStockTickerHistory("data/fileWithoutHeader");
		StockTickerHistory currentResults = dataFileReader.getStockTickerCollection(filesWithoutHeader);

		assertTrue(currentResults.equals(expectedResults));
	}
	
	@Test
	public void fileWithEmptyLine() throws ParseException{
		DataFileReader dataFileReader = new DataFileReader();
		URL resource = this.getClass().getClassLoader().getResource("data/fileWithEmptyLine");
		File filesWithoutHeader = new File(resource.getPath());

		StockTickerHistory expectedResults = mock.readStockTickerHistory("data/fileWithEmptyLine");
		StockTickerHistory currentResults = dataFileReader.getStockTickerCollection(filesWithoutHeader);

		assertTrue(currentResults.equals(expectedResults));
	}
}
