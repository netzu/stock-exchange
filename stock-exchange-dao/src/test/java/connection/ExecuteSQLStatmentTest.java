package connection;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import configuration.StockExchangeProperties;
import configuration.TestApplicationContext;

public class ExecuteSQLStatmentTest {

    static StockExchangeProperties testPropertiesInstance;
            
            
    @BeforeClass
    public static void setup() {
        testPropertiesInstance = TestApplicationContext.getTestPropertiesInstance("settings/StockExchange.properties");
    }
            

    @Test
    public void executedProperly() {

        Connection connection = null;
        final String STATEMENT_TO_EXECUTE = "show tables;";

		try {


            Class.forName(testPropertiesInstance.getDBDriver());
            connection =
                DriverManager.getConnection(testPropertiesInstance.getDBUrlPath() + "/TestDatabase",
                    testPropertiesInstance.getDBUser(), testPropertiesInstance.getDBPassword());
            ExecuteSQLStatment.execute(connection, STATEMENT_TO_EXECUTE);

            assertTrue(connection.isValid(0));
            assertTrue(connection.getMetaData().getURL().equals(testPropertiesInstance.getDBUrlPath() + "/TestDatabase"));
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Got exception when not expected");
        } finally {
            try {
				if (null != connection) {
					connection.close();
				}
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Test
    public void executedWithException() {

        Connection connection = null;
        final String STATEMENT_TO_EXECUTE = "show tables;";



        try {
            Class.forName(testPropertiesInstance.getDBDriver());
            connection = DriverManager.getConnection(testPropertiesInstance.getDBUrlPath() + "/TestDatabase", "sa", "");

            connection.close();
            ExecuteSQLStatment.execute(connection, STATEMENT_TO_EXECUTE);

            assertFalse(connection.isValid(0));
        } catch (Exception ex) {
            assertTrue(ex.getMessage().equals("could not execute statment"));
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
