package pet.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Board;
import pet.bean.Products;
import pet.dao.ProductDao;

public class MainController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductDao pdao = new ProductDao();
		
		List<Products> lists = pdao.mainProductList();

		request.setAttribute("lists", lists);

		super.doGet(request, response);
		
		String gotopage = "common/main.jsp";
		super.goToPage(gotopage);
	}
}
