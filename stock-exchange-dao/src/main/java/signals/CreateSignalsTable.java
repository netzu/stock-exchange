package signals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.h2.engine.Database;

import connection.ExecuteSQLStatment;

public class CreateSignalsTable {

		private static final String SIGNALS = 
				"CREATE TABLE IF NOT EXISTS SIGNALS (id int NOT NULL AUTO_INCREMENT, settings_id real, ticker_id real, buy_sell BOOLEAN , timestamp TIMESTAMP);";
		
		private Connection connection;
		
	    private static org.apache.log4j.Logger log = Logger.getLogger(CreateSignalsTable.class);

	    public CreateSignalsTable(final Connection connection) throws ClassNotFoundException, SQLException {
	        this.connection = connection;
	    }

	    public void create(){
	    	ExecuteSQLStatment.execute(connection, SIGNALS);
	    }
}