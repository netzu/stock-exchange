package indicators.williamsR;

import java.util.ArrayList;

import org.joda.time.DateTime;

public class WilliamsRSignals {
	private int revaluation = -80;   //wyprzedane
	private int undervaluation = -20;	//wykupione

	
	public ArrayList<DateTime> buySignals(ArrayList<WilliamsRData> collection) {
		
		double previousCloseSignal = collection.get(0).getWilliamsR();
		double currentCloseSignal = collection.get(1).getWilliamsR();
		
		ArrayList<DateTime> buySignal = new ArrayList<DateTime>();
				
		for(int i=1; i<collection.size(); i++) {
			
			previousCloseSignal = collection.get(i-1).getWilliamsR();
			currentCloseSignal = collection.get(i).getWilliamsR();
						
			if((previousCloseSignal<=revaluation) && (currentCloseSignal>revaluation)){
				buySignal.add(collection.get(i).getDate());
			}
		}	
		
		return buySignal;
	}
	
	public ArrayList<DateTime> sellSignals(ArrayList<WilliamsRData> collection) {
		double previousCloseSignal = collection.get(0).getWilliamsR();
		double currentCloseSignal = collection.get(1).getWilliamsR();
		
		ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
		
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
