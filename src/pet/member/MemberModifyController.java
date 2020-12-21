package pet.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Member;
import pet.common.SuperClass;
import pet.dao.MemberDao;

public class MemberModifyController extends SuperClass {
	private Member bean = null;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		MemberDao mdao = new MemberDao();
		Member bean = mdao.selectDataByID(id);
		
		super.doGet(request, response);
		request.setAttribute("bean", bean);
		
		String gotopage = "/member/mModifyForm.jsp";
		super.goToPage(gotopage);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
				bean = new Member();
				
				bean.setAddress1(request.getParameter("address1"));
				bean.setAddress2(request.getParameter("address2"));
				bean.setId(request.getParameter("id"));
				bean.setName(request.getParameter("name"));
				bean.setPassword(request.getParameter("password"));
				bean.setZipcode(request.getParameter("zipcode"));
				bean.setNickname(request.getParameter("nickname"));
				bean.setEmail(request.getParameter("email"));
				bean.setPhone(request.getParameter("phone"));
//				bean.setMpoint(Integer.parseInt(request.getParameter("mpoint")));
//				bean.setMlevel(Integer.parseInt(request.getParameter("mlevel")));
				
				String gotopage = "";
				
				if (this.validate(request) == true) { // 유효성 검사 성공
					MemberDao dao = new MemberDao();
					int cnt = -99999;
					cnt = dao.modifyData(bean);
					
					gotopage = "";
					super.goToPage(gotopage);
					
				} else { // 유효성 검사 실패
					request.setAttribute("bean", bean);
					super.doPost(request, response);
					
					gotopage = "/member/meUpdateForm.jsp";
					super.goToPage(gotopage);
				}
	}
	
}
