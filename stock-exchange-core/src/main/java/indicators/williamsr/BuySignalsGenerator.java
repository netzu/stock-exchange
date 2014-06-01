package indicators.williamsr;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class BuySignalsGenerator {

	private static final int REVALUATION_TRESHOLD = -80;

	public List<DateTime> generate(List<WilliamsRData> williamsRCollection) {
		
		if(williamsRCollection.isEmpty()){
			throw new WilliamsRCalculationException("Cannot generate buy signal for empty williams R collection");
		}
		
		if(williamsRCollection.size()<2){
			throw new WilliamsRCalculationException("Cannot generate buy signal for williams R collection which has less than 2 elements");
		}

		double previousWilliamsValue = williamsRCollection.get(0).getWilliamsR();
		double currentWilliamsValue = williamsRCollection.get(1).getWilliamsR();

		List<DateTime> buySignal = new ArrayList<DateTime>();

		for(int i=1; i<williamsRCollection.size(); i++) {

			previousWilliamsValue = williamsRCollection.get(i-1).getWilliamsR();
			currentWilliamsValue = williamsRCollection.get(i).getWilliamsR();
			
			if((previousWilliamsValue<=REVALUATION_TRESHOLD) && (currentWilliamsValue>REVALUATION_TRESHOLD)){
				buySignal.add(williamsRCollection.get(i).getDate());
			}
		}
		return buySignal;
	}
}
