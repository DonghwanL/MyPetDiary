package pet.board.inquiry;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import pet.bean.Board;
import pet.common.SuperClass;
import pet.dao.BoardDao;

public class LikeUpdateAction extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("board_no"));
		
		BoardDao bdao = new BoardDao();
		
		bdao.updateLikes(no);
		
		Board bean = bdao.selectLike(no);
		
		System.out.println("총 Like 개수: " + bean.getLikes_count());
		
		super.doGet(request, response);
		request.setAttribute("bean", bean);
		
		System.out.println("좋아요 추가 완료");
		
		JSONObject obj = new JSONObject();
		obj.put("like", bean.getLikes_count());
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(obj);

//		String gotopage = "/board/board_inquiry/QBDetailView.jsp";
//		super.goToPage(gotopage);
	}
}
