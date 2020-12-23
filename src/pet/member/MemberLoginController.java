package pet.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Member;
import pet.common.SuperClass;
import pet.dao.MemberDao;

public class MemberLoginController extends SuperClass {
	String id = null;
	String password = null;
	
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String gotopage = "/member/mLoginForm.jsp";
		super.goToPage(gotopage);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.id = request.getParameter("id");
		this.password = request.getParameter("password");
		
		String gotopage= "";
		
		if(this.validate(request) == false) {
			
			gotopage = "member/mLoginForm.jsp";
			request.setAttribute("id", this.id);
			request.setAttribute("password", this.password);
			
			super.doPost(request, response);
			super.goToPage(gotopage);
			
			
		} else {
			MemberDao dao = new MemberDao();
			
			Member bean = dao.Login(id, password);
			
			String message = "";
			super.doPost(request, response);

			if(bean == null) {
				gotopage = "member/mLoginForm.jsp";
				message = "아이디나 비번이 잘못되었습니다";
				request.setAttribute("errmsg", message);
				super.goToPage(gotopage);
				
			} else {
				super.session.setAttribute("loginfo", bean);
				gotopage = "common/main.jsp";
				super.goToPage(gotopage);
			}			
		}
	}
	
	
	@Override
	public boolean validate(HttpServletRequest request) {
		boolean isCheck = true; 
		
		if(this.id.length() == 0 || this.id == null || this.id == "null" ) {
			request.setAttribute(super.PREFIX + "id", "아이디를 입력해주세요.");
			isCheck = false;
		}
		
		if(this.password.length() == 0 || this.password == null || this.password == "null" ) {
			request.setAttribute(super.PREFIX +"password", "비밀번호를 입력해주세요.");
			isCheck =  false;
		}
		
		return isCheck;
	}
}
