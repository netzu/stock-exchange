package data.collector;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class StockTickerHistory {

	private List<EODTick> EODTickDataList = new ArrayList<EODTick>();

	public List<EODTick> getEODTickDataList() {
		return EODTickDataList;
	}

	public void setEODTickDataList(List<EODTick> EODTickDataList) {
		this.EODTickDataList = EODTickDataList;
	}

	public void add(EODTick dailyStock) {
		this.EODTickDataList.add(dailyStock);
	}
	
	public List <EODTick> subListOfCollection(int from, int to){

        if (from > to) {
            throw new StockExchangeIllegalStateException("Cannot create sublist when FROM is greather or equal TO");
        }

		return this.EODTickDataList.subList(from, to);
	}
	
	public EODTick findStockByDate(DateTime date){
	
		if(EODTickDataList.isEmpty()){
			throw new StockExchangeIllegalStateException("Cannot find stock by date if ticker's collection is empty");
		}
		
		if(date==null){
			throw new StockExchangeIllegalStateException("Cannot find ticker by date if date is null");
		}
		 
		for(int i=0; i<this.EODTickDataList.size();i++){
			DateTime current = EODTickDataList.get(i).getDate();
			
			if(current.equals(date)){
				return EODTickDataList.get(i);
			}
		}
		
		throw new StockExchangeIllegalStateException("Could not find a stock in given day");
	}
	
	public int findStockIndexByDate(DateTime date){
		
		int index = -1;
		
		if(EODTickDataList.isEmpty()){
			throw new StockExchangeIllegalStateException("Cannot find index for a ticker with given date for an empty ticker collection");
		}
		
		if(date==null){
			throw new StockExchangeIllegalStateException("Cannot find index for a ticker when date is null");
		}
		 
		for(int i=0; i<this.EODTickDataList.size();i++){
			DateTime current = EODTickDataList.get(i).getDate();
			
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
				+ ((EODTickDataList == null) ? 0 : EODTickDataList
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
		if (EODTickDataList == null) {
			if (other.EODTickDataList != null){
				return false;
			}
		} else if (!EODTickDataList.equals(other.EODTickDataList)){
			return false;
		}
		return true;
	}
}
