package pet.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pet.common.SuperClass;

public class MemberLogOutController extends SuperClass {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate(); 
		
		String contextPath = request.getContextPath() ;
		String gotopage = "/common/main.jsp"; 
		
		response.sendRedirect(contextPath + gotopage); 
	}
}
