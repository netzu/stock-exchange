package run;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import download.QuartzJob;


public class MetastockDemon {
	private static org.apache.log4j.Logger log = Logger.getLogger(MetastockDemon.class);
	
	public static void main( String[] args ) throws SchedulerException
    { 		
		log.info("Starting demon...");
		
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = sf.getScheduler();

		JobDetail jd = newJob(QuartzJob.class).withIdentity("name", "group").build();
		
		CronTrigger t = newTrigger().withIdentity("name", "group")
			    .withSchedule(cronSchedule("0 0/10 * * * ?"))
                .startNow()
			    .build();

		scheduler.scheduleJob(jd, t);
        scheduler.start();
    }
}
