package pet.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import pet.bean.Products;
import pet.common.SuperClass;
import pet.dao.ProductDao;

public class PRModifyController extends SuperClass {
	private Products bean = null;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int p_id = Integer.parseInt(request.getParameter("p_id"));
				
		ProductDao pdao = new ProductDao();
		Products bean = pdao.selectProductByPK(p_id);
		
		request.setAttribute("product", bean);
		
		String gotopage = "product/PRModify.jsp"; 
		
		super.doGet(request, response);
		super.goToPage(gotopage);		
	}
	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartRequest multi = (MultipartRequest)request.getAttribute("multi");
		String savePath = request.getSession().getServletContext().getRealPath("upload");
		
		bean = new Products();
		
		bean.setP_type(multi.getParameter("p_type"));
		bean.setCategory(multi.getParameter("category"));  
		bean.setFile_name(multi.getFilesystemName("imageFile"));
		bean.setFile_path(savePath);
		bean.setContent(multi.getParameter("content"));		
		bean.setName(multi.getParameter("name"));
		
		if (multi.getParameter("p_id") != null && multi.getParameter("p_id").equals("") == false) {
			bean.setP_id(Integer.parseInt(multi.getParameter("p_id")));	
		}
		
		if (multi.getParameter("p_point") != null && multi.getParameter("p_point").equals("") == false) {
			bean.setP_point(Integer.parseInt(multi.getParameter("p_point")));	
		}
		
		if (multi.getParameter("price") != null && multi.getParameter("price").equals("") == false) {
			bean.setPrice(Integer.parseInt(multi.getParameter("price")));	
		}
		
		if (multi.getParameter("stock") != null && multi.getParameter("stock").equals("") == false) {
			bean.setStock(Integer.parseInt(multi.getParameter("stock")));	
		}				
	
		ProductDao pdao = new ProductDao();			
		int cnt = -999999;
		
		cnt = pdao.modifyProduct(bean);
		new adminPRListController().doGet(request, response);
	}
}
