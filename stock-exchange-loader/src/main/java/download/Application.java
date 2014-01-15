package download;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;


public class Application {
	private static org.apache.log4j.Logger log = Logger.getLogger(MetastockDataDecompressor.class);
	
	public static void main( String[] args ) throws SchedulerException
    { 		
		log.info("I'm starting to work...");

		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = sf.getScheduler();

		JobDetail jd = newJob(QuartzJob.class).withIdentity("name", "group").build();
		
		log.info("1");
		
		CronTrigger t = newTrigger().withIdentity("name", "group")
			    .withSchedule(cronSchedule("0 5 21 ? * MON-FRI"))
                .startNow()
			    .build();

//		Trigger t = newTrigger().withIdentity("triger").withSchedule(
//				cronSchedule("0 1 21 * * ?")).forJob(jd).startNow().build();
	
//		MetastockDataDecompressor decompresser = new MetastockDataDecompressor();
//		MetastockDataDownloader downloader = new MetastockDataDownloader();
//		scheduler.getContext().put("downloader", downloader);
//		scheduler.getContext().put("decompresser", decompresser);

		scheduler.scheduleJob(jd, t);
        scheduler.start();
		log.info("2");

    }
}
