package creator.signlasTables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.h2.engine.Database;

import creator.TablesCreator;

public class CreareWilliamsRSignalsTable {

		private static final String WILLIAMS_R_SIGNALS = 
				"CREATE TABLE IF NOT EXISTS WILLIAMS_R_SIGNALS id real, settings_id real, ticker_id real, bolean buy_sell, date DATE);";

		private static final String CREATE_SEQUENCE = 
				"CREATE SEQUENCE WILLIAMS_R_SIGNALS.id MINVALUE 1 START WITH 1 INCREMENT BY 1 CYCLE NOCACHE;";
		
		private Connection connection;

	    private static org.apache.log4j.Logger log = Logger.getLogger(CreareWilliamsRSignalsTable.class);

	    public CreareWilliamsRSignalsTable(final Connection connection) throws ClassNotFoundException, SQLException {
	        this.connection = connection;
	    }

	    public void create(){
	    	TablesCreator.createTableIfNotExist(connection, WILLIAMS_R_SIGNALS, "WILLIAMS_R_SIGNALS");
	    	TablesCreator.createSequence(connection, CREATE_SEQUENCE, "id");
	    }

}
