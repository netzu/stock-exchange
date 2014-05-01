package run;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;

import DAO.DBConnection;
import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import database.creator.CreateMetastockDBSchema;
import database.manipulator.MetastockDBUpdater;
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
						
			metastockDBCreator.CreateIfNotExist();
			downloader.downloadData();
			decompresser.UnZipMetastockData();
			recentdata.refresh();
			
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


