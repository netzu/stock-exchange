package indicators.williamsR;

import java.util.ArrayList;

import org.joda.time.DateTime;

public class WilliamsRSignals {
	private int revaluation = -80;   //wyprzedane
	private int undervaluation = -20;	//wykupione
	
	private ArrayList<DateTime> buySignal = new ArrayList<DateTime>();
	private ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
	
	public ArrayList<DateTime> getBuySignal() {
		return buySignal;
	}

	public ArrayList<DateTime> getSellSignal() {
		return sellSignal;
	}

	
	private void calculateBuysignals(WilliamsRCollectionForTicker collection) {
		
		double previousCloseSignal = collection.getWilliamsR().get(0).getWilliamsR();
		double currentCloseSignal = collection.getWilliamsR().get(1).getWilliamsR();
				
		for(int i=1; i<collection.getWilliamsR().size(); i++) {
			
			previousCloseSignal = collection.getWilliamsR().get(i-1).getWilliamsR();
			currentCloseSignal = collection.getWilliamsR().get(i).getWilliamsR();
						
			if((previousCloseSignal<=revaluation) && (currentCloseSignal>revaluation)){
				buySignal.add(collection.getWilliamsR().get(i).getDate());
			}
		}		
	}
	
	private void calculateSellsignals(WilliamsRCollectionForTicker collection) {
		double previousCloseSignal = collection.getWilliamsR().get(0).getWilliamsR();
		double currentCloseSignal = collection.getWilliamsR().get(1).getWilliamsR();
		
		for(int i=1; i<collection.getWilliamsR().size(); i++) {
			
			previousCloseSignal = collection.getWilliamsR().get(i-1).getWilliamsR();
			currentCloseSignal = collection.getWilliamsR().get(i).getWilliamsR();
						
			if((previousCloseSignal>=undervaluation) && (currentCloseSignal<undervaluation)){
				sellSignal.add(collection.getWilliamsR().get(i).getDate());
			}
		}
	}
	
	public void generateWilliamsRSignals(WilliamsRCollectionForTicker collection) {
		calculateBuysignals(collection);
		calculateSellsignals(collection);
	}

}
