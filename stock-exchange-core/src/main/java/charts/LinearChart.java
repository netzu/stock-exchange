package charts;

import java.util.ArrayList;

import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;

import data.collector.StockTicker;
import data.collector.StockTickerCollection;

public class LinearChart {
	
	
	public TimeSeriesCollection linearChartDailyData(final StockTickerCollection tickerCollection){
		ArrayList<StockTicker> stockTickerDataList = tickerCollection.getStockTickerDataList();

		if (stockTickerDataList.isEmpty()) {
			return null;
		}
		
		TimeSeriesCollection dataset = new TimeSeriesCollection();		
		TimeSeries oneTicker = new TimeSeries(stockTickerDataList.get(0).getStockName());
		
		for(StockTicker stockTicker : stockTickerDataList) {
			TimeSeriesDataItem item = new TimeSeriesDataItem(new Day(stockTicker.getDate().toDate()), stockTicker.getClose());
			oneTicker.add(item);			
		}

		dataset.addSeries(oneTicker);
		
		return dataset;
	}
	
	public TimeSeriesCollection linearChartWeeklyData(final StockTickerCollection tickerCollection){
		ArrayList<StockTicker> stockTickerDataList = tickerCollection.getStockTickerDataList();
		
		if (stockTickerDataList.isEmpty()) {
			return null;
		}
			
		TimeSeriesCollection dataset = new TimeSeriesCollection();		
		TimeSeries oneTicker = new TimeSeries(stockTickerDataList.get(0).getStockName());
		
		for(StockTicker stockTicker : stockTickerDataList) {
			TimeSeriesDataItem item = new TimeSeriesDataItem(new Day(stockTicker.getDate().toDate()), stockTicker.getClose());
			oneTicker.add(item);			
		}

		dataset.addSeries(oneTicker);
		
		return dataset;
	}
	
	
	public TimeSeriesCollection linearChartMonthlyData(final StockTickerCollection tickerCollection){
		return null;
	}
	
}
