package buy.signals.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class CreatePriceAnalyzeDB {

	private static final String CREATE_PRICE_ANALYZE_TABLE = 
			"CREATE TABLE IF NOT EXISTS PriceAnalyze(indicator CHAR(100), period real);";

	private static final String CREATE_PRICE_ANALYZE_PERIOD_STATS = 
			"CREATE TABLE IF NOT EXISTS PriceAnalyzerPeriodStats(period real, correctness real)";
	
	private static final String CREATE_PRICE_ANALAZE_DAY_STATS = 
			"CREATE TABLE IF NOT EXIST PriceAnalyzerDailyStats (day real, shareInAllProfits, shareInAllSignals)";
	
	private Connection connection;

    private static org.apache.log4j.Logger log = Logger.getLogger(CreatePriceAnalyzeDB.class);

    public CreatePriceAnalyzeDB(final Connection connection) throws ClassNotFoundException, SQLException {
        this.connection = connection;
    }
    
    private void createTableIfNotExist(){
    	
		try {
			PreparedStatement statement = connection.prepareStatement(CREATE_PRICE_ANALYZE_TABLE);
			statement.executeUpdate();
			statement.close();			
			log.trace("PriceAnalyze TABLE has been created");
			
			statement = connection.prepareStatement(CREATE_PRICE_ANALYZE_PERIOD_STATS);
			statement.executeUpdate();
			statement.close();			
			log.trace("PriceAnalyzerPeriodStats TABLE has been created");
			
			statement = connection.prepareStatement(CREATE_PRICE_ANALAZE_DAY_STATS);
			statement.executeUpdate();
			statement.close();			
			log.trace("PriceAnalyzerPeriodStats TABLE has been created");
			
		} catch (SQLException e) {
			log.trace("Could not create one of the tables");
		}
    }

    public void createMetastockDBIfNotExist(){
        	createTableIfNotExist();
    }

}
