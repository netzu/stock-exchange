package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ExecuteSQLStatment {
	
	private static org.apache.log4j.Logger log = Logger.getLogger(ExecuteSQLStatment.class);
	
    public static void execute(Connection connection, final String statementToExecute){
    	
		try {
			PreparedStatement statement = connection.prepareStatement(statementToExecute);
			statement.executeUpdate();
			statement.close();
			
			log.trace("statment: " + statementToExecute + " has been executed properly");
		} catch (SQLException e) {
			log.trace("Could not execute statment: " + statementToExecute );
			throw new ExecuteSQLStatmentException("could not execute statment");
		}
    }

}
