package batch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import domain.Free;
import repository.FreeDAO;

public class FreeJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		try {
			List<Free> list = FreeDAO.getInstance().getFreeRank();
			File file = new File("BoardProject");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			String[] Rank = { "게시글번호", "작성자", "제목", "작성IP", "조회수", "내용" };
			for (int i = 0, length = list.size(); i < length; i++) {
				Free free = list.get(i);
				bw.write(Rank[i] + " " + free.getFreeNo());
			}
			bw.close();
			System.out.println("다음 달부터 사용 가능합니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}