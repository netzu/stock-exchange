package indicators.movingaverage.complex;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import configuration.Share;
import indicators.DateTimeFromSignal;
import indicators.Signal;
import indicators.movingaverage.simple.MovingAverageSignalsGenerator;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import data.collector.StockTickerHistory;

public class ComplexMovingAverageSellSignlasTest {

    private static final int FIRST_PERIOD = 1;
    private static final int SECOND_PERIOD = 2;
    private static final int THIRD_PERIOD = 3;
    StockTickerHistory mockStockTickerHistory;

    private List<Signal> firstPeriodSignals = Lists.newArrayList();
    private List<Signal> secondPeriodSignals = Lists.newArrayList();
    private List<Signal> thirdPeriodSignals = Lists.newArrayList();

    private MovingAverageSignalsGenerator mockMovingAverageSignalsGenerator;
    private ComplexMovingAverageSignalsGenerator complexMovingAverageSignalsGenerator;
    private ComplexMovingAverageSettings settings;
	
	@Before
	public void setup() {
        settings = new ComplexMovingAverageSettings(FIRST_PERIOD, SECOND_PERIOD, THIRD_PERIOD);
        mockMovingAverageSignalsGenerator = Mockito.mock(MovingAverageSignalsGenerator.class);
        mockStockTickerHistory = Mockito.mock(StockTickerHistory.class);
        when(mockMovingAverageSignalsGenerator.sellSignals(mockStockTickerHistory)).thenReturn(firstPeriodSignals, secondPeriodSignals, thirdPeriodSignals);

        complexMovingAverageSignalsGenerator = new ComplexMovingAverageSignalsGenerator(settings) {

            protected MovingAverageSignalsGenerator getSimpleAverageGenerator(final int period) {

                return mockMovingAverageSignalsGenerator;

            }

        };
	}
	
	
	
	@Test
	public void Test_1() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(buildFromString("20120303"));
		setSellSignalsForSecondPeriod(buildFromString("20120303"));
		setSellSignalsForThirdPeriod(buildFromString("20120303"));

        List<DateTime> sellSignals = Lists.transform(complexMovingAverageSignalsGenerator.sellSignals(mockStockTickerHistory), new DateTimeFromSignal());
		
