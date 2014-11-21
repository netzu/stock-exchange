package indicators.movingaverage.simple;

public class SimpleAverageBuySignalGenerator extends AbstractSimpleAverageSignalGenerator {

    private final DecisionChainFactory decisionFactory;

    public SimpleAverageBuySignalGenerator() {
        this.decisionFactory = new BuyDecisionChainFactory();
    }


    @Override
    public DecisionChainFactory getDecisionChainFactory() {
        return this.decisionFactory;
    }
}
