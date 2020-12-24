package pet.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Member;
import pet.common.SuperClass;
import pet.dao.MemberDao;

public class MemberIDCheckController extends SuperClass {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		MemberDao mdao= new MemberDao();
		Member bean = mdao.selectDataByID(id);
		
		if (bean == null) {
			request.setAttribute("message", id + "은(는) <font color='blue'>사용 가능한</font> ID 입니다.");
			request.setAttribute("isCheck", true);
			
		} else {
			
			if (bean.getId().equalsIgnoreCase("admin")) {//관리자인 경우 
				request.setAttribute("message", "admin은 <font color='#b3130b'>사용 불가능</font>한 ID 입니다.");
				request.setAttribute("isCheck", false);
				
			} else {
				request.setAttribute("message", id + "은(는) 이미 <font color='#b3130b'>사용 중인</font> ID 입니다.");
				request.setAttribute("isCheck", false);
			}

		}
		
		super.doGet(request, response);
		
		String gotopage = "member/mIDCheck.jsp";
		super.goToPage(gotopage);
	}
}
