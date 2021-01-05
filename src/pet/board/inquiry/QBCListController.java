package pet.board.inquiry;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import pet.bean.Comment;
import pet.common.SuperClass;
import pet.dao.CommentDao;

public class QBCListController extends SuperClass {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		CommentDao cdao = new CommentDao();
		
		List<Comment> commentList = cdao.readCommentList(no);
		
		if (commentList.size() > 0) {
			request.setAttribute("commentList", commentList); 
		}

		super.doGet(request, response);
		
		String gotopage = "board/board_inquiry/QBDetailView.jsp";
		super.goToPage(gotopage);
	}
	
}
