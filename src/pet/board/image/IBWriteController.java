package pet.board.image;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import pet.bean.Board;
import pet.bean.Member;
import pet.common.SuperClass;
import pet.dao.BoardDao;
import pet.dao.MemberDao;

public class IBWriteController extends SuperClass {
	private Board bean = null;

	//업로드가 되는 실제 경로
	private String uploadedPath = null; 

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		String gotpage = "board/board_image/IBWrite.jsp";
		super.goToPage(gotpage);
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
			
			bean.setBoard_type("사진");	// 게시글 타입 
			bean.setWriter(login.getId()); // 작성자 
			bean.setTitle(title);	// 제목 
			bean.setFile_name(filename); // 파일이름 
			bean.setFile_path(savePath); // 파일경로 
			bean.setContent(content); // 글 내용 

			BoardDao bdao = new BoardDao();
			MemberDao mdao = new MemberDao();
			Member member = new Member();
			int cnt = -999999;
			
			cnt = bdao.imagePost(bean);

			super.doGet(request, response);
			
			member = (Member)super.session.getAttribute("loginfo");
			
			int count = bdao.selectPostCount(member.getId()); 

			if (count >= 10) {
				// 회원 레벨을 2로 업데이트
				mdao.userLevelUpdate(member.getId(), 2);
				
			} else if (count >= 20) {
				// 회원 레벨을 3으로 업데이트
				mdao.userLevelUpdate(member.getId(), 3);
			}

			new IBListController().doGet(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
