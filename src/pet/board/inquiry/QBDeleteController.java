package pet.board.inquiry;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.common.SuperClass;
import pet.dao.BoardDao;

public class QBDeleteController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardDao bdao = new BoardDao();
		
		int cnt = -999999;
		cnt = bdao.deletePost(no);
		
		System.out.println("QB 게시물 삭제 완료");
		new QBListController().doGet(request, response);
	}
}
