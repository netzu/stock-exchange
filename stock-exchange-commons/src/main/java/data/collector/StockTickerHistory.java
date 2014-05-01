package data.collector;

import java.util.ArrayList;

import org.joda.time.DateTime;

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
		}
		return subList;
	}
	
	public StockTicker findStockByDate(DateTime date){
	
		if(stockTickerDataList.isEmpty()){
			throw new IllegalStateException("Empty Stock Ticker Collection");
		}
		
		if(date==null){
			throw new IllegalArgumentException("Date is null!");
		}
		 
		for(int i=0; i<this.stockTickerDataList.size();i++){
			DateTime current = stockTickerDataList.get(i).getDate();
			
			if(current.equals(date)){
				return stockTickerDataList.get(i);
			}
		}
		return null;
	}
}
