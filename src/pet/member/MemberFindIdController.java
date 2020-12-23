package pet.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.common.SuperClass;
import pet.dao.MemberDao;

public class MemberFindIdController extends SuperClass {
	String name = null;
	String email = null;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		String gotopage = "/member/mFindIdForm.jsp";
		super.goToPage(gotopage);
	}
	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.name = request.getParameter("name");
		this.email = request.getParameter("email");
		
		String id = null;
		
		if(this.validate(request) == false ) {
			String gotopage = "";
			gotopage = "member/mFindIdForm.jsp";
			super.doPost(request, response);
			super.goToPage(gotopage); 
			
		} else {
			MemberDao dao = new MemberDao();
			id = dao.FindID(name, email);
			
			if(id == null) { //아이디가 존재하지 않을 경우, mFindIdForm.jsp 로 이동
				String gotopage = "";
				gotopage = "member/mFindIdForm.jsp";
				
				super.goToPage(gotopage);
				
			} else { // id 값이 null이 아니면, ID 보여주는 화면으로 이동 
				String gotopage = "";
				request.setAttribute("id", id);
				gotopage = "member/mGetIdForm.jsp";
				super.doPost(request, response);
				super.goToPage(gotopage);
			}
		}
	}
	
	@Override
	public boolean validate(HttpServletRequest request) {
		boolean isCheck = true; 
		
		if(this.name.length() == 0 || this.name == null || this.name == "null" ) {
			request.setAttribute(super.PREFIX + "name", "이름을 입력해주세요.");
			isCheck = false;
		}
		
		if(this.email.length() == 0 || this.email == null || this.email == "null" ) {
			request.setAttribute(super.PREFIX +"email", "이메일을 입력해주세요.");
			isCheck =  false;
		}
		
		return isCheck;
	}
}
