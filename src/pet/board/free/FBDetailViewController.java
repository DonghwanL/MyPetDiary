package pet.board.free;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Board;
import pet.bean.Comment;
import pet.bean.Member;
import pet.common.SuperClass;
import pet.dao.BoardDao;
import pet.dao.CommentDao;
import pet.member.MemberLoginController;
import pet.util.FlowParameters;

public class FBDetailViewController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardDao dao = new BoardDao();
		
		Board bean = dao.selectDataByPK(no);
		
		FlowParameters parameters 
			= new FlowParameters(
					request.getParameter("pageNumber"),
					request.getParameter("pageSize"),
					request.getParameter("mode"),
					request.getParameter("keyword"));
	
		System.out.println(this.getClass() + " : "  + parameters.toString());
		
		super.doGet(request, response);

		if (bean != null) { 
			
			Member login = (Member)super.session.getAttribute("loginfo");
			 
			if (login == null) {
				new MemberLoginController().doGet(request, response);
			}
			
			if (!bean.getWriter().equals(login.getId())) {
				dao.updateReadsCount(no);
			}
			
			CommentDao cdao = new CommentDao();
			
			List<Comment> commentList = cdao.readCommentList(no);
			
			if (commentList.size() > 0) {
				request.setAttribute("commentList", commentList); 
			}
			
			request.setAttribute("bean", bean);
			request.setAttribute("parameters", parameters.toString());
	  	
			String gotopage = "board/board_free/FBDetailView.jsp";
			super.goToPage(gotopage); 
			
		} else {
			new FBListController().doGet(request, response);
		}
		
	}
}
