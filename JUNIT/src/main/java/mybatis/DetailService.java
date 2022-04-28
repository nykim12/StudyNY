package mybatis;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetailService implements ProductService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		Optional<String> optNo = Optional.ofNullable(request.getParameter("productNo"));
		Long productNo = Long.parseLong(optNo.orElse("0"));
		request.setAttribute("product", ProductDAO.getInstance().selectProductListByNo(productNo));
		request.setAttribute("contextPath", request.getContextPath());
		return new ActionForward("product2/detail.jsp", false);
	}

}