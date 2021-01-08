package pet.board.image;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import pet.bean.Board;
import pet.common.SuperClass;
import pet.dao.BoardDao;

public class IBModifyController extends SuperClass {
	private Board bean = null;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardDao dao = new BoardDao();
		
		bean = dao.selectDataByPK(no);
		
		request.setAttribute("bean", bean);
		
		super.doGet(request, response);
		
		String gotopage = "board/board_image/IBModify.jsp";
		super.goToPage(gotopage);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		MultipartRequest multi = (MultipartRequest)request.getAttribute("multi");
		String savePath = request.getSession().getServletContext().getRealPath("upload");
		
		bean = new Board();
		
		bean.setBoard_type(multi.getParameter("board_type"));
		bean.setTitle(multi.getParameter("title"));
		bean.setFile_name(multi.getFilesystemName("imageFile"));
		bean.setFile_path(savePath); 
		bean.setContent(multi.getParameter("content"));
		
		if (multi.getParameter("no") != null && multi.getParameter("no").equals("") == false) {
			bean.setNo(Integer.parseInt(multi.getParameter("no")));	
		}
	
		BoardDao bdao = new BoardDao();			
		int cnt = -999999;
		
		cnt = bdao.imageModify(bean);
		new IBListController().doGet(request, response);
	}
}
