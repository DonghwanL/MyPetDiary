package pet.board.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Comment;
import pet.board.image.IBDetailViewController;
import pet.common.SuperClass;
import pet.dao.CommentDao;

public class FBCModifyController extends SuperClass {
	private Comment bean = null;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cno = Integer.parseInt(request.getParameter("cno"));
		
		CommentDao cdao = new CommentDao();
		
		int cnt = -999999;
		bean = cdao.getComment(cno);
		
		request.setAttribute("comment", bean);
		
		super.doGet(request, response);
		
		String gotopage = "board/board_free/FBCModify.jsp";
		super.goToPage(gotopage);
	}
	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bean = new Comment();
		
		bean.setC_no(Integer.parseInt(request.getParameter("cno")));
		bean.setContent(request.getParameter("comment_content"));
		
		String gotopage = "";
		
		CommentDao cdao = new CommentDao();
		int cnt = -999999;
			
		cnt = cdao.modifyComment(bean);
		
		System.out.println("코멘트 수정 완료");
		
		new FBDetailViewController().doPost(request, response);
	}
}
