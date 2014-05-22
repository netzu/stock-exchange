package indicators.williams_r;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class WilliamsRSignals {
	private int revaluation = -80;   //wyprzedane
	private int undervaluation = -20;	//wykupione

	
	public List<DateTime> buySignals(ArrayList<WilliamsRData> collection) {
		
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
	
	public List<DateTime> sellSignals(ArrayList<WilliamsRData> collection) {
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
