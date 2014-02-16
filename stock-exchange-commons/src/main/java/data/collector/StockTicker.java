package data.collector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class StockTicker {
	
	private String stockName;
	private DateTime date;
	private double open;
	private double close;
	private double high;
	private double low;
	private double volumen;	
	
	private DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");
	
	private static org.apache.log4j.Logger log = Logger.getLogger(StockTicker.class);
	
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
		
		setStockName(extractedStockDataFromOneDay[0]);
		setDate(DateTime.parse(extractedStockDataFromOneDay[1], dateFormater));
		setOpen(Double.parseDouble(extractedStockDataFromOneDay[2]));
		setHigh(Double.parseDouble(extractedStockDataFromOneDay[3]));
		setLow(Double.parseDouble(extractedStockDataFromOneDay[4]));
		setClose(Double.parseDouble(extractedStockDataFromOneDay[5]));
		setVolumen(Double.parseDouble(extractedStockDataFromOneDay[6]));
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
		log.info("Stock, Date, Opne, High, Low, Close, volumen");
		log.info(data);
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
