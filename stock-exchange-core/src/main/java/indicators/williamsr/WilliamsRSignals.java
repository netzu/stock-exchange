package indicators.williamsr;

import java.util.List;

import org.joda.time.DateTime;

public class WilliamsRSignals {

	public List<DateTime> buySignals(List<WilliamsRData> collection) {
		BuySignalsGenerator signals = new BuySignalsGenerator();
		return signals.generate(collection);
	}

	public List<DateTime> sellSignals(List<WilliamsRData> collection) {
		SellSignalsGenerator signals = new SellSignalsGenerator();
		return signals.generate(collection);
	}
}
