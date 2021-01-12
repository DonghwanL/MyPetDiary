package pet.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Member;
import pet.bean.Products;
import pet.common.SuperClass;
import pet.dao.ProductDao;

public class PRDetailViewController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int p_id = Integer.parseInt(request.getParameter("p_id").trim());

		ProductDao dao = new ProductDao();

		Products bean = dao.selectProductByPK(p_id);

		super.doGet(request, response);

		if (bean != null) {
			Member login = (Member) super.session.getAttribute("loginfo");

			request.setAttribute("bean", bean);

			String gotopage = "product/PRDetail.jsp";
			super.goToPage(gotopage);

		} else {
			new PRListController().doGet(request, response);
		}
	}
}
