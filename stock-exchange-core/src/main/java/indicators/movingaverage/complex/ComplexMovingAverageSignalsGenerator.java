package indicators.movingaverage.complex;

import com.google.common.collect.Lists;
import data.collector.StockTickerHistory;
import indicators.DateTimeFromSignal;
import indicators.Signal;
import indicators.SignalsGenerator;
import indicators.movingaverage.simple.SimpleMovingAverageSignalsGenerator;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by mht on 21/11/14.
 */
public class ComplexMovingAverageSignalsGenerator implements SignalsGenerator {

    private final ComplexMovingAverageSettings settings;

    public ComplexMovingAverageSignalsGenerator(final ComplexMovingAverageSettings settings) {
        this.settings = settings;
    }

    @Override
    public List<Signal> buySignals(final StockTickerHistory stockTickerHistory) {


        List<DateTime> l1 = getRawBuyDateTimeFromSimpleAverage(settings.getPeriod1(), stockTickerHistory);
        List<DateTime> l2 = getRawBuyDateTimeFromSimpleAverage(settings.getPeriod2(), stockTickerHistory);
        List<DateTime> l3 = getRawBuyDateTimeFromSimpleAverage(settings.getPeriod3(), stockTickerHistory);


        final List<Signal> result = Lists.newArrayList();

        for (final DateTime dateTime : l1) {
            if (l2.contains(dateTime) && l3.contains(dateTime)) {
                result.add(new Signal(dateTime));
            }
        }
        return result;
    }

    @Override
    public List<Signal> sellSignals(final StockTickerHistory stockTickerHistory) {
        List<DateTime> l1 = getRawSellDateTimeFromSimpleAverage(settings.getPeriod1(), stockTickerHistory);
        List<DateTime> l2 = getRawSellDateTimeFromSimpleAverage(settings.getPeriod2(), stockTickerHistory);
        List<DateTime> l3 = getRawSellDateTimeFromSimpleAverage(settings.getPeriod3(), stockTickerHistory);


        final List<Signal> result = Lists.newArrayList();

        for (final DateTime dateTime : l1) {
            if (l2.contains(dateTime) && l3.contains(dateTime)) {
                result.add(new Signal(dateTime));
            }
        }
        return result;
    }

    private List<DateTime> getRawSellDateTimeFromSimpleAverage(final int period, final StockTickerHistory stockTickerHistory) {

        SimpleMovingAverageSignalsGenerator generator = getSimpleAverageGenerator(period);

        return Lists.transform(generator.sellSignals(stockTickerHistory), new DateTimeFromSignal());

    }

    private List<DateTime> getRawBuyDateTimeFromSimpleAverage(final int period, final StockTickerHistory stockTickerHistory) {

        SimpleMovingAverageSignalsGenerator generator = getSimpleAverageGenerator(period);

        return Lists.transform(generator.buySignals(stockTickerHistory), new DateTimeFromSignal());

    }


    protected SimpleMovingAverageSignalsGenerator getSimpleAverageGenerator(final int period) {

        return new SimpleMovingAverageSignalsGenerator(period);

    }

}
