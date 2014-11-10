package buy.signal.measurements;

import java.util.List;

public class BiggestLoss {
	
	public double calulateInValue(List<Double> delta){	
		
		if(!wasThereLoss(delta)){
			throw new PriceDeltaEmptyException("There was no loss");			
		}
		
		return findSmallesValue(delta);	
	}

	private boolean wasThereLoss(List<Double> delta){
		
		if( findSmallesValue(delta) < 0.0 ){
			return true;
		}
		
		return false;
	}
	
	private double findSmallesValue(List<Double> delta) {
		
		if(delta.size() == 0){
			throw new PriceDeltaEmptyException("Delta is empty");
		}
		
		double temp = delta.get(0).doubleValue(); 
		
		for(int i = 0; i < delta.size(); i++){
			if(temp > delta.get(i).doubleValue()){
				temp = delta.get(i).doubleValue();
			}
		}
		return temp;
	}
}
