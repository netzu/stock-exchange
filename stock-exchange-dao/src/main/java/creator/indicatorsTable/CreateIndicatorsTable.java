package creator.indicatorsTable;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import connection.ExecuteSQLStatment;

public class CreateIndicatorsTable {
	
	private static org.apache.log4j.Logger log = Logger.getLogger(CreateIndicatorsTable.class);
	private Connection connection;
	
	private static final String INDICATORS = 
			"CREATE TABLE IF NOT EXISTS INDICATORS (indicator_id int, indicator_name CHAR(60));";
	
	    public CreateIndicatorsTable(final Connection connection) throws ClassNotFoundException, SQLException {
        this.connection = connection;
    }
    
    public void create(){
    	ExecuteSQLStatment.execute(connection, INDICATORS);
    }
}
