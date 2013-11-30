package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import configuration.ApplicationContext;
import data.collector.Main;
import data.collector.StockTicker;
import data.collector.StockTickerCollection;

public class UpdateDBWithFreshnestData {
	private static final String INSERT_STOCK_DATA = 
			"INSERT INTO Daily_Stock_Info (ticker, date, open, high, low, close, volumen) VALUES (?, ?, ?, ?, ?, ?, ?);";
	
    private Connection connection;
    
	private static org.apache.log4j.Logger log = Logger.getLogger(UpdateDBWithFreshnestData.class);
    
    public UpdateDBWithFreshnestData(final Connection connection) throws ClassNotFoundException, SQLException {    	
    	this.connection = connection;
    }
    
    public void CloseConnection() throws SQLException{
    	connection.close();
    }
    
    private boolean checkIfNotDuplicateInformation(StockTicker stockDataFromOneDay) throws ClassNotFoundException, SQLException, ParseException{    	
    	GetDataFromDB stock = new GetDataFromDB(connection);			
		StockTicker dataForStocktickerFromOneDay = stock.getDataForStocktickerFromOneDay(stockDataFromOneDay.getStockName(), stockDataFromOneDay.getDate());
		
		return null == dataForStocktickerFromOneDay;

    }
    
    private PreparedStatement prepareStatment(StockTicker stockDataFromOneDay) throws SQLException{
		PreparedStatement statement = connection.prepareStatement(INSERT_STOCK_DATA);
		
		statement.setString(1, stockDataFromOneDay.getStockName());
		statement.setDate(2, new java.sql.Date(stockDataFromOneDay.getDate().toDate().getTime()));
		statement.setDouble(3, stockDataFromOneDay.getOpen());
		statement.setDouble(4, stockDataFromOneDay.getHigh());
		statement.setDouble(5, stockDataFromOneDay.getLow());
		statement.setDouble(6, stockDataFromOneDay.getClose());
		statement.setDouble(7, stockDataFromOneDay.getVolumen());
		
		return statement;
    }
    
    private void executeStatment(PreparedStatement statement) throws SQLException{
    	statement.executeUpdate(); 
    }
      
	public void insertStockTickerDataCollectionWithoutDuplicates(StockTickerCollection stockTickerCollection) throws SQLException, ClassNotFoundException, ParseException{
		
		int collectionSize = stockTickerCollection.getStockTickerDataList().size();
		
		for(int iterator = 0; iterator<collectionSize; iterator++){			
			StockTicker forStockDataFromOneDay = stockTickerCollection.getStockTickerDataList().get(iterator);
			
			
			
			if(checkIfNotDuplicateInformation(forStockDataFromOneDay)==true){
				log.info("Dodaje do bazy: " + 
						forStockDataFromOneDay.getStockName() + ", " + 
						forStockDataFromOneDay.getDate() + ", " + 
						forStockDataFromOneDay. getVolumen());
				PreparedStatement insertDataStatement = prepareStatment(forStockDataFromOneDay);
				executeStatment(insertDataStatement);
			}
			else
				continue;
			}	
		}
}


