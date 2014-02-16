package indicators.simpleMovingAverage;

import indicators.williamsR.WilliamsRCollectionForTicker;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import data.collector.StockTickerHistory;

public class ComplexMovingAverageSignals {
	
	private ArrayList<DateTime> buySignal = new ArrayList<DateTime>();
	private ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
	
	private static org.apache.log4j.Logger log = Logger.getLogger(ComplexMovingAverageSignals.class);
	
	private void complexBuySignal(CompexMovingAverageIndicator averageCollection, StockTickerHistory stockCollection){
		SimpleMovingAverageSignals firstSignals = new SimpleMovingAverageSignals();
		SimpleMovingAverageSignals secondSignals = new SimpleMovingAverageSignals();
		SimpleMovingAverageSignals thirdSignals = new SimpleMovingAverageSignals();
		
		firstSignals.generateSimpleSignals(averageCollection.getFirstMovingAverage(), stockCollection);		
		secondSignals.generateSimpleSignals(averageCollection.getSecondMovingAverage(), stockCollection);	
		thirdSignals.generateSimpleSignals(averageCollection.getThirdMovingAverage(), stockCollection);

		log.info("Buy: ");
		
		for(int i=0; i<firstSignals.getBuySignal().size(); i++){
			DateTime date= firstSignals.getBuySignal().get(i);
			
			if((secondSignals.getBuySignal().contains(date)) && (thirdSignals.getBuySignal().contains(date))){
				buySignal.add(date);	
				log.info(date);
			}
		}
	}
	
	private void complexSellSignal(CompexMovingAverageIndicator averageCollection, StockTickerHistory stockCollection){
		SimpleMovingAverageSignals firstSignals = new SimpleMovingAverageSignals();
		SimpleMovingAverageSignals secondSignals = new SimpleMovingAverageSignals();
		SimpleMovingAverageSignals thirdSignals = new SimpleMovingAverageSignals();
		
		firstSignals.generateSimpleSignals(averageCollection.getFirstMovingAverage(), stockCollection);		
		secondSignals.generateSimpleSignals(averageCollection.getSecondMovingAverage(), stockCollection);	
		thirdSignals.generateSimpleSignals(averageCollection.getThirdMovingAverage(), stockCollection);
		
		log.info("Sell: ");

		for(int i=0; i<firstSignals.getSellSignal().size(); i++){
			DateTime date= firstSignals.getSellSignal().get(i);
			
			if((secondSignals.getSellSignal().contains(date)) && (thirdSignals.getSellSignal().contains(date))){
				sellSignal.add(date);	
				log.info(date);
			}
		}
	}
	
	public void generateComplexSignals(CompexMovingAverageIndicator averageCollection, StockTickerHistory stockCollection){
		complexBuySignal(averageCollection, stockCollection);
		complexSellSignal(averageCollection, stockCollection);
	}

	public ArrayList<DateTime> getBuySignal() {
		return buySignal;
	}

	public void setBuySignal(ArrayList<DateTime> buySignal) {
		this.buySignal = buySignal;
	}

	public ArrayList<DateTime> getSellSignal() {
		return sellSignal;
	}

	public void setSellSignal(ArrayList<DateTime> sellSignal) {
		this.sellSignal = sellSignal;
	}
}
