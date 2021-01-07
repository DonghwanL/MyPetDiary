package pet.board.image;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Board;
import pet.bean.Comment;
import pet.common.SuperClass;
import pet.dao.BoardDao;
import pet.dao.CommentDao;

public class IBCWriteController extends SuperClass {
	Comment bean = null;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		bean = new Comment();
		
		if(request.getParameter("no") != null && request.getParameter("no") != "") {
			bean.setB_no(Integer.parseInt(request.getParameter("no")));
		}
		
		bean.setContent(request.getParameter("comments_content"));
		bean.setWriter(request.getParameter("comment_writer"));
		
		System.out.println(bean);
				
		CommentDao cdao = new CommentDao(); 			
		int cnt = -99999; 		

		cnt = cdao.addComment(bean);
		request.setAttribute("bean", bean);
		
		new IBDetailViewController().doGet(request, response);
	}
}
