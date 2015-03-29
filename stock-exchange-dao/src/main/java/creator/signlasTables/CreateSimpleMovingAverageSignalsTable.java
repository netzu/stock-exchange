package creator.signlasTables;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import creator.TablesCreator;

public class CreateSimpleMovingAverageSignalsTable {

		private static final String SIMPLE_MOVING_AVERAGE_SIGNALS = 
				"CREATE TABLE IF NOT EXISTS SIMPLE_MOVING_AVERAGE_SIGNALS id real, settings_id real, ticker_id real, bolean buy_sell, date DATE);";

		private static final String CREATE_SEQUENCE = 
				"CREATE SEQUENCE SIMPLE_MOVING_AVERAGE_SIGNALS.id MINVALUE 1 START WITH 1 INCREMENT BY 1 CYCLE NOCACHE;";
		
		private Connection connection;

	    private static org.apache.log4j.Logger log = Logger.getLogger(CreareWilliamsRSignalsTable.class);

	    public CreateSimpleMovingAverageSignalsTable(final Connection connection) throws ClassNotFoundException, SQLException {
	        this.connection = connection;
	    }

	    public void create(){
	    	TablesCreator.createTableIfNotExist(connection, SIMPLE_MOVING_AVERAGE_SIGNALS, "SIMPLE_MOVING_AVERAGE_SIGNALS");
	    	TablesCreator.createSequence(connection, CREATE_SEQUENCE, "id");

	    }
    }
