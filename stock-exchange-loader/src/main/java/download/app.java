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
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


public class app {
	private static org.apache.log4j.Logger log = Logger.getLogger(MetastockDataDecopresser.class);
	
	public static void main( String[] args ) throws SchedulerException
    { 		
		log.info("I'm starting to work...");

		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = sf.getScheduler();

		JobDetail jd = newJob(QuartzJob.class).withIdentity("name", "group").build();
		
		log.info("1");
		
		CronTrigger t = newTrigger().withIdentity("name", "group")
			    .withSchedule(cronSchedule("0/5 * * * * ?").inTimeZone(TimeZone.getDefault()))
			    .build();

//		Trigger t = newTrigger().withIdentity("triger").withSchedule(
//				cronSchedule("0 1 21 * * ?")).forJob(jd).startNow().build();
	
//		MetastockDataDecopresser decompresser = new MetastockDataDecopresser();
//		MetastockDataDownloader downloader = new MetastockDataDownloader();
//		scheduler.getContext().put("downloader", downloader);
//		scheduler.getContext().put("decompresser", decompresser);

		scheduler.scheduleJob(jd, t);
		log.info("2");

    }
}
