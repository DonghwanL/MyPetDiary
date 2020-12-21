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
				request.setAttribute("message", nickname + "은(는) 사용 가능한 닉네임 입니다.");
				request.setAttribute("isCheck", true);
				
			} else {
				request.setAttribute("message", nickname + "은(는) 이미 사용 중인 닉네임 입니다.");
				request.setAttribute("isCheck", false);
			}
			
			super.doGet(request, response);
			
			String gotopage = "member/mNicknameCheck.jsp";
			super.goToPage(gotopage);
		}
}
