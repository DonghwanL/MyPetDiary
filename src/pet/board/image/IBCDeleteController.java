package pet.board.image;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.common.SuperClass;
import pet.dao.CommentDao;

public class IBCDeleteController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("cno"));
		
		CommentDao cdao = new CommentDao();
		
		int cnt = -999999;
		cnt = cdao.deleteComments(no);
		
		System.out.println("QB 코멘트 삭제 완료");
		new IBDetailViewController().doGet(request, response);
	}
}
