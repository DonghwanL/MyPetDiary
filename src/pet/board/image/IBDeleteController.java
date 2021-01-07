package pet.board.image;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.common.SuperClass;
import pet.dao.BoardDao;

public class IBDeleteController extends SuperClass {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("IBDeleteController START ===========");
		
		int no = Integer.parseInt(request.getParameter("no"));

		BoardDao dao = new BoardDao();

		int cnt = -999999;
		cnt = dao.deletePost(no);

		new IBListController().doGet(request, response);
	}
}
