package indicators.movingaverage.simple;

import data.collector.StockTickerHistory;
import indicators.Signal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mht on 20/11/14.
 */
public abstract class AbstractSimpleAverageSignalGenerator {


    public List<Signal> generate(List<SimpleMovingAverageData> averageCollection, StockTickerHistory stockCollection){
        double previousClose;
        double currentClose;
        double previousAverage;
        double currentAverage;

        List<Signal> sellSignal = new ArrayList<Signal>();

        int startPoint = stockCollection.getEODTickDataList().size() - averageCollection.size();

        for(int i=1; i<averageCollection.size(); i++){
            previousClose = stockCollection.getEODTickDataList().get(i-1+startPoint).getClose();
            currentClose = stockCollection.getEODTickDataList().get(i+startPoint).getClose();

            previousAverage = averageCollection.get(i-1).getAverage();
            currentAverage = averageCollection.get(i).getAverage();

            DecisionChain decisionChain = getDecisionChainFactory().createChain(currentClose, previousAverage, currentAverage, previousClose);

            if(decisionChain.evaluate()) {
                sellSignal.add(new Signal(averageCollection.get(i).getDate()));
            }
        }
        return sellSignal;
    }

    public abstract DecisionChainFactory getDecisionChainFactory();
}
