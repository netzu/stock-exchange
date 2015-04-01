package creator;

import java.sql.Connection;
import java.sql.DriverManager;
import configuration.StockExchangeProperties;
import org.apache.log4j.Logger;

public class MetastockDBConnection {
	private static final Logger LOGGER = Logger.getLogger(MetastockDBConnection.class);

	public Connection getConnection(StockExchangeProperties properites) {		
		try {  
            Class.forName(properites.getDBDriver());  
            Connection connection = DriverManager.getConnection(properites.getDBUrl(), properites.getDBUser(), properites.getDBPassword());  
            LOGGER.info("Connecting to MetastockDB");
            return connection;
            } 
	    catch (Exception e) {  
            LOGGER.error("Cannot create connection to MetastockDB :(", e);
            throw new IllegalStateException(e);
	    }
	}
}
