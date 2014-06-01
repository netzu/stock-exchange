package data.collector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class StockTicker {
	
	private static final int VOLUMEN_INDEX = 6;
	private static final int CLOSE_VALUE_INDEX = 5;
	private static final int LOWEST_VALUE_INDEX = 4;
	private static final int HIGHEST_VALUE_INDEX = 3;
	private static final int OPEN_VALUE_INDEX = 2;
	private static final int DATE_INDEX = 1;
	private static final int STOCK_NAME_INDEX = 0;
	
	private String stockName;
	private DateTime date;
	private double open;
	private double close;
	private double high;
	private double low;
	private double volumen;	
	
	private DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");
	
	private static org.apache.log4j.Logger LOGGER = Logger.getLogger(StockTicker.class);
	
	public String getStockName() {
		return stockName;
	}
	
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	
	public DateTime getDate() {
		return date;
	}
	
	public void setDate(DateTime date) {
		this.date = date;
	}
	
	public double getOpen() {
		return open;
	}
	
	public void setOpen(double open) {
		this.open = open;
	}
	
	public double getClose() {
		return close;
	}
	
	public void setClose(double close) {
		this.close = close;
	}
	
	public double getHigh() {
		return high;
	}
	
	public void setHigh(double high) {
		this.high = high;
	}
	
	public double getLow() {
		return low;
	}
	
	public void setLow(double low) {
		this.low = low;
	}
	
	public double getVolumen() {
		return volumen;
	}
	
	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}
	
	public void extractStockdata(String[] extractedStockDataFromOneDay) throws ParseException {	
		
		setStockName(extractedStockDataFromOneDay[STOCK_NAME_INDEX]);
		setDate(DateTime.parse(extractedStockDataFromOneDay[DATE_INDEX], dateFormater));
		setOpen(Double.parseDouble(extractedStockDataFromOneDay[OPEN_VALUE_INDEX]));
		setHigh(Double.parseDouble(extractedStockDataFromOneDay[HIGHEST_VALUE_INDEX]));
		setLow(Double.parseDouble(extractedStockDataFromOneDay[LOWEST_VALUE_INDEX]));
		setClose(Double.parseDouble(extractedStockDataFromOneDay[CLOSE_VALUE_INDEX]));
		setVolumen(Double.parseDouble(extractedStockDataFromOneDay[VOLUMEN_INDEX]));
	}
	
	public void extractStockdata(ResultSet resultOfQuery) throws ParseException, SQLException {	
		
		setStockName(resultOfQuery.getString("ticker"));
		
		DateTime dt = new DateTime(resultOfQuery.getDate("date"));		
		setDate(dt);
		
		setOpen(resultOfQuery.getDouble("open"));
		setHigh(resultOfQuery.getDouble("high"));
		setLow(resultOfQuery.getDouble("low"));
		setClose(resultOfQuery.getDouble("close"));
		setVolumen(resultOfQuery.getDouble("volumen"));
	}
	
	public void printStockTickerData(){		
		String data = new String(getStockName()+ ", " + getDate() + ", "+ getOpen()+", "+getHigh()+", "+getLow()+", "+getClose()+", "+getVolumen());
		LOGGER.info("Stock, Date, Opne, High, Low, Close, volumen");
		LOGGER.info(data);
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(close);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((dateFormater == null) ? 0 : dateFormater.hashCode());
		temp = Double.doubleToLongBits(high);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(low);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(open);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((stockName == null) ? 0 : stockName.hashCode());
		temp = Double.doubleToLongBits(volumen);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockTicker other = (StockTicker) obj;
		if (Double.doubleToLongBits(close) != Double
				.doubleToLongBits(other.close))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (dateFormater == null) {
			if (other.dateFormater != null)
				return false;
		} else if (!dateFormater.equals(other.dateFormater))
			return false;
		if (Double.doubleToLongBits(high) != Double
				.doubleToLongBits(other.high))
			return false;
		if (Double.doubleToLongBits(low) != Double.doubleToLongBits(other.low))
			return false;
		if (Double.doubleToLongBits(open) != Double
				.doubleToLongBits(other.open))
			return false;
		if (stockName == null) {
			if (other.stockName != null)
				return false;
		} else if (!stockName.equals(other.stockName))
			return false;
		if (Double.doubleToLongBits(volumen) != Double
				.doubleToLongBits(other.volumen))
			return false;
		return true;
	}

	public static StockTicker copy(final StockTicker source) {
		StockTicker copy = new StockTicker();
		
		copy.setClose(source.getClose());
		copy.setDate(new DateTime(source.getDate()));
		copy.setHigh(source.getHigh());
		copy.setLow(source.getLow());
		copy.setOpen(source.getOpen());
		copy.setStockName(new String(source.getStockName()));
		copy.setVolumen(source.getVolumen());
		
		return copy;
	}
}