package indicators.movingaverage.complex;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import indicators.movingaverage.simple.SimpleMovingAverageData;
import indicators.movingaverage.simple.SimpleMovingAverageSignalsGenerator;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import data.collector.StockTickerHistory;

public class ComplexMovingAverageSellSignlasTest {
	
	DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");
	StockTickerHistory mockStockTickerHistory;
	ComplexMovingAverageSettings mockAverageData1;
	ComplexMovingAverageSettings mockAverageData2;
	ComplexMovingAverageSettings mockAverageData3;
	
	List<ComplexMovingAverageSettings> averageDataList;

	List<SimpleMovingAverageData> simpleMovingAverageDataList1 = Mockito.mock(ArrayList.class);
	List<SimpleMovingAverageData> simpleMovingAverageDataList2 = Mockito.mock(ArrayList.class);
	List<SimpleMovingAverageData> simpleMovingAverageDataList3 = Mockito.mock(ArrayList.class);
	
	private SimpleMovingAverageSignalsGenerator mockSimpleMovingAverageSignalsGenerator;
	
	@Before
	public void setup() {
		mockSimpleMovingAverageSignalsGenerator = Mockito.mock(SimpleMovingAverageSignalsGenerator.class);
		mockStockTickerHistory = Mockito.mock(StockTickerHistory.class);
		mockAverageData1 = Mockito.mock(ComplexMovingAverageSettings.class);
		mockAverageData2 = Mockito.mock(ComplexMovingAverageSettings.class);
		mockAverageData3 = Mockito.mock(ComplexMovingAverageSettings.class);
		
		Mockito.when(mockAverageData1.getAverageData()).thenReturn(simpleMovingAverageDataList1);
		Mockito.when(mockAverageData2.getAverageData()).thenReturn(simpleMovingAverageDataList2);
		Mockito.when(mockAverageData3.getAverageData()).thenReturn(simpleMovingAverageDataList3);
		
		averageDataList = Arrays.asList(mockAverageData1, mockAverageData2, mockAverageData3);
	}
	
	
	
	@Test
	public void Test_1() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(buildFromString("20120303"));
		setSellSignalsForSecondPeriod(buildFromString("20120303"));
		setSellSignalsForThirdPeriod(buildFromString("20120303"));		
		
		ComplexMovingAverageSignalsGenerator signals = new ComplexMovingAverageSignalsGenerator(settings);
		
		List<DateTime> sellSignals = signals.generatSellSignals(averageDataList, mockSimpleMovingAverageSignalsGenerator, mockStockTickerHistory);
		
