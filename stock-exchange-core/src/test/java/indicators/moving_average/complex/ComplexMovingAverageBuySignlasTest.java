package indicators.moving_average.complex;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import indicators.moving_average.complex.AverageData;
import indicators.moving_average.complex.ComplexMovingAverageSignals;
import indicators.moving_average.simple.SimpleMovingAverageData;
import indicators.moving_average.simple.SimpleMovingAverageSignals;

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

public class ComplexMovingAverageBuySignlasTest {
	
	DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");
	StockTickerHistory mockStockTickerHistory;
	AverageData mockAverageData1;
	AverageData mockAverageData2;
	AverageData mockAverageData3;
	
	List<AverageData> averageDataList;

	List<SimpleMovingAverageData> simpleMovingAverageDataList1 = Mockito.mock(ArrayList.class);
	List<SimpleMovingAverageData> simpleMovingAverageDataList2 = Mockito.mock(ArrayList.class);
	List<SimpleMovingAverageData> simpleMovingAverageDataList3 = Mockito.mock(ArrayList.class);
	
	private SimpleMovingAverageSignals mockSimpleMovingAverageSignals;
	
	@Before
	public void setup() {
		mockSimpleMovingAverageSignals = Mockito.mock(SimpleMovingAverageSignals.class);
		mockStockTickerHistory = Mockito.mock(StockTickerHistory.class);
		mockAverageData1 = Mockito.mock(AverageData.class);
		mockAverageData2 = Mockito.mock(AverageData.class);
		mockAverageData3 = Mockito.mock(AverageData.class);
		
		Mockito.when(mockAverageData1.getAverageData()).thenReturn(simpleMovingAverageDataList1);
		Mockito.when(mockAverageData2.getAverageData()).thenReturn(simpleMovingAverageDataList2);
		Mockito.when(mockAverageData3.getAverageData()).thenReturn(simpleMovingAverageDataList3);
		
		averageDataList = Arrays.asList(mockAverageData1, mockAverageData2, mockAverageData3);
	}
	
	
	
	@Test
	public void Test_1() throws IOException, ParseException {

		setBuySignalsForFirstPeriod(buildFromString("20120303"));
		setBuySignalsForSecondPeriod(buildFromString("20120303"));
		setBuySignalsForThirdPeriod(buildFromString("20120303"));		
		
		ComplexMovingAverageSignals signals = new ComplexMovingAverageSignals();
		
		List<DateTime> buySignals = signals.buysignal(averageDataList,mockSimpleMovingAverageSignals, mockStockTickerHistory);
		
		assertFalse(buySignals.isEmpty()); 
		Assert.assertTrue(buySignals.contains(buildFromString("20120303"))); 
	    assertEquals(1, buySignals.size());		
	}
	
	@Test
	public void Test_2() throws IOException, ParseException {

		setBuySignalsForFirstPeriod(buildFromString("20120303"));
		setBuySignalsForSecondPeriod(buildFromString("20120303"));
		setBuySignalsForThirdPeriod(new DateTime[0]);		
		
		ComplexMovingAverageSignals signals = new ComplexMovingAverageSignals();
		
		List<DateTime> buySignals = signals.buysignal(averageDataList, mockSimpleMovingAverageSignals, mockStockTickerHistory);
		
		Assert.assertTrue(buySignals.isEmpty()); 
		assertEquals(0, buySignals.size());
	}
	
	@Test
	public void Test_3() throws IOException, ParseException {

		setBuySignalsForFirstPeriod(buildFromString("20110303"));
		setBuySignalsForSecondPeriod(new DateTime[0]);
		setBuySignalsForThirdPeriod(buildFromString("20110303"));		
		
		ComplexMovingAverageSignals signals = new ComplexMovingAverageSignals();
		
		List<DateTime> buySignals = signals.buysignal(averageDataList, mockSimpleMovingAverageSignals, mockStockTickerHistory);
		
		Assert.assertTrue(buySignals.isEmpty()); 
		assertEquals(0, buySignals.size());
	}
	
