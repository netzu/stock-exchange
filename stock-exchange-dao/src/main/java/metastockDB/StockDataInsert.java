package metastockDB;

import data.collector.EODTick;
import data.collector.StockTickerHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

public class StockDataInsert {
    private static final int VOLUMEN_VALUE = 7;
	private static final int CLOSE_VALUE = 6;
	private static final int LOWEST_VALUE = 5;
	private static final int HIGHEST_VALUE = 4;
	private static final int OPEN_VALUE = 3;
	private static final int DATE_VALUE = 2;
	private static final int TICKER_NAME_VALUE = 1;

	private static final String INSERT_STOCK_DATA =
            "INSERT INTO Daily_Stock_Info (ticker, date, open, high, low, close, volumen) VALUES (?, ?, ?, ?, ?, ?, ?);";

    private Connection connection;

    public StockDataInsert(final Connection connection) throws ClassNotFoundException, SQLException {
        this.connection = connection;
    }

    public void insertStockTickerDataCollectionWithoutDuplicates(StockTickerHistory stockTickerCollection) throws SQLException, ClassNotFoundException, ParseException {

        int collectionSize = stockTickerCollection.getEODTickDataList().size();

        for (int iterator = 0; iterator < collectionSize; iterator++) {
            EODTick forStockDataFromOneDay = stockTickerCollection.getEODTickDataList().get(iterator);

            boolean notDuplicate = checkIfNotDuplicateInformation(forStockDataFromOneDay);

            if (notDuplicate == true) {
                PreparedStatement insertDataStatement = prepareStatment(forStockDataFromOneDay);
                try {
                executeStatment(insertDataStatement);
                } finally {
                	if (insertDataStatement != null) {
                		insertDataStatement.close();
                	}
                }
            } else{
                continue;           	
            }
        }
    }

    private boolean checkIfNotDuplicateInformation(EODTick stockDataFromOneDay) throws ClassNotFoundException, SQLException, ParseException {
        StockDataSelect stock = new StockDataSelect(connection);
        EODTick dataForStocktickerFromOneDay = stock.getDataForStocktickerFromOneDay(stockDataFromOneDay.getStockName(), stockDataFromOneDay.getDate());

        return null == dataForStocktickerFromOneDay;

    }

    private PreparedStatement prepareStatment(EODTick stockDataFromOneDay) {
        
    	PreparedStatement statement = null;
    	
    	try{
    	   	statement = connection.prepareStatement(INSERT_STOCK_DATA);
	
	        statement.setString(TICKER_NAME_VALUE, stockDataFromOneDay.getStockName());
	        statement.setDate(DATE_VALUE, new java.sql.Date(stockDataFromOneDay.getDate().toDate().getTime()));
	        statement.setDouble(OPEN_VALUE, stockDataFromOneDay.getOpen());
	        statement.setDouble(HIGHEST_VALUE, stockDataFromOneDay.getHigh());
	        statement.setDouble(LOWEST_VALUE, stockDataFromOneDay.getLow());
	        statement.setDouble(CLOSE_VALUE, stockDataFromOneDay.getClose());
	        statement.setDouble(VOLUMEN_VALUE, stockDataFromOneDay.getVolumen());
	
	        return statement;
    	}catch(SQLException ex){
    		throw new IllegalStateException(ex);
    	}
    }

    private void executeStatment(PreparedStatement statement) throws SQLException {
        statement.executeUpdate();
        
    }
}


