package pet.board.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Board;
import pet.bean.Member;
import pet.common.SuperClass;
import pet.dao.BoardDao;
import pet.util.FlowParameters;

public class FBDeleteController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardDao dao = new BoardDao();
		
		int cnt = -999999 ;
		cnt = dao.deletePost(no);
		
		new FBListController().doGet(request, response); 
	}
}
