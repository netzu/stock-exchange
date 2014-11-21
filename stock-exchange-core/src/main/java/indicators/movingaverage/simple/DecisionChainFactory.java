package indicators.movingaverage.simple;

public interface DecisionChainFactory {

    DecisionChain createChain(double currentClose, double previousAverage, double currentAverage, double previousClose);

}
