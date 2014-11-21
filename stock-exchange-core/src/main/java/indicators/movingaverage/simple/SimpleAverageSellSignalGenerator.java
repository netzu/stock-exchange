package indicators.movingaverage.simple;

public class SimpleAverageSellSignalGenerator extends AbstractSimpleAverageSignalGenerator {

    private final DecisionChainFactory decisionChainFactory;

    public SimpleAverageSellSignalGenerator() {
        this.decisionChainFactory = new SellDecisionChainFactory();
    }


    @Override
    public DecisionChainFactory getDecisionChainFactory() {
        return this.decisionChainFactory;
    }
}
