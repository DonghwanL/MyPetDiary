package pet.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Member;
import pet.common.SuperClass;
import pet.dao.MemberDao;

public class MemberNicknameCheckController extends SuperClass {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickname = request.getParameter("nickname");
		
		MemberDao mdao= new MemberDao();
		Member bean = mdao.SelectDataByNn(nickname);
		
		if (bean == null) {// 존재하지 않는 닉네임 
			request.setAttribute("message", nickname + "은(는) <font color='blue'><b>사용 가능</b></font>한 닉네임입니다.");
			request.setAttribute("isCheck", true);
			
		} else { // 존재하는 경우 
				request.setAttribute("message", nickname + "은(는) 이미 <font color='red'><b>사용중</b></font>인 닉네임입니다.") ;
				request.setAttribute("isCheck", false) ;
			}

		
		super.doGet(request, response);
		
		String gotopage = "member/mNicknameCheck.jsp";
		super.goToPage( gotopage );
}
}