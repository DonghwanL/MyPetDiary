package pet.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import pet.bean.Products;
import pet.common.SuperClass;
import pet.dao.ProductDao;

public class PRAddController extends SuperClass {
	private Products bean = null;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String url = "product/PRAdd.jsp";
		super.goToPage(url); 	
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request 영역에 있는 업로드을 위한 객체 multi 정보를 읽어 들입니다.
		MultipartRequest multi = (MultipartRequest)request.getAttribute("multi");
		String savePath = request.getSession().getServletContext().getRealPath("upload");
		
		bean = new Products();
			
		System.out.println( "[" + multi.getParameter("category") + "]" );
		
		if(multi.getParameter("stock") != null && multi.getParameter("stock").equals("") == false){
			bean.setStock( Integer.parseInt( multi.getParameter("stock")));	
		}
		if(multi.getParameter("p_point") != null && multi.getParameter("p_point").equals("") == false){
			bean.setP_point( Integer.parseInt( multi.getParameter("p_point")));	
		}		
		if(multi.getParameter("price") != null && multi.getParameter("price").equals("") == false){
			bean.setPrice( Integer.parseInt( multi.getParameter("price")));	
		}
		
		bean.setCategory(multi.getParameter("category"));		
		bean.setContent(multi.getParameter("content"));
		bean.setFile_name(multi.getFilesystemName("imageFile"));
		bean.setCreated_at(multi.getParameter("created_at"));
		bean.setName(multi.getParameter("name"));
		bean.setFile_path(savePath);
		
		ProductDao pdao = new ProductDao();
		int cnt = -99999; 

		// Bean 객체를 이용하여 해당 게시물을 추가
		cnt = pdao.addProduct(bean);
		
		System.out.println("상품 등록이 완료 되었습니다");
		new adminPRListController().doGet(request, response);		
		
	}
}