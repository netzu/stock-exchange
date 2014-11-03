package buy.signal.test;

import java.util.List;

import org.joda.time.DateTime;

public class BuySignal {
	private String ticker;
	private List<DateTime> listOfSignals;

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public List<DateTime> getListOfSignals() {
		return listOfSignals;
	}

	public void setListOfSignals(List<DateTime> listOfSignals) {
		this.listOfSignals = listOfSignals;
	}
	
}