		assertFalse(sellSignals.isEmpty()); 
		Assert.assertTrue(sellSignals.contains(buildFromString("20120303"))); 
	    assertEquals(1, sellSignals.size());		
	}
	
	@Test
	public void Test_2() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(buildFromString("20120303"));
		setSellSignalsForSecondPeriod(buildFromString("20120303"));
		setSellSignalsForThirdPeriod(new DateTime[0]);		
		

		List<DateTime> sellSignals = Lists.transform(complexMovingAverageSignalsGenerator.sellSignals(mockStockTickerHistory), new DateTimeFromSignal());
		
		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}
	
	@Test
	public void Test_3() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(buildFromString("20110303"));
		setSellSignalsForSecondPeriod(new DateTime[0]);
		setSellSignalsForThirdPeriod(buildFromString("20110303"));

        List<DateTime> sellSignals = Lists.transform(complexMovingAverageSignalsGenerator.sellSignals(mockStockTickerHistory), new DateTimeFromSignal());
		
		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}
	
	@Test
	public void Test_4() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(new DateTime[0]);
		setSellSignalsForSecondPeriod(buildFromString("20151023"));
		setSellSignalsForThirdPeriod(buildFromString("20151023"));

        List<DateTime> sellSignals = Lists.transform(complexMovingAverageSignalsGenerator.sellSignals(mockStockTickerHistory), new DateTimeFromSignal());

		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}
	
	@Test
	public void Test_5() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(buildFromString("20091007"));
		setSellSignalsForSecondPeriod(new DateTime[0]);
		setSellSignalsForThirdPeriod(new DateTime[0]);

        List<DateTime> sellSignals = Lists.transform(complexMovingAverageSignalsGenerator.sellSignals(mockStockTickerHistory), new DateTimeFromSignal());

		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}
	
	@Test
	public void Test_6() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(new DateTime[0]);
		setSellSignalsForSecondPeriod(new DateTime[0]);
		setSellSignalsForThirdPeriod(buildFromString("19990418"));

        List<DateTime> sellSignals = Lists.transform(complexMovingAverageSignalsGenerator.sellSignals(mockStockTickerHistory), new DateTimeFromSignal());

		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}
	
	@Test
	public void Test_7() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(new DateTime[0]);
		setSellSignalsForSecondPeriod(buildFromString("20080930"));
		setSellSignalsForThirdPeriod(new DateTime[0]);

        List<DateTime> sellSignals = Lists.transform(complexMovingAverageSignalsGenerator.sellSignals(mockStockTickerHistory), new DateTimeFromSignal());
		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}
	
	@Test
	public void Test_8() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(new DateTime[0]);
		setSellSignalsForSecondPeriod(new DateTime[0]);
		setSellSignalsForThirdPeriod(new DateTime[0]);

        List<DateTime> sellSignals = Lists.transform(complexMovingAverageSignalsGenerator.sellSignals(mockStockTickerHistory), new DateTimeFromSignal());

		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}

	@Test
	public void Test_9() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(buildFromString("20080930"));
		setSellSignalsForSecondPeriod(buildFromString("20080930"));
		setSellSignalsForThirdPeriod(buildFromString("20110709"));

        List<DateTime> sellSignals = Lists.transform(complexMovingAverageSignalsGenerator.sellSignals(mockStockTickerHistory), new DateTimeFromSignal());

		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}
	
	@Test
	public void Test_10() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(buildFromString("20140224"));
		setSellSignalsForSecondPeriod(buildFromString("20130913"));
		setSellSignalsForThirdPeriod(buildFromString("20140224"));

        List<DateTime> sellSignals = Lists.transform(complexMovingAverageSignalsGenerator.sellSignals(mockStockTickerHistory), new DateTimeFromSignal());

		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}
	
	@Test
	public void Test_11() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(buildFromString("20140224"));
		setSellSignalsForSecondPeriod(buildFromString("20130913"));
		setSellSignalsForThirdPeriod(buildFromString("20130913"));

        List<DateTime> sellSignals = Lists.transform(complexMovingAverageSignalsGenerator.sellSignals(mockStockTickerHistory), new DateTimeFromSignal());

		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}
	
	@Test
	public void Test_12() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(buildFromString("20121101"));
		setSellSignalsForSecondPeriod(buildFromString("20030913"));
		setSellSignalsForThirdPeriod(buildFromString("20111227"));

        List<DateTime> sellSignals = Lists.transform(complexMovingAverageSignalsGenerator.sellSignals(mockStockTickerHistory), new DateTimeFromSignal());

		Assert.assertTrue(sellSignals.isEmpty()); 
		assertEquals(0, sellSignals.size());
	}
	
	@Test
	public void Test_13() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(buildFromString("20121101"));
		setSellSignalsForSecondPeriod(buildFromString("20030913"),buildFromString("20140103"),buildFromString("20111019"),buildFromString("20121101"));
		setSellSignalsForThirdPeriod(buildFromString("20111227"), buildFromString("20121101"), buildFromString("20140103"));

        List<DateTime> sellSignals = Lists.transform(complexMovingAverageSignalsGenerator.sellSignals(mockStockTickerHistory), new DateTimeFromSignal());

		assertFalse(sellSignals.isEmpty()); 
		Assert.assertTrue(sellSignals.contains(buildFromString("20121101"))); 
	    assertEquals(1, sellSignals.size());
	}
	
	@Test
	public void Test_14() throws IOException, ParseException {

		setSellSignalsForFirstPeriod(buildFromString("20121101"), buildFromString("20140103"), buildFromString("19991221"));
		setSellSignalsForSecondPeriod(buildFromString("20030913"),buildFromString("20140103"),buildFromString("20111019"),buildFromString("20121101"));
		setSellSignalsForThirdPeriod(buildFromString("20111227"), buildFromString("20121101"), buildFromString("20140103"));

        List<DateTime> sellSignals = Lists.transform(complexMovingAverageSignalsGenerator.sellSignals(mockStockTickerHistory), new DateTimeFromSignal());

		assertFalse(sellSignals.isEmpty()); 
		Assert.assertTrue(sellSignals.contains(buildFromString("20121101"))); 
		Assert.assertTrue(sellSignals.contains(buildFromString("20140103"))); 
	    assertEquals(2, sellSignals.size());
	}
	
	
	
	private void setSellSignalsForFirstPeriod(final DateTime... dateTimes) {
        firstPeriodSignals.clear();
        firstPeriodSignals.addAll(Lists.transform(Lists.newArrayList(dateTimes), new DataTimeWrapperFunction()));
	}
	
	
	private void setSellSignalsForSecondPeriod(final DateTime... dateTimes) {
        secondPeriodSignals.clear();
        secondPeriodSignals.addAll(Lists.transform(Lists.newArrayList(dateTimes), new DataTimeWrapperFunction()));
	}
	
	private void setSellSignalsForThirdPeriod(final DateTime... dateTimes) {
        thirdPeriodSignals.clear();
        thirdPeriodSignals.addAll(Lists.transform(Lists.newArrayList(dateTimes), new DataTimeWrapperFunction()));
	}
	
	
	DateTime buildFromString(final String strinDate) {
		return Share.COMMON_FORMATTER.parseDateTime(strinDate);
	}
	
		   

	
	
}
