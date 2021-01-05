package pet.board.inquiry;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Board;
import pet.common.SuperClass;
import pet.dao.BoardDao;

public class QBModifyController extends SuperClass {
	private Board bean = null ;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardDao dao = new BoardDao();
		
		bean = dao.selectDataByPK(no);
		
		request.setAttribute("bean", bean);
		
		super.doGet(request, response);
		
		String gotopage = "board/board_inquiry/QBModify.jsp";
		super.goToPage(gotopage);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bean = new Board();
		
		bean.setNo(Integer.parseInt(request.getParameter("no")));
		bean.setTitle(request.getParameter("title"));
		bean.setContent(request.getParameter("content"));
		bean.setWriter(request.getParameter("writer"));
		bean.setUpdated_at(request.getParameter("updated_at"));
		bean.setBoard_type(request.getParameter("board_type"));

		String gotopage = "";
		
		BoardDao dao = new BoardDao();
		int cnt = -999999;
			
		cnt = dao.modifyPost(bean);
			
		new QBListController().doGet(request, response);
	}
}