	@Test
	public void Test_4() throws IOException, ParseException {

		setBuySignalsForFirstPeriod(new DateTime[0]);
		setBuySignalsForSecondPeriod(buildFromString("20151023"));
		setBuySignalsForThirdPeriod(buildFromString("20151023"));		
		
		ComplexMovingAverageSignals signals = new ComplexMovingAverageSignals();
		
		List<DateTime> buySignals = signals.buysignal(averageDataList, mockSimpleMovingAverageSignals, mockStockTickerHistory);

		Assert.assertTrue(buySignals.isEmpty()); 
		assertEquals(0, buySignals.size());
	}
	
	@Test
	public void Test_5() throws IOException, ParseException {

		setBuySignalsForFirstPeriod(buildFromString("20091007"));
		setBuySignalsForSecondPeriod(new DateTime[0]);
		setBuySignalsForThirdPeriod(new DateTime[0]);		
		
		ComplexMovingAverageSignals signals = new ComplexMovingAverageSignals();
		
		List<DateTime> buySignals = signals.buysignal(averageDataList, mockSimpleMovingAverageSignals, mockStockTickerHistory);

		Assert.assertTrue(buySignals.isEmpty()); 
		assertEquals(0, buySignals.size());
	}
	
	@Test
	public void Test_6() throws IOException, ParseException {

		setBuySignalsForFirstPeriod(new DateTime[0]);
		setBuySignalsForSecondPeriod(new DateTime[0]);
		setBuySignalsForThirdPeriod(buildFromString("19990418"));		
		
		ComplexMovingAverageSignals signals = new ComplexMovingAverageSignals();
		
		List<DateTime> buySignals = signals.buysignal(averageDataList, mockSimpleMovingAverageSignals, mockStockTickerHistory);

		Assert.assertTrue(buySignals.isEmpty()); 
		assertEquals(0, buySignals.size());
	}
	
	@Test
	public void Test_7() throws IOException, ParseException {

		setBuySignalsForFirstPeriod(new DateTime[0]);
		setBuySignalsForSecondPeriod(buildFromString("20080930"));
		setBuySignalsForThirdPeriod(new DateTime[0]);		
		
		ComplexMovingAverageSignals signals = new ComplexMovingAverageSignals();
		
		List<DateTime> buySignals = signals.buysignal(averageDataList, mockSimpleMovingAverageSignals, mockStockTickerHistory);

		Assert.assertTrue(buySignals.isEmpty()); 
		assertEquals(0, buySignals.size());
	}
	
	@Test
	public void Test_8() throws IOException, ParseException {

		setBuySignalsForFirstPeriod(new DateTime[0]);
		setBuySignalsForSecondPeriod(new DateTime[0]);
		setBuySignalsForThirdPeriod(new DateTime[0]);		
		
		ComplexMovingAverageSignals signals = new ComplexMovingAverageSignals();
		
		List<DateTime> buySignals = signals.buysignal(averageDataList, mockSimpleMovingAverageSignals, mockStockTickerHistory);

		Assert.assertTrue(buySignals.isEmpty()); 
		assertEquals(0, buySignals.size());
	}

	@Test
	public void Test_9() throws IOException, ParseException {

		setBuySignalsForFirstPeriod(buildFromString("20080930"));
		setBuySignalsForSecondPeriod(buildFromString("20080930"));
		setBuySignalsForThirdPeriod(buildFromString("20110709"));		
		
		ComplexMovingAverageSignals signals = new ComplexMovingAverageSignals();
		
		List<DateTime> buySignals = signals.buysignal(averageDataList, mockSimpleMovingAverageSignals, mockStockTickerHistory);

		Assert.assertTrue(buySignals.isEmpty()); 
		assertEquals(0, buySignals.size());
	}
	
	@Test
	public void Test_10() throws IOException, ParseException {

		setBuySignalsForFirstPeriod(buildFromString("20140224"));
		setBuySignalsForSecondPeriod(buildFromString("20130913"));
		setBuySignalsForThirdPeriod(buildFromString("20140224"));		
		
		ComplexMovingAverageSignals signals = new ComplexMovingAverageSignals();
		
		List<DateTime> buySignals = signals.buysignal(averageDataList, mockSimpleMovingAverageSignals, mockStockTickerHistory);

		Assert.assertTrue(buySignals.isEmpty()); 
		assertEquals(0, buySignals.size());
	}
	
