package run;

import java.sql.Connection;
import java.sql.SQLException;

import metastockDB.CreateMetastockDBSchema;
import metastockDB.MetastockDBUpdater;

import org.apache.log4j.Logger;

import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import creator.DBConnection;
import download.MetastockDataUnziper;
import download.MetastockDataDownloader;

public class MetastockRunOnce {
	
	private static org.apache.log4j.Logger  LOGGER = Logger.getLogger(MetastockRunOnce.class);
	
	public static void main(final String[] args){
    Connection connection= null;
		
		try {			
			StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
			connection = new DBConnection().getConnection(propertiesInstance);
									
			CreateMetastockDBSchema metastockDBCreator = new CreateMetastockDBSchema(connection);
			
			MetastockDataDownloader downloader = new MetastockDataDownloader();
			MetastockDataUnziper decompresser = new MetastockDataUnziper();
			MetastockDBUpdater recentdata = new MetastockDBUpdater(propertiesInstance);
						
			metastockDBCreator.createMetastockDBIfNotExist();
			downloader.downloadData();
			decompresser.unZipMetastockData();
			recentdata.refresh(connection);
			
		} catch (Exception e) {
			LOGGER.error("Error occured when refreshing data",e);
			throw new IllegalStateException(e);
	    } finally {
	    	try {
	    		if(connection!=null){
	    			connection.close();
	    		}
			} catch (SQLException sqe) {
				throw new IllegalStateException(sqe);
			}
	    }
		

	}
}


