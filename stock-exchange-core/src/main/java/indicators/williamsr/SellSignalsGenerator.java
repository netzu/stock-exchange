package indicators.williamsr;

import java.util.ArrayList;
import java.util.List;

import indicators.Signal;
import org.joda.time.DateTime;

class SellSignalsGenerator {

	private static final int UNDERVALUATION_TRESHOLD = -20;

	static List<Signal> generate(List<WilliamsRData> williamsRCollection) {
		
		if(williamsRCollection.isEmpty()){
			throw new WilliamsRCalculationException("Cannot generate sell signal for empty williams R collection");
		}
		
		if(williamsRCollection.size()<2){
			throw new WilliamsRCalculationException("Cannot generate sell signal for williams R collection which has less than 2 elements");
		}
		
		double previousWilliamsValue = williamsRCollection.get(0).getWilliamsR();
		double currentWilliamsValue = williamsRCollection.get(1).getWilliamsR();

		List<Signal> sellSignal = new ArrayList<Signal>();

		for(int i=1; i<williamsRCollection.size(); i++) {

			previousWilliamsValue = williamsRCollection.get(i-1).getWilliamsR();
			currentWilliamsValue = williamsRCollection.get(i).getWilliamsR();

			if((previousWilliamsValue>=UNDERVALUATION_TRESHOLD) && (currentWilliamsValue<UNDERVALUATION_TRESHOLD)){
				sellSignal.add(new Signal(williamsRCollection.get(i).getDate()));
			}
		}
		return sellSignal;
	}
}
