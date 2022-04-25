package ex02_tomcat;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/*
	ServletContextListener 인터페이스
	
		1.	웹 애플리케이션의 Lifecicyle로 동작
		2.	시작할 때 contextInitialized() 메소드가 동작
		3.	동료될 때 contextDestroyed() 메소드가 동작
*/

@WebListener
public class myListener implements ServletContextListener {

	private Scheduler scheduler;

	/*
	 * Default constructor Sceduler scheduler 생성하기
	 */
	public myListener() {

		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	contextDestroyed
//	contextDestoryed() 웹 애플리케이션이 종료될 때 Scheduler 인스턴스 종료
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			if (scheduler != null) {
				scheduler.shutdown();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	contextIntialized()
//	웹 애플리케이션이 시작할 때 Scheduler 인스턴스 시작
	public void contextInitialized(ServletContextEvent sce) {
		try {
			JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("job2", "group2").build();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group2").startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
					.build();

			scheduler.scheduleJob(job, trigger);

			scheduler.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}