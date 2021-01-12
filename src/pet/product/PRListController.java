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

public class PRListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filterType = request.getParameter("filterType");
		String pageNumber = request.getParameter("pageNumber") != null ? request.getParameter("pageNumber") : "1";
		String pageSize = request.getParameter("pageSize") != null ? request.getParameter("pageSize") : "9";

		ProductDao dao = new ProductDao();

		FlowParameters parameters = 
				new FlowParameters(
						pageNumber, 
						"9", 
						request.getParameter("mode"),
						request.getParameter("keyword"));

		int totalCount 
			= dao.selectTotalCount_S(
					parameters.getMode(), 
					parameters.getKeyword(),
					filterType);

		String contextPath = request.getContextPath();
		String myurl = contextPath + "/Mypet?command=PRList";

		Paging pageInfo 
			= new Paging(
					parameters.getPageNumber(), 
					parameters.getPageSize(), 
					totalCount, 
					myurl,
					parameters.getMode(), 
					parameters.getKeyword());

		List<Products> lists = dao.filterProductList(
				pageInfo.getBeginRow(), 
				pageInfo.getEndRow(),
				filterType);
	

		// 바인딩해야 할 목록들
		request.setAttribute("lists", lists);

		// 페이징 관련 항목들
		request.setAttribute("pagingHtml", pageInfo.getPagingHtml());
		request.setAttribute("pagingStatus", pageInfo.getPagingStatus());

		// 검색 필들의 상태 값 저장을 위한 항목들
		request.setAttribute("mode", parameters.getMode());
		request.setAttribute("keyword", parameters.getKeyword());

		// 상세보기, 수정, 삭제, 답글 등의 링크에 사용될 parameter list 문자열
		request.setAttribute("parameters", parameters.toString());

		super.doGet(request, response);
		
		String gotopage = "product/PRList.jsp";
		super.goToPage(gotopage);
	}
}
