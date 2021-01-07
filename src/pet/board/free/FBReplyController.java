package pet.board.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Board;
import pet.common.SuperClass;
import pet.dao.BoardDao;

public class FBReplyController extends SuperClass {
	Board bean = null;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String gotopage = "board/board_free/FBReply.jsp";
		super.goToPage(gotopage);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bean = new Board();
		
		bean.setBoard_type(request.getParameter("board_type"));
		bean.setContent(request.getParameter("content"));
		bean.setCreated_at(request.getParameter("created_at"));
		bean.setTitle(request.getParameter("title"));
		bean.setWriter(request.getParameter("writer"));
		
		int groupno = Integer.parseInt(request.getParameter("groupno"));
		bean.setGroup_no(groupno);
		
		int orderno = Integer.parseInt(request.getParameter("orderno"));
		bean.setOrder_no(orderno + 1);
		
		int depth = Integer.parseInt(request.getParameter("depth"));
		bean.setDepth(depth + 1);
		
		BoardDao bdao = new BoardDao();
		int cnt = -99999;
		
		cnt = bdao.addReply(bean, groupno, orderno);
			
		new FBListController().doGet(request, response);
	}
}
