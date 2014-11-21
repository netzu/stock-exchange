package indicators.movingaverage.simple;

/**
 * Created by mht on 20/11/14.
 */
class BuyDecisionChainFactory implements DecisionChainFactory {
    @Override
    public DecisionChain createChain(double currentClose, double previousAverage, double currentAverage, double previousClose) {
        DecisionChain dc = new DecisionChain();

        Smaller currentCloseSmallerPreviousClose = new Smaller(currentClose, previousAverage);
        SmallerOrEqual currentAverageSmallerOrEqualPreviousAverage = new SmallerOrEqual(currentAverage, previousAverage);
        Smaller currentCloseSmallerPreviousAverage = new Smaller(currentClose, previousAverage);
        Smaller previousAverageSmallerPreviousClose = new Smaller(previousAverage, previousClose);
        Smaller currentCloseSmallerCurrentAverage = new Smaller(currentClose, currentAverage);
        Smaller currentAverageSmallerPreviousClose = new Smaller(currentAverage, previousClose);

        dc.add(currentCloseSmallerPreviousClose);
        dc.add(currentAverageSmallerOrEqualPreviousAverage);
        dc.add(currentCloseSmallerPreviousAverage);
        dc.add(previousAverageSmallerPreviousClose);
        dc.add(currentCloseSmallerCurrentAverage);
        dc.add(currentAverageSmallerPreviousClose);

        return dc;
    }
}
