package pet.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.bean.Postcode;
import pet.common.SuperClass;
import pet.dao.CompositeDao;

public class MemberZipCheckController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dong = request.getParameter("dong");
		List<Postcode> lists = null;
		CompositeDao dao = new CompositeDao();
		
		if (dong != null) {
			lists = dao.SelectDataZipcode(dong);
			System.out.println("검색된 동네 개수 : " + lists.size());
			
		}
		request.setAttribute("lists", lists);
		request.setAttribute("dong", dong);
		
		String goToPage = "member/mZipCheck.jsp";
		
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		this.doGet(request, response);
	}

}
