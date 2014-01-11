package data.collector;

import java.util.ArrayList;

public class StockTickerCollection {
	private ArrayList <StockTicker> stockTickerDataList = new ArrayList<StockTicker>();

	public ArrayList<StockTicker> getStockTickerDataList() {
		return stockTickerDataList;
	}

	public void setStockTickerDataList(ArrayList<StockTicker> stockTickerDataList) {
		this.stockTickerDataList = stockTickerDataList;
	}

	public void add(StockTicker dailyStock) {
		this.stockTickerDataList.add(dailyStock);		
	}
}
