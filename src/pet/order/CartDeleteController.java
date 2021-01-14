package pet.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.common.SuperClass;

public class CartDeleteController extends SuperClass {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String gotopage = "";
		
		if (super.session.getAttribute("loginfo") == null) {			
			gotopage = "member/mLoginForm.jsp";
			super.goToPage(gotopage);
			
		} else {
			MyCartList mycart = (MyCartList)session.getAttribute("mycart");
			
			if (mycart == null) {
				mycart = new MyCartList();
			}
			
			int p_id = Integer.parseInt(request.getParameter("p_id"));			
			
			mycart.DeleteOrder(p_id); 
			session.setAttribute("mycart", mycart); 
			new CartListController().doGet(request, response); 
		}
	}
}
