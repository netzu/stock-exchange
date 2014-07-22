package database.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/*
 * Creates schema of DB with data from stock market
 */
public class CreateMetastockDBSchema {
	
	private static final String CREATE_METASTOCK_TABLE = 
			"CREATE TABLE IF NOT EXISTS Daily_Stock_Info(ticker CHAR(40), date DATE, open real, high real, low real, close real, volumen real);";
	
	private static final String CREATE_INDEX = 
			"CREATE UNIQUE INDEX IF NOT EXISTS stock_id ON Daily_Stock_Info (ticker, date);";
			 	
	private Connection connection;

    private static org.apache.log4j.Logger log = Logger.getLogger(CreateMetastockDBSchema.class);

    public CreateMetastockDBSchema(final Connection connection) throws ClassNotFoundException, SQLException {
        this.connection = connection;
    }
    
    private void createTableIfNotExist(){
    	
		try {
			PreparedStatement statement = connection.prepareStatement(CREATE_METASTOCK_TABLE);
			statement.executeUpdate();
			statement.close();
			
			log.trace("Daily_Stock_Info TABLE has been created");
		} catch (SQLException e) {
			log.trace("Could not create MetastockDB");
			throw new IllegalStateException(e);
		}
    }
    
    private void createIndexIfNotExist(){

    	try {
    		PreparedStatement statement = connection.prepareStatement(CREATE_INDEX);
    		statement.executeUpdate();
    		statement.close();
			
			log.info("INDEX on Daily_Stock_Info TABLE has been created");
		} catch (SQLException e) {
			log.info("Could not create INDEX on Metastock DB");
			throw new IllegalStateException(e);
		}
    }    

    public void createMetastockDBIfNotExist(){
        	createTableIfNotExist();
        	createIndexIfNotExist();
    }
}
