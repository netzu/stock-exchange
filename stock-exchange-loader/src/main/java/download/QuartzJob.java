package download;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import dao.DBConnection;
import database.creator.CreateMetastockDBSchema;
import database.manipulator.MetastockDBUpdater;

public class QuartzJob implements Job {

	private static final Logger LOGGER = Logger.getLogger(QuartzJob.class);
	

	@Override
	public void execute(final JobExecutionContext context) throws JobExecutionException {
		
		Connection connection= null;
		
		try {			
			StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();
			connection = new DBConnection().getConnection(propertiesInstance);
			
			context.getMergedJobDataMap();
						
			CreateMetastockDBSchema metastockDBCreator = new CreateMetastockDBSchema(connection);
			
			MetastockDataDownloader downloader = new MetastockDataDownloader();
			MetastockDataUnziper decompresser = new MetastockDataUnziper();
			MetastockDBUpdater recentdata = new MetastockDBUpdater(propertiesInstance);
						
			metastockDBCreator.createMetastockDBIfNotExist();
			downloader.downloadData();
			decompresser.unZipMetastockData();
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
