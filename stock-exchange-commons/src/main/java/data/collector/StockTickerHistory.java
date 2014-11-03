package data.collector;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class StockTickerHistory {

	private List<StockTicker> stockTickerDataList = new ArrayList<StockTicker>();

	public List<StockTicker> getStockTickerDataList() {
		return stockTickerDataList;
	}

	public void setStockTickerDataList(List<StockTicker> stockTickerDataList) {
		this.stockTickerDataList = stockTickerDataList;
	}

	public void add(StockTicker dailyStock) {
		this.stockTickerDataList.add(dailyStock);		
	}
	
	public List <StockTicker> subListOfCollection(int from, int to){
		
		if(from >= to){
			throw new StockExchangeIllegalStateException("Cannot create sublist when FROM is greather or equal TO");
		}
		
		List <StockTicker> subList = new ArrayList<StockTicker>();
		
		for(int i = from; i<to; i++){
			subList.add(stockTickerDataList.get(i));
		}
		return subList;
	}
	
	public StockTicker findStockByDate(DateTime date){
	
		if(stockTickerDataList.isEmpty()){
			throw new StockExchangeIllegalStateException("Cannot find stock by date if ticker's collection is empty");
		}
		
		if(date==null){
			throw new StockExchangeIllegalStateException("Cannot find ticker by date if date is null");
		}
		 
		for(int i=0; i<this.stockTickerDataList.size();i++){
			DateTime current = stockTickerDataList.get(i).getDate();
			
			if(current.equals(date)){
				return stockTickerDataList.get(i);
			}
		}
		
		throw new StockExchangeIllegalStateException("Could not find a stock in given day");
	}
	
	public int findStockIndexByDate(DateTime date){
		
		int index = -1;
		
		if(stockTickerDataList.isEmpty()){
			throw new StockExchangeIllegalStateException("Cannot find index for a ticker with given date for an empty ticker collection");
		}
		
		if(date==null){
			throw new StockExchangeIllegalStateException("Cannot find index for a ticker when date is null");
		}
		 
		for(int i=0; i<this.stockTickerDataList.size();i++){
			DateTime current = stockTickerDataList.get(i).getDate();
			
			if(current.equals(date)){
				index = i;
				return index;
			}
		}
		
		if(index==-1){
			throw new StockExchangeIllegalStateException("Could not find it");
		}
		
		return index;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((stockTickerDataList == null) ? 0 : stockTickerDataList
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
			}
		if (obj == null){
			return false;
			}
		if (getClass() != obj.getClass()){
			return false;
			}
		StockTickerHistory other = (StockTickerHistory) obj;
		if (stockTickerDataList == null) {
			if (other.stockTickerDataList != null){
				return false;
			}
		} else if (!stockTickerDataList.equals(other.stockTickerDataList)){
			return false;
		}
		return true;
	}
}
