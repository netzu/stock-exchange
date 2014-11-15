package data.collector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class StockDataExtractor {
	private static final int VOLUMEN_INDEX = 6;
	private static final int CLOSE_VALUE_INDEX = 5;
	private static final int LOWEST_VALUE_INDEX = 4;
	private static final int HIGHEST_VALUE_INDEX = 3;
	private static final int OPEN_VALUE_INDEX = 2;
	private static final int DATE_INDEX = 1;
	private static final int STOCK_NAME_INDEX = 0;
	
	private DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");

	public EODTick extractFromString(String[] extractedStockDataFromOneDay) throws ParseException {
		
		EODTick exctractedData = new EODTick();
		
		exctractedData.setStockName(extractedStockDataFromOneDay[STOCK_NAME_INDEX]);
		exctractedData.setDate(DateTime.parse(extractedStockDataFromOneDay[DATE_INDEX], dateFormater));
		exctractedData.setOpen(Double.parseDouble(extractedStockDataFromOneDay[OPEN_VALUE_INDEX]));
		exctractedData.setHigh(Double.parseDouble(extractedStockDataFromOneDay[HIGHEST_VALUE_INDEX]));
		exctractedData.setLow(Double.parseDouble(extractedStockDataFromOneDay[LOWEST_VALUE_INDEX]));
		exctractedData.setClose(Double.parseDouble(extractedStockDataFromOneDay[CLOSE_VALUE_INDEX]));
		exctractedData.setVolumen(Double.parseDouble(extractedStockDataFromOneDay[VOLUMEN_INDEX]));
		
		return exctractedData;
	}
	
	public EODTick extractFromQueryResults(ResultSet resultOfQuery) throws ParseException, SQLException {
		
		EODTick exctractedData = new EODTick();
		
		exctractedData.setStockName(resultOfQuery.getString("ticker"));;		
		exctractedData.setDate(new DateTime(resultOfQuery.getDate("date")));
		exctractedData.setOpen(resultOfQuery.getDouble("open"));
		exctractedData.setHigh(resultOfQuery.getDouble("high"));
		exctractedData.setLow(resultOfQuery.getDouble("low"));
		exctractedData.setClose(resultOfQuery.getDouble("close"));
		exctractedData.setVolumen(resultOfQuery.getDouble("volumen"));
		
		return exctractedData;
	}
}