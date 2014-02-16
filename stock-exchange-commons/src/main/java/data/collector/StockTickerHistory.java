package data.collector;

import java.util.ArrayList;

public class StockTickerHistory {
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
	
	public ArrayList <StockTicker> subListOfCollection(int from, int to){
		ArrayList <StockTicker> subList = new ArrayList<StockTicker>();
		
		for(int i = from; i<to; i++){
			subList.add(stockTickerDataList.get(i));
			//stockTickerDataList.get(i).printStockTickerData();
			//System.out.println(stockTickerDataList.get(i).getDate());
		}
		return subList;
	}
}
