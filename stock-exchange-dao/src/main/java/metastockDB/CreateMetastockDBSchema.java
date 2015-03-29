package metastockDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import creator.CreateTableException;
import creator.TablesCreator;

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
    
    public void createMetastockDBIfNotExist(){
    	TablesCreator.createTableIfNotExist(connection, CREATE_METASTOCK_TABLE, "MetastockDB");
    	TablesCreator.createIndexIfNotExist(connection, CREATE_INDEX, "MetastockDB");
    }
}
