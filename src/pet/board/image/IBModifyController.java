package pet.board.image;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import pet.bean.Board;
import pet.bean.Member;
import pet.board.inquiry.QBListController;
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
		MultipartRequest multRequest = null;

 		// 업로드 될 파일의 최대 사이즈 (10MB)
		int sizeLimit = 10 * 1024 * 1024;
		
		// 파일이 업로드될 실제 tomcat 폴더의 경로 (WebContent 기준)
		String savePath = request.getSession().getServletContext().getRealPath("upload");
		System.out.println("savePath : " + savePath);
		
		try {
			// MultipartRequest 객체를 생성하면 파일 업로드 수행
			multRequest = new MultipartRequest(request, savePath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());	
			
			// 파일 이름 추출 
			String filename = multRequest.getFilesystemName("imageFile");
			String title = multRequest.getParameter("title");
			String content = multRequest.getParameter("content");

			Member login = (Member)super.session.getAttribute("loginfo");

			bean = new Board();
			
			bean.setBoard_type("사진");
			bean.setWriter(login.getId()); 
			bean.setTitle(title);
			bean.setFile_name(filename);
			bean.setFile_path(savePath); 
			bean.setContent(content);

			BoardDao dao = new BoardDao();
			int cnt = -999999;
			
			cnt = dao.imageModify(bean);

			super.doGet(request, response);

			new IBListController().doGet(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
