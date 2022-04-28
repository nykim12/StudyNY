package mybatis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListService implements ProductService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("product", ProductDAO.getInstance().selectProductList());
		return new ActionForward("product2/list.jsp", false);
	}

}