package ex02_tomcat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		try {
			File file = new File("C:\\Users\\GDJ45\\git\\studyNY\\BATCH\\myJob.txt");
			FileWriter fw = new FileWriter((file));
			fw.write("Hello World\n");
			fw.write(new SimpleDateFormat("yyyy-mm-dd").format(new Date()));
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}