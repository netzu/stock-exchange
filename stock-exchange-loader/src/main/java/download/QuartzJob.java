package download;

import java.nio.channels.IllegalSelectorException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import database.creator.CreateMetastockDBSchema;
import database.manipulator.MetastockDBUpdater;
import DAO.DBConnection;
import DAO.StockDataInsert;

public class QuartzJob implements Job {

	private static final Logger LOGGER = Logger.getLogger(QuartzJob.class);
	

	@Override
	public void execute(final JobExecutionContext context) throws JobExecutionException {
		
		Connection connection= null;
		
		try {			
			StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
			connection = new DBConnection().getConnection(propertiesInstance);
			
			JobDataMap data = context.getMergedJobDataMap();
						
			CreateMetastockDBSchema metastockDBCreator = new CreateMetastockDBSchema(connection);
			
			MetastockDataDownloader downloader = new MetastockDataDownloader();
			MetastockDataDecompressor decompresser = new MetastockDataDecompressor();
			MetastockDBUpdater recentdata = new MetastockDBUpdater();
						
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
