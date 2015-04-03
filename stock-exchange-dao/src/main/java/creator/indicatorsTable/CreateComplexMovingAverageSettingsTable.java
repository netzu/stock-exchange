package creator.indicatorsTable;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import creator.ExecuteSQLStatment;

public class CreateComplexMovingAverageSettingsTable {
	
	private static org.apache.log4j.Logger log = Logger.getLogger(CreateComplexMovingAverageSettingsTable.class);
	private Connection connection;
	
	private static final String COMPLEX_MOVING_AVERAGE_SETTINGS = 
			"CREATE TABLE IF NOT EXISTS COMPLEX_MOVING_AVERAGE_SETTINGS (settings_id int NOT NULL AUTO_INCREMENT, indicator_id int, period real, period_2 real, period_3 real);";
	
	private static final String CREATE_UNIQUE_INDEX = 
			"CREATE UNIQUE INDEX index ON COMPLEX_MOVING_AVERAGE_SETTINGS(indicator_id, period);";
	
	    public CreateComplexMovingAverageSettingsTable(final Connection connection) throws ClassNotFoundException, SQLException {
        this.connection = connection;
    }
    
    public void create(){
    	ExecuteSQLStatment.execute(connection, COMPLEX_MOVING_AVERAGE_SETTINGS);
    	ExecuteSQLStatment.execute(connection, CREATE_UNIQUE_INDEX);
    }
}
