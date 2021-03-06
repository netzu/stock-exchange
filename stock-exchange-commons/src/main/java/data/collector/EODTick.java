package data.collector;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class EODTick {
	
	private static final double DEFAULT_EPSILON = 0.001;
	private String stockName;
	private DateTime date;
	private double open;
	private double close;
	private double high;
	private double low;
	private double volumen;	
	
	private DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");
		
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
	public boolean equals(Object obj) {//NOSONAR
		if (this == obj)	//NOSONAR
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EODTick other = (EODTick) obj;
		if (!areEqual(close, other.close, DEFAULT_EPSILON))
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
		if (!areEqual(high, other.high, DEFAULT_EPSILON))
			return false;
		if (!areEqual(low, other.low, DEFAULT_EPSILON))
			return false;
		if (!areEqual(open, other.open, DEFAULT_EPSILON))
			return false;
		if (stockName == null) {
			if (other.stockName != null)
				return false;
		} else if (!stockName.equals(other.stockName))
			return false;
		if (!areEqual(volumen, other.volumen, DEFAULT_EPSILON))
			return false;
		return true;
	}

	private boolean areEqual(final double x1, final double x2, final double epsilon) {
		return (Math.abs(x1 - x2) )<= epsilon;
	}
	
	public static EODTick copy(final EODTick source) {
		EODTick copy = new EODTick();
		
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