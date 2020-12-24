package pet.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Member;
import pet.common.SuperClass;
import pet.dao.MemberDao;

public class MemberNickCheckController extends SuperClass {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
		String nickname = request.getParameter("nickname");
				
		MemberDao mdao = new MemberDao();
		Member bean = mdao.selectDataByNick(nickname);
				
		if (bean == null) {
			request.setAttribute("message", nickname + "은(는) <font color='blue'>사용 가능한</font> 닉네임 입니다.");
			request.setAttribute("isCheck", true);
					
		} else {
			
			if (bean.getNickname().equalsIgnoreCase("admin")) {
				request.setAttribute("message", "admin은 사용 불가능한 닉네임 입니다.");
				request.setAttribute("isCheck", false);
						
			} else {
				request.setAttribute("message", nickname + "은(는) 이미 <font color='#b3130b'>사용 중인</font> 닉네임 입니다.");
				request.setAttribute("isCheck", false);
			}
		}
			
		super.doGet(request, response);
			
		String gotopage = "member/mNicknameCheck.jsp";
		super.goToPage(gotopage);
	}
}
