package charts;

import java.util.ArrayList;
import java.util.List;

import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;

import data.collector.StockTicker;
import data.collector.StockTickerHistory;

public class LinearChart {
	
	
	public TimeSeriesCollection linearChartDailyData(final StockTickerHistory tickerCollection){
		List<StockTicker> stockTickerDataList = tickerCollection.getStockTickerDataList();

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
	
	public TimeSeriesCollection linearChartWeeklyData(final StockTickerHistory tickerCollection){
		List<StockTicker> stockTickerDataList = tickerCollection.getStockTickerDataList();
		
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
	
	
	public TimeSeriesCollection linearChartMonthlyData(final StockTickerHistory tickerCollection){
		return null;
	}
	
}
