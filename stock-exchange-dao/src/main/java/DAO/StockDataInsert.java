package DAO;

import data.collector.StockTicker;
import data.collector.StockTickerHistory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

public class StockDataInsert {
    private static final String INSERT_STOCK_DATA =
            "INSERT INTO Daily_Stock_Info (ticker, date, open, high, low, close, volumen) VALUES (?, ?, ?, ?, ?, ?, ?);";

    private Connection connection;

    private static org.apache.log4j.Logger log = Logger.getLogger(StockDataInsert.class);

    public StockDataInsert(final Connection connection) throws ClassNotFoundException, SQLException {
        this.connection = connection;
    }

    public void insertStockTickerDataCollectionWithoutDuplicates(StockTickerHistory stockTickerCollection) throws SQLException, ClassNotFoundException, ParseException {

        int collectionSize = stockTickerCollection.getStockTickerDataList().size();

        for (int iterator = 0; iterator < collectionSize; iterator++) {
            StockTicker forStockDataFromOneDay = stockTickerCollection.getStockTickerDataList().get(iterator);


            if (checkIfNotDuplicateInformation(forStockDataFromOneDay) == true) {
                PreparedStatement insertDataStatement = prepareStatment(forStockDataFromOneDay);
                executeStatment(insertDataStatement);
            } else
                continue;
        }
    }

    public void CloseConnection() throws SQLException {
        connection.close();
    }

    private boolean checkIfNotDuplicateInformation(StockTicker stockDataFromOneDay) throws ClassNotFoundException, SQLException, ParseException {
        StockDataSelect stock = new StockDataSelect(connection);
        StockTicker dataForStocktickerFromOneDay = stock.getDataForStocktickerFromOneDay(stockDataFromOneDay.getStockName(), stockDataFromOneDay.getDate());

        return null == dataForStocktickerFromOneDay;

    }

    private PreparedStatement prepareStatment(StockTicker stockDataFromOneDay) throws SQLException {
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

    private void executeStatment(PreparedStatement statement) throws SQLException {
        statement.executeUpdate();
    }
}


