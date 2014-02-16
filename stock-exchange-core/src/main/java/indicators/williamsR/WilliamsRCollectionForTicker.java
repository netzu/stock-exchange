package indicators.williamsR;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public class WilliamsRCollectionForTicker {
	
	private ArrayList<WilliamsRData> williamsR = new ArrayList<WilliamsRData>();
	
	private static org.apache.log4j.Logger log = Logger.getLogger(WilliamsRCollectionForTicker.class);
	
	public ArrayList<WilliamsRData> getWilliamsR() {
		return williamsR;
	}

	public void setWilliamsR(ArrayList<WilliamsRData> williamsR) {
		this.williamsR = williamsR;
	}
	
	public void addNewElementToTheList(WilliamsRData data){
		williamsR.add(data);
	}
	
	public void printToFile(){
		log.info("highestHigh, lowestLow, currentClose, date, williamsR");
		
		WilliamsRData data = new WilliamsRData();
		
		for(int i=0; i<this.williamsR.size(); i++) {
			data = this.williamsR.get(i);
			
			String out = new String(data.getHighestHigh() + ", " + data.getLowestLow() + ", " + data.getCurrentClose() + ", " + data.getDate() + ", " + data.getWilliamsR());
			
			log.info(out);
		}
	}
}
