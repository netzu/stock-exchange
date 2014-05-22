package indicators.williamsr;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class WilliamsRSignals {
	//wyprzedane
	private static final int UNDERVALUATION_THRESHOLD = -20;
	//wykupione
	private static final int REVALUATION_TRESHOLD = -80;
	
	private int revaluation = REVALUATION_TRESHOLD;
	private int undervaluation = UNDERVALUATION_THRESHOLD;

	
	public List<DateTime> buySignals(List<WilliamsRData> collection) {
		
		double previousCloseSignal = collection.get(0).getWilliamsR();
		double currentCloseSignal = collection.get(1).getWilliamsR();
		
		List<DateTime> buySignal = new ArrayList<DateTime>();
				
		for(int i=1; i<collection.size(); i++) {
			
			previousCloseSignal = collection.get(i-1).getWilliamsR();
			currentCloseSignal = collection.get(i).getWilliamsR();
						
			if((previousCloseSignal<=revaluation) && (currentCloseSignal>revaluation)){
				buySignal.add(collection.get(i).getDate());
			}
		}	
		
		return buySignal;
	}
	
	public List<DateTime> sellSignals(List<WilliamsRData> collection) {
		double previousCloseSignal = collection.get(0).getWilliamsR();
		double currentCloseSignal = collection.get(1).getWilliamsR();
		
		List<DateTime> sellSignal = new ArrayList<DateTime>();
		
		for(int i=1; i<collection.size(); i++) {
			
			previousCloseSignal = collection.get(i-1).getWilliamsR();
			currentCloseSignal = collection.get(i).getWilliamsR();
						
			if((previousCloseSignal>=undervaluation) && (currentCloseSignal<undervaluation)){
				sellSignal.add(collection.get(i).getDate());
			}
		}
		
		return sellSignal;
	}
}
