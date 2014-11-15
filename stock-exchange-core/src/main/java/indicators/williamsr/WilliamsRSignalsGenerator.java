package indicators.williamsr;

import data.collector.StockTickerHistory;
import indicators.Signal;
import indicators.SignalsGenerator;

import java.util.List;

/**
 * Created by mht on 11/11/14.
 */
public class WilliamsRSignalsGenerator implements SignalsGenerator {

    private final WilliamsRIndicator indicator;

    public WilliamsRSignalsGenerator(final int daysNum) {
        indicator = new WilliamsRIndicator(daysNum);
    }


    @Override
    public List<Signal> buySignals(final StockTickerHistory stockTickerHistory) {


        List<WilliamsRData> williamsRDatas = getIndicator().calculateWilliamsR(stockTickerHistory);


        return BuySignalsGenerator.generate(williamsRDatas);
    }

    @Override
    public List<Signal> sellSignals(final StockTickerHistory stockTickerHistory) {
        List<WilliamsRData> williamsRDatas = getIndicator().calculateWilliamsR(stockTickerHistory);


        return SellSignalsGenerator.generate(williamsRDatas);
    }

    WilliamsRIndicator getIndicator() {

        return this.indicator;
    }
}
