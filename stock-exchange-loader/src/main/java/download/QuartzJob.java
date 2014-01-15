package download;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJob implements Job {

	private static final Logger LOGGER = Logger.getLogger(QuartzJob.class);
	
	@Override
	public void execute(final JobExecutionContext context) throws JobExecutionException {
		try {
			LOGGER.error("AAAAAAA");
			
//			MetastockDataDownloader downloader = (MetastockDataDownloader)context.getScheduler().getContext().get("downloader");
//			MetastockDataDecompressor decompresser = (MetastockDataDecompressor)context.getScheduler().getContext().get("decompresser");
			
			JobDataMap data = context.getMergedJobDataMap();

			
			MetastockDataDownloader downloader = new MetastockDataDownloader();
			MetastockDataDecompressor decompresser = new MetastockDataDecompressor();
			
			downloader.downloadData();
			decompresser.UnZipMetastockData();
			
		} catch (Exception e) {
			LOGGER.error("Error occured when refreshing data",e);
	    }
		

	}

}
