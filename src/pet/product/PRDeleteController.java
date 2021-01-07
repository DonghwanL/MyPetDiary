package pet.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.common.SuperClass;
import pet.dao.ProductDao;
import pet.util.FlowParameters;

public class PRDeleteController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 주문 상세 테이블 remark 컬럼 수정 및 상품 행 삭제 
		
		int p_id = Integer.parseInt(request.getParameter("p_id"));
		
		FlowParameters parameters
			= new FlowParameters(
					request.getParameter("pageNumber"),
					request.getParameter("pageSize"),
					request.getParameter("mode"),
					request.getParameter("keyword"));
		
		System.out.println(parameters.toString());
		
		ProductDao pdao = new ProductDao();
		
		int cnt = -99999;
		cnt = pdao.delectProduct(p_id);
		
		new adminPRListController().doGet(request, response);
	}
}
