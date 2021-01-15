package pet.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.common.SuperClass;
import pet.dao.MemberDao;

public class MemberFindPasswordController extends SuperClass {
	String name = null;
	String id = null;
	String email = null;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		String gotopage = "";
		
		gotopage = "/member/mFindPasswordForm.jsp";
		super.goToPage(gotopage);
	}
	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.name = request.getParameter("name");
		this.id = request.getParameter("id");
		this.email = request.getParameter("email");
		
		String password = null;
		
		if (this.validate(request) == false) {
			String gotopage = "";
			gotopage = "member/mFindPasswordForm.jsp";
			super.goToPage(gotopage);
			
		} else {
			MemberDao dao = new MemberDao();
			password = dao.FindPassword(name, id, email);
			
			if (password == null) {
				String gotopage = "";
				gotopage = "member/mFindPasswordForm.jsp";
				super.goToPage(gotopage);
				
			} else {
				String gotopage = "";
				request.setAttribute("password", password);
				super.doPost(request, response);
				gotopage = "member/mGetPasswordForm.jsp";
				super.goToPage(gotopage);
			}
		}
		
		super.doPost(request, response);
	}
	
	@Override
	public boolean validate(HttpServletRequest request) {
		boolean isCheck = true; 
		
		if(this.name.length() == 0 || this.name == null || this.name == "null" ) {
			request.setAttribute(super.PREFIX + "name", "이름을 입력해주세요.");
			isCheck = false;
		}
		
		if(this.id.length() == 0 || this.id == null || this.id == "null" ) {
			request.setAttribute(super.PREFIX +"id", "아이디를 입력해주세요.");
			isCheck =  false;
		}
		
		if(this.email.length() == 0 || this.email == null || this.email == "null" ) {
			request.setAttribute(super.PREFIX +"email", "이메일을 입력해주세요.");
			isCheck =  false;
		}
		
		return isCheck;
	}
}
