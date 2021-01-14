package pet.board.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Board;
import pet.bean.Member;
import pet.common.SuperClass;
import pet.dao.BoardDao;
import pet.dao.MemberDao;

public class FBWriteController extends SuperClass {
private Board bean = null;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
		
		String gotopage = "board/board_free/FBWrite.jsp";
		super.goToPage(gotopage);	
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		bean  = new Board();
		
		bean.setBoard_type(request.getParameter("board_type"));
		bean.setContent(request.getParameter("content"));
		bean.setCreated_at(request.getParameter("created_at"));
		bean.setTitle(request.getParameter("title"));
		bean.setWriter(request.getParameter("writer"));
		
		if(request.getParameter("depth") != null && request.getParameter("depth") != "") {
			bean.setDepth(Integer.parseInt(request.getParameter("depth")));	
		}
		
		if(request.getParameter("groupno") != null && request.getParameter("groupno") != "") {
			bean.setGroup_no(Integer.parseInt(request.getParameter("groupno")));	
		}
		
		if(request.getParameter("orderno") != null && request.getParameter("orderno") != "") {
			bean.setOrder_no(Integer.parseInt(request.getParameter("orderno")));	
		}
		
		if(request.getParameter("reads_count") != null && request.getParameter("reads_count") != "") {
			bean.setReads_count(Integer.parseInt(request.getParameter("reads_count")));	
		}   
		
		System.out.println(bean);
				
		BoardDao bdao = new BoardDao();
		MemberDao mdao = new MemberDao();
		Member member = new Member();
		int cnt = -99999; 		
		
		cnt = bdao.writePost(bean);
		
		member = (Member)super.session.getAttribute("loginfo");
		
		int count = bdao.selectPostCount(member.getId()); 

		if (count >= 10) {
			// 회원 레벨을 2로 업데이트
			mdao.userLevelUpdate(member.getId(), 2);
			
		} else if (count >= 20) {
			// 회원 레벨을 3으로 업데이트
			mdao.userLevelUpdate(member.getId(), 3);
		}

		new FBListController().doGet(request, response);
	}
}
