package creator.indicatorsTable;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import signals.CreareSignalsTable;
import creator.ExecuteSQLStatment;

public class CreateWilliamRSettingsTable {
	
	private static org.apache.log4j.Logger log = Logger.getLogger(CreateWilliamRSettingsTable.class);
	private Connection connection;
	
	private static final String WILLIAMR_SETTINGS = 
			"CREATE TABLE IF NOT EXISTS WILLIAMR_SETTINGS (settings_id int NOT NULL AUTO_INCREMENT, indicator_id real, period real);";
	
	private static final String CREATE_UNIQUE_INDEX = 
			"CREATE UNIQUE INDEX index ON WILLIAMR_SETTINGS(indicator_id, period);";
	
	    public CreateWilliamRSettingsTable(final Connection connection) throws ClassNotFoundException, SQLException {
        this.connection = connection;
    }
    
    public void create(){
    	ExecuteSQLStatment.execute(connection, WILLIAMR_SETTINGS);
    	ExecuteSQLStatment.execute(connection, CREATE_UNIQUE_INDEX);
    }
}
