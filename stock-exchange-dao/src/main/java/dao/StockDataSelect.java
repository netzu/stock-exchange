package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import data.collector.StockTicker;
import data.collector.StockTickerHistory;

public class StockDataSelect {
	
	private static final String GET_ALL_TICKERS_NAMES = "SELECT DISTINCT(ticker) FROM Daily_Stock_Info;";
	private static final String SELECT_ALL_DATA_FOR_TICKER = "SELECT * FROM Daily_Stock_Info WHERE ticker = ? order by date asc";
	private static final String SELECT_DATA_FOR_TICKER_FROM_CERTAIN_DAY = "SELECT * FROM Daily_Stock_Info WHERE ticker = ? AND date = ?";
	
	private Connection connection;
	
	public StockDataSelect(final Connection connection) throws ClassNotFoundException, SQLException{
		this.connection = connection;
	}
	
	public List<String> getAllStockTickerNames() throws SQLException{
		List<String> results = new ArrayList<String>();
		
		PreparedStatement statement = connection.prepareStatement(GET_ALL_TICKERS_NAMES);
		ResultSet resultOfQuery = statement.executeQuery();
		
		while(resultOfQuery.next()){
			String oneTickerName = resultOfQuery.getString(1);
			results.add(oneTickerName);	
		}
		
		resultOfQuery.close();
		statement.close();
		return results;
	}
	
	public StockTickerHistory getAllDataForStockTicker(String stockTickerName) throws SQLException, ParseException{
		StockTickerHistory stockCollectionForTicker = new StockTickerHistory();
		
		
		PreparedStatement statement = connection.prepareStatement(SELECT_ALL_DATA_FOR_TICKER);
		statement.setString(1, stockTickerName);
		ResultSet resultOfQuery = statement.executeQuery(); 
		
		while(resultOfQuery.next()){
			StockTicker stockDataFromOneDay = new StockTicker();
			stockDataFromOneDay.extractStockdata(resultOfQuery);
			stockCollectionForTicker.add(stockDataFromOneDay);
		}
		
		resultOfQuery.close();
		statement.close();
		return stockCollectionForTicker;
	}
	
	public StockTicker getDataForStocktickerFromOneDay(String stockTickerName, DateTime date) throws SQLException, ParseException{
		StockTicker stockDataFromOneDay = new StockTicker();
		
		PreparedStatement statement = connection.prepareStatement(SELECT_DATA_FOR_TICKER_FROM_CERTAIN_DAY);
		statement.setString(1, stockTickerName);
		statement.setDate(2, new java.sql.Date(date.toDate().getTime()));
		
		ResultSet resultOfQuery = statement.executeQuery();
		
		if (!resultOfQuery.next()) {
			resultOfQuery.close();
			statement.close();
			return null;
		}
		
		stockDataFromOneDay.extractStockdata(resultOfQuery);
		
		resultOfQuery.close();
		statement.close();
		return stockDataFromOneDay;
	}
	
	public StockTicker getDataForStocktickerFromOneDay(String stockTickerName, String day) throws SQLException, ParseException{
		StockTicker stockDataFromOneDay = new StockTicker();
		
		DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");
		DateTime date = dateFormater.parseDateTime(day);
		
		PreparedStatement statement = connection.prepareStatement(SELECT_DATA_FOR_TICKER_FROM_CERTAIN_DAY);
		statement.setString(1, stockTickerName);
		statement.setDate(2, new java.sql.Date(date.toDate().getTime()));
		
		ResultSet resultOfQuery = statement.executeQuery();
		
		if (!resultOfQuery.next()) {
			resultOfQuery.close();
			statement.close();
			throw new IllegalStateException("No data when expected");
		}
		
		stockDataFromOneDay.extractStockdata(resultOfQuery);
		
		resultOfQuery.close();
		statement.close();
		return stockDataFromOneDay;
	}
}
