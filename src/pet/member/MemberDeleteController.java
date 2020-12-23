package pet.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.common.SuperClass;
import pet.dao.MemberDao;

public class MemberDeleteController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		MemberDao mdao = new MemberDao();
		int cnt = -99999;
		
		
		
		cnt = mdao.deleteMember(id);
		
		super.doGet(request, response);
		
		super.session.invalidate();
		
		System.out.println("MDAO : 회원정보 삭제 완료");
		
		new MemberLoginController().doGet(request, response);
	}
}
