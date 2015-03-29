package creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class TablesCreator {
	
	private static org.apache.log4j.Logger log = Logger.getLogger(TablesCreator.class);
	
    public static void createTableIfNotExist(Connection connection, final String statementCreateDB, String tableName){
    	
		try {
			PreparedStatement statement = connection.prepareStatement(statementCreateDB);
			statement.executeUpdate();
			statement.close();
			
			log.trace(tableName + " TABLE has been created");
		} catch (SQLException e) {
			log.trace("Could not create " + tableName );
			throw new CreateTableException("Could not create " + tableName);
		}
    }
    
    public static void createIndexIfNotExist(Connection connection, final String CREATE_INDEX, String tableName){

    	try {
    		PreparedStatement statement = connection.prepareStatement(CREATE_INDEX);
    		statement.executeUpdate();
    		statement.close();
			
			log.info("INDEX on " + tableName + " TABLE has been created");
		} catch (SQLException e) {
			log.info("Could not create INDEX on " + tableName);
			throw new CreateTableException("Could not create INDEX on " + tableName);
		}
    }    
    
    public static void createSequence(Connection connection, final String CREATE_SEQUENCE, String column){

    	try {
    		PreparedStatement statement = connection.prepareStatement(CREATE_SEQUENCE);
    		statement.executeUpdate();
    		statement.close();
			
			log.info("Sequence on " + column + " has been created");
		} catch (SQLException e) {
			log.info("Could not create sequence on " + column);
			throw new CreateTableException("Could not create sequence on " + column);
		}
    } 

}