		assertFalse(sellSignals.isEmpty()); 
		Assert.assertTrue(sellSignals.contains(buildFromString("20120303"))); 
	    assertEquals(1, sellSignals.size());		
	}
	
	@Test
	public void Test_2() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(buildFromString("20120303"));
		setSellSignalsForSecondPeriod(buildFromString("20120303"));
		setSellSignalsForThirdPeriod(new DateTime[0]);		
		
		ComplexMovingAverageSignalsGenerator signals = new ComplexMovingAverageSignalsGenerator(settings);
		
		List<DateTime> sellSignals = signals.generatSellSignals(averageDataList, mockSimpleMovingAverageSignalsGenerator, mockStockTickerHistory);
		
		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}
	
	@Test
	public void Test_3() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(buildFromString("20110303"));
		setSellSignalsForSecondPeriod(new DateTime[0]);
		setSellSignalsForThirdPeriod(buildFromString("20110303"));		
		
		ComplexMovingAverageSignalsGenerator signals = new ComplexMovingAverageSignalsGenerator(settings);
		
		List<DateTime> sellSignals = signals.generatSellSignals(averageDataList, mockSimpleMovingAverageSignalsGenerator, mockStockTickerHistory);
		
		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}
	
	@Test
	public void Test_4() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(new DateTime[0]);
		setSellSignalsForSecondPeriod(buildFromString("20151023"));
		setSellSignalsForThirdPeriod(buildFromString("20151023"));		
		
		ComplexMovingAverageSignalsGenerator signals = new ComplexMovingAverageSignalsGenerator(settings);
		
		List<DateTime> sellSignals = signals.generatSellSignals(averageDataList, mockSimpleMovingAverageSignalsGenerator, mockStockTickerHistory);

		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}
	
	@Test
	public void Test_5() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(buildFromString("20091007"));
		setSellSignalsForSecondPeriod(new DateTime[0]);
		setSellSignalsForThirdPeriod(new DateTime[0]);		
		
		ComplexMovingAverageSignalsGenerator signals = new ComplexMovingAverageSignalsGenerator(settings);
		
		List<DateTime> sellSignals = signals.generatSellSignals(averageDataList, mockSimpleMovingAverageSignalsGenerator, mockStockTickerHistory);

		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}
	
	@Test
	public void Test_6() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(new DateTime[0]);
		setSellSignalsForSecondPeriod(new DateTime[0]);
		setSellSignalsForThirdPeriod(buildFromString("19990418"));		
		
		ComplexMovingAverageSignalsGenerator signals = new ComplexMovingAverageSignalsGenerator(settings);
		
		List<DateTime> sellSignals = signals.generatSellSignals(averageDataList, mockSimpleMovingAverageSignalsGenerator, mockStockTickerHistory);

		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}
	
	@Test
	public void Test_7() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(new DateTime[0]);
		setSellSignalsForSecondPeriod(buildFromString("20080930"));
		setSellSignalsForThirdPeriod(new DateTime[0]);		
		
		ComplexMovingAverageSignalsGenerator signals = new ComplexMovingAverageSignalsGenerator(settings);
		
		List<DateTime> sellSignals = signals.generatSellSignals(averageDataList, mockSimpleMovingAverageSignalsGenerator, mockStockTickerHistory);

		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}
	
	@Test
	public void Test_8() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(new DateTime[0]);
		setSellSignalsForSecondPeriod(new DateTime[0]);
		setSellSignalsForThirdPeriod(new DateTime[0]);		
		
		ComplexMovingAverageSignalsGenerator signals = new ComplexMovingAverageSignalsGenerator(settings);
		
		List<DateTime> sellSignals = signals.generatSellSignals(averageDataList, mockSimpleMovingAverageSignalsGenerator, mockStockTickerHistory);

		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}

	@Test
	public void Test_9() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(buildFromString("20080930"));
		setSellSignalsForSecondPeriod(buildFromString("20080930"));
		setSellSignalsForThirdPeriod(buildFromString("20110709"));		
		
		ComplexMovingAverageSignalsGenerator signals = new ComplexMovingAverageSignalsGenerator(settings);
		
		List<DateTime> sellSignals = signals.generatSellSignals(averageDataList, mockSimpleMovingAverageSignalsGenerator, mockStockTickerHistory);

		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}
	
	@Test
	public void Test_10() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(buildFromString("20140224"));
		setSellSignalsForSecondPeriod(buildFromString("20130913"));
		setSellSignalsForThirdPeriod(buildFromString("20140224"));		
		
		ComplexMovingAverageSignalsGenerator signals = new ComplexMovingAverageSignalsGenerator(settings);
		
		List<DateTime> sellSignals = signals.generatSellSignals(averageDataList, mockSimpleMovingAverageSignalsGenerator, mockStockTickerHistory);

		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}
	
	@Test
	public void Test_11() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(buildFromString("20140224"));
		setSellSignalsForSecondPeriod(buildFromString("20130913"));
		setSellSignalsForThirdPeriod(buildFromString("20130913"));		
		
		ComplexMovingAverageSignalsGenerator signals = new ComplexMovingAverageSignalsGenerator(settings);
		
		List<DateTime> sellSignals = signals.generatSellSignals(averageDataList, mockSimpleMovingAverageSignalsGenerator, mockStockTickerHistory);

		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}
	
	@Test
	public void Test_12() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(buildFromString("20121101"));
		setSellSignalsForSecondPeriod(buildFromString("20030913"));
		setSellSignalsForThirdPeriod(buildFromString("20111227"));		
		
		ComplexMovingAverageSignalsGenerator signals = new ComplexMovingAverageSignalsGenerator(settings);
		
		List<DateTime> sellSignals = signals.generatSellSignals(averageDataList, mockSimpleMovingAverageSignalsGenerator, mockStockTickerHistory);

		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}
	
	@Test
	public void Test_13() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(buildFromString("20121101"));
		setSellSignalsForSecondPeriod(buildFromString("20030913"),buildFromString("20140103"),buildFromString("20111019"),buildFromString("20121101"));
		setSellSignalsForThirdPeriod(buildFromString("20111227"), buildFromString("20121101"), buildFromString("20140103"));		
		
		ComplexMovingAverageSignalsGenerator signals = new ComplexMovingAverageSignalsGenerator(settings);
		
		List<DateTime> sellSignals = signals.generatSellSignals(averageDataList, mockSimpleMovingAverageSignalsGenerator, mockStockTickerHistory);

		assertFalse(sellSignals.isEmpty()); 
		Assert.assertTrue(sellSignals.contains(buildFromString("20121101"))); 
	    assertEquals(1, sellSignals.size());
	}
	
	@Test
	public void Test_14() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(buildFromString("20121101"), buildFromString("20140103"), buildFromString("19991221"));
		setSellSignalsForSecondPeriod(buildFromString("20030913"),buildFromString("20140103"),buildFromString("20111019"),buildFromString("20121101"));
		setSellSignalsForThirdPeriod(buildFromString("20111227"), buildFromString("20121101"), buildFromString("20140103"));		
		
		ComplexMovingAverageSignalsGenerator signals = new ComplexMovingAverageSignalsGenerator(settings);
		
		List<DateTime> sellSignals = signals.generatSellSignals(averageDataList, mockSimpleMovingAverageSignalsGenerator, mockStockTickerHistory);

		assertFalse(sellSignals.isEmpty()); 
		Assert.assertTrue(sellSignals.contains(buildFromString("20121101"))); 
		Assert.assertTrue(sellSignals.contains(buildFromString("20140103"))); 
	    assertEquals(2, sellSignals.size());
	}
	
	
	
	private void setSellSignalsForFirstPeriod(final DateTime... dateTimes) {
		ArrayList<DateTime> arrayList = new ArrayList<DateTime>(Arrays.asList(dateTimes));
		Mockito.when(mockSimpleMovingAverageSignalsGenerator.getSellSignals(simpleMovingAverageDataList1, mockStockTickerHistory)).thenReturn(arrayList);
	}
	
	
	private void setSellSignalsForSecondPeriod(final DateTime... dateTimes) {
		ArrayList<DateTime> arrayList = new ArrayList<DateTime>(Arrays.asList(dateTimes));
		Mockito.when(mockSimpleMovingAverageSignalsGenerator.getSellSignals(simpleMovingAverageDataList2, mockStockTickerHistory)).thenReturn(arrayList);
	}
	
	private void setSellSignalsForThirdPeriod(final DateTime... dateTimes) {
		ArrayList<DateTime> arrayList = new ArrayList<DateTime>(Arrays.asList(dateTimes));
		Mockito.when(mockSimpleMovingAverageSignalsGenerator.getSellSignals(simpleMovingAverageDataList3, mockStockTickerHistory)).thenReturn(arrayList);
	}
	
	
	DateTime buildFromString(final String strinDate) {
		return dateFormater.parseDateTime(strinDate);
	}
	
		   

	
	
}
