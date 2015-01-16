package indicators.movingaverage.complex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import indicators.movingaverage.simple.MovingAverageSignalsGenerator;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.collect.Lists;

import configuration.Share;
import data.collector.StockTickerHistory;
import indicators.DateTimeFromSignal;
import indicators.Signal;

public class ComplexMovingAverageBuySignlasTest {

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
        when(mockMovingAverageSignalsGenerator.buySignals(mockStockTickerHistory)).thenReturn(firstPeriodSignals, secondPeriodSignals, thirdPeriodSignals);

        complexMovingAverageSignalsGenerator = new ComplexMovingAverageSignalsGenerator(settings) {

            protected MovingAverageSignalsGenerator getSimpleAverageGenerator(final int period) {

                return mockMovingAverageSignalsGenerator;

            }

        };

    }

    @Test
    public void Test_1() throws IOException, ParseException {

        setBuySignalsForFirstPeriod(buildFromString("20120303"));
        setBuySignalsForSecondPeriod(buildFromString("20120303"));
        setBuySignalsForThirdPeriod(buildFromString("20120303"));

        List<DateTime> buySignals =
            Lists.transform(complexMovingAverageSignalsGenerator.buySignals(mockStockTickerHistory),
                new DateTimeFromSignal());

        assertFalse(buySignals.isEmpty());
        Assert.assertTrue(buySignals.contains(buildFromString("20120303")));
        assertEquals(1, buySignals.size());
    }

    @Test
    public void Test_2() throws IOException, ParseException {

        setBuySignalsForFirstPeriod(buildFromString("20120303"));
        setBuySignalsForSecondPeriod(buildFromString("20120303"));
        setBuySignalsForThirdPeriod(new DateTime[0]);

        List<DateTime> buySignals =
            Lists.transform(complexMovingAverageSignalsGenerator.buySignals(mockStockTickerHistory),
                new DateTimeFromSignal());

        Assert.assertTrue(buySignals.isEmpty());
        assertEquals(0, buySignals.size());
    }

    @Test
    public void Test_3() throws IOException, ParseException {

        setBuySignalsForFirstPeriod(buildFromString("20110303"));
        setBuySignalsForSecondPeriod(new DateTime[0]);
        setBuySignalsForThirdPeriod(buildFromString("20110303"));

        ComplexMovingAverageSignalsGenerator signals = new ComplexMovingAverageSignalsGenerator(settings);

        List<DateTime> buySignals =
            Lists.transform(complexMovingAverageSignalsGenerator.buySignals(mockStockTickerHistory),
                new DateTimeFromSignal());

        Assert.assertTrue(buySignals.isEmpty());
        assertEquals(0, buySignals.size());
    }

    @Test
    public void Test_4() throws IOException, ParseException {

        setBuySignalsForFirstPeriod(new DateTime[0]);
        setBuySignalsForSecondPeriod(buildFromString("20151023"));
        setBuySignalsForThirdPeriod(buildFromString("20151023"));

        List<DateTime> buySignals =
            Lists.transform(complexMovingAverageSignalsGenerator.buySignals(mockStockTickerHistory),
                new DateTimeFromSignal());

        Assert.assertTrue(buySignals.isEmpty());
        assertEquals(0, buySignals.size());
    }

    @Test
    public void Test_5() throws IOException, ParseException {

        setBuySignalsForFirstPeriod(buildFromString("20091007"));
        setBuySignalsForSecondPeriod(new DateTime[0]);
        setBuySignalsForThirdPeriod(new DateTime[0]);

        List<DateTime> buySignals =
            Lists.transform(complexMovingAverageSignalsGenerator.buySignals(mockStockTickerHistory),
                new DateTimeFromSignal());

        Assert.assertTrue(buySignals.isEmpty());
        assertEquals(0, buySignals.size());
    }

    @Test
    public void Test_6() throws IOException, ParseException {

        setBuySignalsForFirstPeriod(new DateTime[0]);
        setBuySignalsForSecondPeriod(new DateTime[0]);
        setBuySignalsForThirdPeriod(buildFromString("19990418"));

        List<DateTime> buySignals =
            Lists.transform(complexMovingAverageSignalsGenerator.buySignals(mockStockTickerHistory),
                new DateTimeFromSignal());

        Assert.assertTrue(buySignals.isEmpty());
        assertEquals(0, buySignals.size());
    }

    @Test
    public void Test_7() throws IOException, ParseException {

        setBuySignalsForFirstPeriod(new DateTime[0]);
        setBuySignalsForSecondPeriod(buildFromString("20080930"));
        setBuySignalsForThirdPeriod(new DateTime[0]);

        ComplexMovingAverageSignalsGenerator signals = new ComplexMovingAverageSignalsGenerator(settings);

        List<DateTime> buySignals =
            Lists.transform(complexMovingAverageSignalsGenerator.buySignals(mockStockTickerHistory),
                new DateTimeFromSignal());

        Assert.assertTrue(buySignals.isEmpty());
        assertEquals(0, buySignals.size());
    }

    @Test
    public void Test_8() throws IOException, ParseException {

        setBuySignalsForFirstPeriod(new DateTime[0]);
        setBuySignalsForSecondPeriod(new DateTime[0]);
        setBuySignalsForThirdPeriod(new DateTime[0]);

        List<DateTime> buySignals =
            Lists.transform(complexMovingAverageSignalsGenerator.buySignals(mockStockTickerHistory),
                new DateTimeFromSignal());

        Assert.assertTrue(buySignals.isEmpty());
        assertEquals(0, buySignals.size());
    }

    @Test
    public void Test_9() throws IOException, ParseException {

        setBuySignalsForFirstPeriod(buildFromString("20080930"));
        setBuySignalsForSecondPeriod(buildFromString("20080930"));
        setBuySignalsForThirdPeriod(buildFromString("20110709"));

        List<DateTime> buySignals = Lists.transform(complexMovingAverageSignalsGenerator.buySignals(mockStockTickerHistory), new DateTimeFromSignal());
        Assert.assertTrue(buySignals.isEmpty());
        assertEquals(0, buySignals.size());
    }

    @Test
    public void Test_10() throws IOException, ParseException {

        setBuySignalsForFirstPeriod(buildFromString("20140224"));
        setBuySignalsForSecondPeriod(buildFromString("20130913"));
        setBuySignalsForThirdPeriod(buildFromString("20140224"));

        List<DateTime> buySignals = Lists.transform(complexMovingAverageSignalsGenerator.buySignals(mockStockTickerHistory), new DateTimeFromSignal());
        Assert.assertTrue(buySignals.isEmpty());
        assertEquals(0, buySignals.size());
    }

    @Test
    public void Test_11() throws IOException, ParseException {

        setBuySignalsForFirstPeriod(buildFromString("20140224"));
        setBuySignalsForSecondPeriod(buildFromString("20130913"));
        setBuySignalsForThirdPeriod(buildFromString("20130913"));

        List<DateTime> buySignals = Lists.transform(complexMovingAverageSignalsGenerator.buySignals(mockStockTickerHistory), new DateTimeFromSignal());
        Assert.assertTrue(buySignals.isEmpty());
        assertEquals(0, buySignals.size());
    }

    @Test
    public void Test_12() throws IOException, ParseException {

        setBuySignalsForFirstPeriod(buildFromString("20121101"));
        setBuySignalsForSecondPeriod(buildFromString("20030913"));
        setBuySignalsForThirdPeriod(buildFromString("20111227"));

        List<DateTime> buySignals = Lists.transform(complexMovingAverageSignalsGenerator.buySignals(mockStockTickerHistory), new DateTimeFromSignal());
        Assert.assertTrue(buySignals.isEmpty());
        assertEquals(0, buySignals.size());
    }

    //TODO enable and check does it works
    @Test
    @Ignore("Marked as ignore because it was not annotated as test, turn on later")
    public void Test_13() throws IOException, ParseException {

        setBuySignalsForFirstPeriod(buildFromString("20121101"));
        setBuySignalsForSecondPeriod(buildFromString("20030913"), buildFromString("20140103"), buildFromString("20111019"),
            buildFromString("20121101"));
        setBuySignalsForThirdPeriod(buildFromString("20111227"), buildFromString("20121101"), buildFromString("20140103"));

        List<DateTime> buySignals = Lists.transform(complexMovingAverageSignalsGenerator.buySignals(mockStockTickerHistory), new DateTimeFromSignal());

        assertFalse(buySignals.isEmpty());
        Assert.assertTrue(buySignals.contains(buildFromString("20121101")));
        assertEquals(1, buySignals.size());
    }

    @Test
    public void Test_14() throws IOException, ParseException {

        setBuySignalsForFirstPeriod(buildFromString("20121101"), buildFromString("20140103"), buildFromString("19991221"));
        setBuySignalsForSecondPeriod(buildFromString("20030913"), buildFromString("20140103"), buildFromString("20111019"),
            buildFromString("20121101"));
        setBuySignalsForThirdPeriod(buildFromString("20111227"), buildFromString("20121101"), buildFromString("20140103"));

        List<DateTime> buySignals = Lists.transform(complexMovingAverageSignalsGenerator.buySignals(mockStockTickerHistory), new DateTimeFromSignal());
        assertFalse(buySignals.isEmpty());
        Assert.assertTrue(buySignals.contains(buildFromString("20121101")));
        Assert.assertTrue(buySignals.contains(buildFromString("20140103")));
        assertEquals(2, buySignals.size());
    }

    private void setBuySignalsForFirstPeriod(final DateTime... dateTimes) {
        firstPeriodSignals.clear();
        firstPeriodSignals.addAll(Lists.transform(Lists.newArrayList(dateTimes), new DataTimeWrapperFunction()));
    }

    private void setBuySignalsForSecondPeriod(final DateTime... dateTimes) {
        secondPeriodSignals.clear();
        secondPeriodSignals.addAll(Lists.transform(Lists.newArrayList(dateTimes), new DataTimeWrapperFunction()));
    }

    private void setBuySignalsForThirdPeriod(final DateTime... dateTimes) {
        thirdPeriodSignals.clear();
        thirdPeriodSignals.addAll(Lists.transform(Lists.newArrayList(dateTimes), new DataTimeWrapperFunction()));
    }

    DateTime buildFromString(final String strinDate) {
        return Share.COMMON_FORMATTER.parseDateTime(strinDate);
    }

}
