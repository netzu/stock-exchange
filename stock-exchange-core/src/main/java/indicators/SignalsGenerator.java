package indicators;

import data.collector.StockTickerHistory;

import java.util.List;

/**
 * Created by mht on 11/11/14.
 */
public interface SignalsGenerator {

    List<Signal> buySignals(StockTickerHistory stockTickerHistory);
    List<Signal> sellSignals(StockTickerHistory stockTickerHistory);
}
