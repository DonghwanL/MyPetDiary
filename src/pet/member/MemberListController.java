package pet.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Member;
import pet.common.SuperClass;
import pet.dao.MemberDao;

public class MemberListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDao dao = new MemberDao();
		
		List<Member> lists = dao.allMemberList();
		
		request.setAttribute("lists", lists);
		
		super.doGet(request, response);
		
		String gotopage = "member/mList.jsp";
		super.goToPage(gotopage);
	}
}