	@Test
	public void Test_11() throws IOException, ParseException {

		setBuySignalsForFirstPeriod(buildFromString("20140224"));
		setBuySignalsForSecondPeriod(buildFromString("20130913"));
		setBuySignalsForThirdPeriod(buildFromString("20130913"));		
		
		ComplexMovingAverageSignals signals = new ComplexMovingAverageSignals();
		
		List<DateTime> buySignals = signals.buysignal(averageDataList, mockSimpleMovingAverageSignals, mockStockTickerHistory);

		Assert.assertTrue(buySignals.isEmpty()); 
		assertEquals(0, buySignals.size());
	}
	
	@Test
	public void Test_12() throws IOException, ParseException {

		setBuySignalsForFirstPeriod(buildFromString("20121101"));
		setBuySignalsForSecondPeriod(buildFromString("20030913"));
		setBuySignalsForThirdPeriod(buildFromString("20111227"));		
		
		ComplexMovingAverageSignals signals = new ComplexMovingAverageSignals();
		
		List<DateTime> buySignals = signals.buysignal(averageDataList, mockSimpleMovingAverageSignals, mockStockTickerHistory);

		Assert.assertTrue(buySignals.isEmpty()); 
		assertEquals(0, buySignals.size());
	}
	
	public void Test_13() throws IOException, ParseException {

		setBuySignalsForFirstPeriod(buildFromString("20121101"));
		setBuySignalsForSecondPeriod(buildFromString("20030913"),buildFromString("20140103"),buildFromString("20111019"),buildFromString("20121101"));
		setBuySignalsForThirdPeriod(buildFromString("20111227"), buildFromString("20121101"), buildFromString("20140103"));		
		
		ComplexMovingAverageSignals signals = new ComplexMovingAverageSignals();
		
		List<DateTime> buySignals = signals.buysignal(averageDataList, mockSimpleMovingAverageSignals, mockStockTickerHistory);

		assertFalse(buySignals.isEmpty()); 
		Assert.assertTrue(buySignals.contains(buildFromString("20121101"))); 
	    assertEquals(1, buySignals.size());
	}
	
	@Test
	public void Test_14() throws IOException, ParseException {

		setBuySignalsForFirstPeriod(buildFromString("20121101"), buildFromString("20140103"), buildFromString("19991221"));
		setBuySignalsForSecondPeriod(buildFromString("20030913"),buildFromString("20140103"),buildFromString("20111019"),buildFromString("20121101"));
		setBuySignalsForThirdPeriod(buildFromString("20111227"), buildFromString("20121101"), buildFromString("20140103"));		
		
		ComplexMovingAverageSignals signals = new ComplexMovingAverageSignals();
		
		List<DateTime> buySignals = signals.buysignal(averageDataList, mockSimpleMovingAverageSignals, mockStockTickerHistory);

		assertFalse(buySignals.isEmpty()); 
		Assert.assertTrue(buySignals.contains(buildFromString("20121101"))); 
		Assert.assertTrue(buySignals.contains(buildFromString("20140103"))); 
	    assertEquals(2, buySignals.size());
	}
	
	
	private void setBuySignalsForFirstPeriod(final DateTime... dateTimes) {
		ArrayList<DateTime> arrayList = new ArrayList<DateTime>(Arrays.asList(dateTimes));
		Mockito.when(mockSimpleMovingAverageSignals.buySignal(simpleMovingAverageDataList1, mockStockTickerHistory)).thenReturn(arrayList);
	}
	
	
	private void setBuySignalsForSecondPeriod(final DateTime... dateTimes) {
		ArrayList<DateTime> arrayList = new ArrayList<DateTime>(Arrays.asList(dateTimes));
		Mockito.when(mockSimpleMovingAverageSignals.buySignal(simpleMovingAverageDataList2, mockStockTickerHistory)).thenReturn(arrayList);
	}
	
	private void setBuySignalsForThirdPeriod(final DateTime... dateTimes) {
		ArrayList<DateTime> arrayList = new ArrayList<DateTime>(Arrays.asList(dateTimes));
		Mockito.when(mockSimpleMovingAverageSignals.buySignal(simpleMovingAverageDataList3, mockStockTickerHistory)).thenReturn(arrayList);
	}
	
	
	DateTime buildFromString(final String strinDate) {
		return dateFormater.parseDateTime(strinDate);
	}
	
		   

	
	
}
