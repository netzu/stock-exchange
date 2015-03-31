package creator.indicatorsTable;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import creator.ExecuteSQLStatment;

public class CreateComplexMovingAverageTable {
	
	private static org.apache.log4j.Logger log = Logger.getLogger(CreateComplexMovingAverageTable.class);
	private Connection connection;
	
	private static final String COMPLEX_MOVING_AVERAGE_SETTINGS = 
			"CREATE TABLE IF NOT EXISTS COMPLEX_MOVING_AVERAGE_SETTINGS (settings_id int NOT NULL AUTO_INCREMENT, indicator_id real, period real, period_2 real, period_3 real);";
	
	private static final String CREATE_UNIQUE_INDEX = 
			"CREATE UNIQUE INDEX index ON COMPLEX_MOVING_AVERAGE_SETTINGS(indicator_id, period);";
	
	    public CreateComplexMovingAverageTable(final Connection connection) throws ClassNotFoundException, SQLException {
        this.connection = connection;
    }
    
    public void create(){
    	ExecuteSQLStatment.execute(connection, COMPLEX_MOVING_AVERAGE_SETTINGS);
    	ExecuteSQLStatment.execute(connection, CREATE_UNIQUE_INDEX);
    }
}
