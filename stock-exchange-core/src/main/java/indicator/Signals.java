package indicator;

import java.util.ArrayList;

import org.joda.time.DateTime;

import data.collector.StockTickerHistory;

public interface Signals {
	
	ArrayList<DateTime> buySignal = new ArrayList<DateTime>();
	ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
	
	public void caclculateSignals(StockTickerHistory stockCollection);

}
