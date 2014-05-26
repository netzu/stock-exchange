package indicators.movingaverage.simple;

public class Smaller implements DecisionMaker {

	private double first;
	private double second;

	public Smaller(double first, double second) {
		this.first = first;
		this.second = second;
	}

	@Override
	/**
	 * check if previous is smaller than current
	 * @see indicators.movingaverage.simple.DecisionMaker#isTrue()
	 */
	public boolean isTrue() {
		return first < second;
	}

}
