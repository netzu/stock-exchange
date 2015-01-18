package indicators.movingaverage.simple;

/**
 * Created by mht on 20/11/14.
 */
class BuyDecisionChainFactory implements DecisionChainFactory {
    @Override
    public DecisionChain createChain(double currentClose, double previousAverage, double currentAverage, double previousClose) {
        DecisionChain dc = new DecisionChain();

        Smaller previousCloseSmallerThanCurrentClose = new Smaller(previousClose, currentClose);
        SmallerOrEqual previousAverageSmallerOrEqualCurrentAverage = new SmallerOrEqual(previousAverage, currentAverage);
        Smaller previousCloseSmallerPreviousAverage = new Smaller(previousClose, previousAverage);
        Smaller previousAverageSmallerCurrentClose = new Smaller(previousAverage, currentClose);
        Smaller previousCloseSmallerCurrentAverage = new Smaller(previousClose, currentAverage);
        Smaller currentAverageSmallerCurrentClose = new Smaller(currentAverage, currentClose);

        dc.add(previousCloseSmallerThanCurrentClose);
        dc.add(previousAverageSmallerOrEqualCurrentAverage);
        dc.add(previousCloseSmallerPreviousAverage);
        dc.add(previousAverageSmallerCurrentClose);
        dc.add(previousCloseSmallerCurrentAverage);
        dc.add(currentAverageSmallerCurrentClose);

        return dc;
    }
}
