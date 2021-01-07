package pet.board.inquiry;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Board;
import pet.common.SuperClass;
import pet.dao.BoardDao;
import pet.util.FlowParameters;
import pet.util.Paging;

public class QBListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao bdao = new BoardDao();
		
		FlowParameters parameters = new FlowParameters(
				request.getParameter("pageNumber"), 
				request.getParameter("pageSize"), 
				request.getParameter("mode"), 
				request.getParameter("keyword"));
		
		System.out.println(this.getClass() + " : " + parameters.toString());
		
		
		int totalCount = bdao.selectTotalCount_Q(
							parameters.getMode(),
							parameters.getKeyword());
		
		String contextPath = request.getContextPath();
		String myurl = contextPath + "/Mypet?command=QBList";
		
		Paging pageInfo = new Paging(
				parameters.getPageNumber(),
				parameters.getPageSize(),
				totalCount,
				myurl,
				parameters.getMode(),
				parameters.getKeyword());
				
		
		List<Board> lists = bdao.readBoardList_Q(
				pageInfo.getBeginRow(),
				pageInfo.getEndRow(),
				parameters.getMode(),
				parameters.getKeyword() + "%");
		
		// [바인딩 목록]
		
		// 게시물 목록
		request.setAttribute("lists", lists); 
		
		// 페이징 관련 항목 
		request.setAttribute("pagingHtml", pageInfo.getPagingHtml());
		request.setAttribute("pagainStatus", pageInfo.getPagingStatus());
		
		// 검색 필드의 상태 값 저장을 위한 항목
		request.setAttribute("mode", parameters.getMode());
		request.setAttribute("keyword", parameters.getKeyword());
		
		// 상세보기, 수정, 삭제, 답글 등의 링크에 사용될 Parameter List 문자열
		request.setAttribute("parameters", parameters.toString());
		
		System.out.println(parameters.toString());
		
		super.doGet(request, response);
		
		String gotopage = "board/board_inquiry/QBList.jsp";
		super.goToPage(gotopage);
	}
}
