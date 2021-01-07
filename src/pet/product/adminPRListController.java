package pet.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Products;
import pet.common.SuperClass;
import pet.dao.ProductDao;
import pet.util.FlowParameters;
import pet.util.Paging;

public class adminPRListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FlowParameters parameters 
			= new FlowParameters(
				request.getParameter("pageNumber"),
				request.getParameter("pageSize"),
				request.getParameter("mode"),
				request.getParameter("keyword")
			);
		
		System.out.println(this.getClass() + " : " + parameters.toString());
		
		ProductDao pdao = new ProductDao();
		
		int totalCount = pdao.selectTotalCount(parameters.getMode(), parameters.getKeyword() + "%");
		
		String contextPath = request.getContextPath();
		String myurl = contextPath + "/Shopping?command=adminPRList";
		
		Paging pageInfo = new Paging(
					parameters.getPageNumber(),
					parameters.getPageSize(),
					totalCount,
					myurl,
					parameters.getMode(),
					parameters.getKeyword()); 
			
		List<Products> lists = pdao.adminProductList(
				pageInfo.getBeginRow(),
				pageInfo.getEndRow(),
				parameters.getMode(),
				parameters.getKeyword() + "%");
		
		// 표에 들어갈 목록
		request.setAttribute("lists", lists);
		
		// 페이징 관련 항목
		request.setAttribute("pagingHtml", pageInfo.getPagingHtml());
		request.setAttribute("pagingStatus", pageInfo.getPagingStatus());
		
		// 필드 검색과 관련된 항목
		
		request.setAttribute("mode", parameters.getMode());
		request.setAttribute("keyword", parameters.getKeyword());
		
		// 파라미터 리스트 문자열 : 상세보기, 수정, 삭제, 답글 등에 사용
		request.setAttribute("parameters", parameters.toString());
				
		super.doGet(request, response);
		
		String gotopage = "product/adminPRList.jsp";
		super.goToPage(gotopage);
	}
}
