package dbcp;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductDetailService implements ProductService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		Optional<String> opt = Optional.ofNullable(request.getParameter("product_no"));
		Long product_no = Long.parseLong(opt.orElse("0"));
		String contextPath = request.getContextPath();
		request.setAttribute("contextPath", contextPath);
		request.setAttribute("product", ProductDAO.getInstance().selectProductByNo(product_no));

		return new ActionForward("product/detail.jsp", false);
	}

}