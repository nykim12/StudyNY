package mybatis;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.exceptions.PersistenceException;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class AddService implements ProductService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String realPath = request.getServletContext().getRealPath("storage");

		File dir = new File(realPath);
		if (dir.exists() == false) {
			dir.mkdirs();
		}

		File file = null;
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(request, realPath, 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			error(file, response, "파일 첨부가 실패했습니다.");
		}

		file = mr.getFile("filename");
		String name = mr.getParameter("name");
		Integer price = Integer.parseInt(mr.getParameter("price"));
		String image = mr.getFilesystemName("filename");
		ProductDTO product = ProductDTO.builder()
				.name(name)
				.price(price)
				.image(image)
				.build();
		ActionForward af = null;
		try {
			int res = ProductDAO.getInstance().insertProduct(product);
			if (res > 0) {
				af = new ActionForward("/JUNIT/list.prod", true);
			}
		} catch (PersistenceException e) { // UNIQUE, NOT NULL, COLUMN TYPE, SIZE 등 DB 오류
			error(file, response, "데이터베이스 오류");
		}
		return af;	
	}

	public void error(File file, HttpServletResponse response, String msg) {
		if (file != null && file.exists()) {
			file.delete();
		}
		try {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + msg + "')");
			out.println("history.back()");
			out.println("</script>");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}