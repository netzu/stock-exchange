package metastockDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import connection.ExecuteSQLStatment;
import connection.ExecuteSQLStatmentException;

/*
 * Creates schema of DB with data from stock market
 */
public class CreateMetastockDBSchema {
	
	private static org.apache.log4j.Logger LOG = Logger.getLogger(CreateMetastockDBSchema.class);
	
	private static final String CREATE_METASTOCK_TABLE = 
			"CREATE TABLE IF NOT EXISTS Daily_Stock_Info(ticker CHAR(40), date DATE, open real, high real, low real, close real, volumen real);";
	
	private static final String CREATE_INDEX = 
			"CREATE UNIQUE INDEX IF NOT EXISTS stock_id ON Daily_Stock_Info (ticker, date);";
			 	
	private Connection connection;

    public CreateMetastockDBSchema(final Connection connection) throws ClassNotFoundException, SQLException {
        this.connection = connection;
    }
    
    public void createMetastockDBIfNotExist(){
    	ExecuteSQLStatment.execute(connection, CREATE_METASTOCK_TABLE);
    	ExecuteSQLStatment.execute(connection, CREATE_INDEX);
    }
}
