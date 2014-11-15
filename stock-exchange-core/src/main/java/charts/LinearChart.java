package charts;

import java.util.List;

import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;

import data.collector.EODTick;
import data.collector.StockTickerHistory;

public class LinearChart {
	
	
	public TimeSeriesCollection linearChartDailyData(final StockTickerHistory tickerCollection){
		List<EODTick> EODTickDataList = tickerCollection.getEODTickDataList();

		if (EODTickDataList.isEmpty()) {
			return null;
		}
		
		TimeSeriesCollection dataset = new TimeSeriesCollection();		
		TimeSeries oneTicker = new TimeSeries(EODTickDataList.get(0).getStockName());
		
		for(EODTick EODTick : EODTickDataList) {
			TimeSeriesDataItem item = new TimeSeriesDataItem(new Day(EODTick.getDate().toDate()), EODTick.getClose());
			oneTicker.add(item);			
		}

		dataset.addSeries(oneTicker);
		
		return dataset;
	}
	
	public TimeSeriesCollection linearChartWeeklyData(final StockTickerHistory tickerCollection){
		List<EODTick> EODTickDataList = tickerCollection.getEODTickDataList();
		
		if (EODTickDataList.isEmpty()) {
			return null;
		}
			
		TimeSeriesCollection dataset = new TimeSeriesCollection();		
		TimeSeries oneTicker = new TimeSeries(EODTickDataList.get(0).getStockName());
		
		for(EODTick EODTick : EODTickDataList) {
			TimeSeriesDataItem item = new TimeSeriesDataItem(new Day(EODTick.getDate().toDate()), EODTick.getClose());
			oneTicker.add(item);			
		}

		dataset.addSeries(oneTicker);
		
		return dataset;
	}
	
	
	public TimeSeriesCollection linearChartMonthlyData(final StockTickerHistory tickerCollection){
		return null;
	}
	
}
