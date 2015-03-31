package creator.indicatorsTable;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import creator.ExecuteSQLStatment;

public class CreateSimpleMovingAvaregeSettingsTable {
	
	private static org.apache.log4j.Logger log = Logger.getLogger(CreateSimpleMovingAvaregeSettingsTable.class);
	private Connection connection;
	
	private static final String SIMPLE_MOVING_AVERAGE_SETTINGS = 
			"CREATE TABLE IF NOT EXISTS SIMPLE_MOVING_AVERAGE_SETTINGS (settings_id int NOT NULL AUTO_INCREMENT, indicator_id real, period real);";

	private static final String CREATE_UNIQUE_INDEX = 
			"CREATE UNIQUE INDEX index ON WILLIAMR_SETTINGS(indicator_id, period);";
	
	    public CreateSimpleMovingAvaregeSettingsTable(final Connection connection) throws ClassNotFoundException, SQLException {
        this.connection = connection;
    }
    
    public void create(){
    	ExecuteSQLStatment.execute(connection, SIMPLE_MOVING_AVERAGE_SETTINGS);
    	ExecuteSQLStatment.execute(connection, CREATE_UNIQUE_INDEX);
    }
}
