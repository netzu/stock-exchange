package indicators.movingaverage.complex;

public class ComplexMovingAverageSettings {

    private final int period1;
    private final int period2;
    private final int period3;

    public ComplexMovingAverageSettings(final int period1, final int period2, final int period3) {
        this.period1 = period1;
        this.period2 = period2;
        this.period3 = period3;
    }

    public int getPeriod1() {
        return period1;
    }

    public int getPeriod2() {
        return period2;
    }

    public int getPeriod3() {
        return period3;
    }
}
